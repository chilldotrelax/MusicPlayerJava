package org.andy.musicplayer_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;

public class TopLevel_Scene extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(TopLevel_Scene.class.getResource("music-player.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);

        //Check for possibility of icon being null.
        Image icon = new Image(getClass().getResourceAsStream("/Assets/app_icon.png"));

        stage.setTitle("Music Player");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();

        //Make sure to not directly instantiate startup w/ new MusicController(). FXML will not be injected.

        //To do: Make sure to add protection for empty "songs" folder.
        MusicController_Logic startup = fxmlLoader.getController();
        startup.initialize();

    }
}
