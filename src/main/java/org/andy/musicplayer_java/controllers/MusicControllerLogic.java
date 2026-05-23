package org.andy.musicplayer_java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.stage.*;
import org.andy.musicplayer_java.popupcontrollers.AboutPopUpWindow;
import org.andy.musicplayer_java.backgroundtasks.FileImportHandler;
import org.andy.musicplayer_java.backgroundtasks.MusicListHandler;
import org.andy.musicplayer_java.MediaPlayerHandler;
import org.andy.musicplayer_java.popupcontrollers.PreferencesPopUpWindow;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.util.Objects;

public class MusicControllerLogic {
    FileChooser chooseFileDialog = new FileChooser();
    FileImportHandler selectedFile = new FileImportHandler();
    MediaPlayerHandler playMusic = null;
    MusicListHandler updateCurrentList = new MusicListHandler();

    @FXML
    private Button playButton;
    @FXML
    private Button stopButton;
    @FXML
    private Label nowPlayingLabel;
    @FXML
    private ListView<String> musicCatalog;
    @FXML

    public void initialize(){
        musicCatalog.setDisable(true);
        if (!updateCurrentList.updateList().isEmpty()){
            musicCatalog.setItems(updateCurrentList.updateList());
            musicCatalog.setDisable(false);
        }

    }
    @FXML
    private Parent aboutWindowPopup = null;
    @FXML
    private Parent preferencesWindowPopup = null;
    @FXML
    private ProgressBar songProgressBar;

    private String currentSongPlaying;

    @FXML
    private void selectedMusicFromList(){
        currentSongPlaying = musicCatalog.getSelectionModel().getSelectedItem();
            if (Objects.equals(nowPlayingLabel.getText(), "Nothing Playing")){
                try{
                    playMusic = new MediaPlayerHandler("src/Songs/"+currentSongPlaying,songProgressBar);
                    playButton.setDisable(false);
                    nowPlayingLabel.setText(currentSongPlaying);
                }
                catch (RuntimeException e){
                    playMusic = null;
                    currentSongPlaying = null;
                }
            }
            else if (!Objects.equals(nowPlayingLabel.getText(), "Nothing Playing")){
                try{
                    songProgressBar.setProgress(Double.MIN_VALUE);
                    playMusic.pauseSound();
                    playMusic = null;
                    playMusic = new MediaPlayerHandler("src/Songs/"+currentSongPlaying,songProgressBar);
                    playButton.setDisable(false);
                    nowPlayingLabel.setText(currentSongPlaying);
                }
                catch (RuntimeException e){
                    System.out.println("Empty Cell Selected");
                }
            }
    }

    //Controls button Logic
    @FXML
    private void playButtonClick(){
        playButton.setDisable(true);
        playMusic.playSound();
        nowPlayingLabel.setText("Playing: "+ musicCatalog.getSelectionModel().getSelectedItem());
        stopButton.setDisable(false);
    }
    @FXML
    private void stopButtonClick(){
        stopButton.setDisable(true);
        playMusic.pauseSound();
        playButton.setDisable(false);
    }
    @FXML
    private void loopButtonClick(){
        playMusic.loopSound();
    }

    //Menu Items Logic
    @FXML
    private void importMusicFromFileOnEvent() {
        Window owner = nowPlayingLabel.getScene().getWindow();
        chooseFileDialog.setTitle("Select MP3 File");
        chooseFileDialog.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3 Files","*.mp3"));
        File music_file_choice = chooseFileDialog.showOpenDialog(owner);

        if (music_file_choice != null && music_file_choice.exists() && music_file_choice.isFile() && musicCatalog.isDisabled()){
            String path = music_file_choice.getAbsolutePath();
            selectedFile.openFromFile(path);
            musicCatalog.setDisable(false);
            musicCatalog.setItems(updateCurrentList.updateList());
        }
        else{
            System.out.println("Error: No file chosen or invalid file type!");
        }
    }
    @FXML
    private void importMusicFromFolderOnEvent(){
        Window owner = nowPlayingLabel.getScene().getWindow();
        chooseFileDialog.setTitle("Select Multiple MP3 Files");
        List<File> music_from_folder_choice = chooseFileDialog.showOpenMultipleDialog(owner);

        if (music_from_folder_choice != null && musicCatalog.isDisabled()){
            selectedFile.openFromFolder(music_from_folder_choice);
            musicCatalog.setDisable(false);
            musicCatalog.setItems(updateCurrentList.updateList());
        }
        else{
            System.out.println("Error: No file chosen or invalid file type!");
        }
    }
    @FXML
    private void appPreferencesOnEvent() throws IOException {
        if (preferencesWindowPopup == null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/andy/musicplayer_java/preferences-menu.fxml"));

            preferencesWindowPopup = loader.load();

            PreferencesPopUpWindow initializePrefWindow = new PreferencesPopUpWindow(preferencesWindowPopup);
            initializePrefWindow.initializePreferencesPopup();
        }
        preferencesWindowPopup = null;

    }
    @FXML
    private void quitAppOnEvent(){
        System.exit(0);

    }
    @FXML
    private void aboutAppOnEvent() throws IOException {
        if (aboutWindowPopup == null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/andy/musicplayer_java/About-Menu.fxml"));
            aboutWindowPopup = loader.load();
            AboutPopUpWindow initializeAboutWindow = new AboutPopUpWindow(aboutWindowPopup);
            initializeAboutWindow.initializePopup();
        }
        aboutWindowPopup = null;
    }
}
