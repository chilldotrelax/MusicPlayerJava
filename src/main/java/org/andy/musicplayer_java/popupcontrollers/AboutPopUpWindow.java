package org.andy.musicplayer_java.popupcontrollers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AboutPopUpWindow {
    private final Parent input;

    public AboutPopUpWindow(Parent input){
        this.input = input;
    }

    public void initializePopup(){
        Stage about_stage = new Stage();

        about_stage.setTitle("About");
        about_stage.setScene(new Scene(this.input));
        about_stage.show();
    }

}
