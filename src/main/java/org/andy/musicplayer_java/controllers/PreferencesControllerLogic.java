package org.andy.musicplayer_java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PreferencesControllerLogic {
    private Path settingsJSON = Paths.get("src/Configuration Files/settings.json");

    @FXML
    private CheckBox appearanceButton,lastSongMemory,exp_autoFormatConversion;

    @FXML
    private void colorScheme_OnEvent(){
        if (appearanceButton.isSelected()){

        }
        else{

        }
    }
    @FXML
    private void saveLastMusic_OnEvent(){
        if (lastSongMemory.isSelected()){

        }
        else{

        }
    }
    @FXML
    private void convertFormat_OnEvent(){
        if (exp_autoFormatConversion.isSelected()){

        }
        else{

        }
    }

}
