package org.andy.musicplayer_java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PreferencesControllerLogic {
    Path settingsJSON = Paths.get("src/Configuration Files/settings.json");


    @FXML
    private CheckBox appearanceButton,lastSongMemory,exp_autoFormatConversion;




    public void colorScheme_OnEvent(){
        if (appearanceButton.isSelected()){

        }
        else{

        }
    }

    public void saveLastMusic_OnEvent(){
        if (lastSongMemory.isSelected()){

        }
        else{

        }
    }

    public void convertFormat_OnEvent(){
        if (exp_autoFormatConversion.isSelected()){

        }
        else{

        }
    }

}
