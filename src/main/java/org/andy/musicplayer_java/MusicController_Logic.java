package org.andy.musicplayer_java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.stage.*;
import org.andy.musicplayer_java.popup_controllers.PreferencesPopUpWindow;
import java.io.IOException;
import java.util.List;
import java.io.File;

public class MusicController_Logic {
    FileChooser fil_chooser = new FileChooser();
    File_Import_Manager grab_file = new File_Import_Manager();
    PlayMusic playMusic = null;
    MusicList_Controller updateCurrList = new MusicList_Controller();

    @FXML
    private Button PlayButton;

    @FXML
    private Button StopButton;

    @FXML
    private Label nowPlayingLabel;

    @FXML //Static field.
    private ListView<String> musicListView;

    @FXML
    public void initialize(){
        musicListView.setItems(updateCurrList.updateList());
    }

    @FXML
    private Parent aboutWindowPopup = null;

    @FXML
    private Parent preferencesWindowPopup = null;

    @FXML
    private ProgressBar songProgressBar;


    private String currentSongPlaying;

    @FXML
    private void senseEvent(){
        currentSongPlaying = musicListView.getSelectionModel().getSelectedItem();
        if (playMusic == null){
            playMusic = new PlayMusic("src/Songs/"+currentSongPlaying);
        }
        else if (currentSongPlaying != null){
            PlayButton.setDisable(true);
            playMusic = null;
            playMusic = new PlayMusic("src/Songs/"+currentSongPlaying);
        }
        PlayButton.setDisable(false);
        nowPlayingLabel.setText(currentSongPlaying);
    }

    //Controls button Logic
    @FXML
    private void playButtonClick(){
        PlayButton.setDisable(true);
        StopButton.setDisable(false);
        playMusic.playSound(songProgressBar);
        nowPlayingLabel.setText("Playing: "+ musicListView.getSelectionModel().getSelectedItem());
    }
    @FXML
    private void stopButtonClick(){
        PlayButton.setDisable(false);
        playMusic.pauseSound(songProgressBar);
        StopButton.setDisable(true);
    }
    @FXML
    private void loopButtonClick(){
        playMusic.loopSound();
    }

    //Menu Items Logic
    @FXML
    private void importMusicFromFile_OnEvent() {
        Window owner = nowPlayingLabel.getScene().getWindow();
        fil_chooser.setTitle("Select MP3 File");
        File music_file_choice = fil_chooser.showOpenDialog(owner);

        if (music_file_choice != null && music_file_choice.exists() && music_file_choice.isFile()){
            String path = music_file_choice.getAbsolutePath();
            grab_file.openFromFile(path);
            musicListView.setItems(updateCurrList.updateList());
        }
        else{
            System.out.println("Error: No file chosen or invalid file type!");
        }
    }
    @FXML
    private void importMusicFromFolder_OnEvent(){
        Window owner = nowPlayingLabel.getScene().getWindow();
        fil_chooser.setTitle("Select Multiple MP3 Files");
        List<File> music_from_folder_choice = fil_chooser.showOpenMultipleDialog(owner);

        if (music_from_folder_choice != null){
            grab_file.openFromFolder(music_from_folder_choice);
            musicListView.setItems(updateCurrList.updateList());
        }
        else{
            System.out.println("Error: No file chosen or invalid file type!");
        }

    }
    @FXML
    private void appPreferences_OnEvent() throws IOException {
        if (preferencesWindowPopup == null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("preferences-menu.fxml"));

            preferencesWindowPopup = loader.load();

            PreferencesPopUpWindow initializePrefWindow = new PreferencesPopUpWindow(preferencesWindowPopup);
            initializePrefWindow.initializePreferencesPopup();
        }
        preferencesWindowPopup = null;

    }
    @FXML
    private void quitApp_OnEvent(){
        System.exit(0);

    }
    @FXML
    private void aboutApp_OnEvent() throws IOException {
        if (aboutWindowPopup == null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("About-Menu.fxml"));

            aboutWindowPopup = loader.load();

            AboutPopUpWindow initializeAboutWindow = new AboutPopUpWindow(aboutWindowPopup);
            initializeAboutWindow.initializePopup();

        }
        aboutWindowPopup = null;


    }
}
