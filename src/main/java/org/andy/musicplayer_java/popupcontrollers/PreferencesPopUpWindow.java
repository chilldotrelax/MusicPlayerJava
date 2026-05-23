package org.andy.musicplayer_java.popupcontrollers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PreferencesPopUpWindow {
    private final Parent input;


    public PreferencesPopUpWindow(Parent input){
        this.input = input;
    }

    public void initializePreferencesPopup(){
        Stage preferences_stage = new Stage();

        preferences_stage.setTitle("Preferences");
        preferences_stage.setScene(new Scene(this.input)); //Root = this.input
        preferences_stage.show();
    }
}
