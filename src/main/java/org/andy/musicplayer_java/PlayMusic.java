package org.andy.musicplayer_java;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlayMusic {
    Path pathsong;
    URI uri;

    public PlayMusic(String CS){
        this.pathsong = Paths.get(CS);
        this.uri = pathsong.toUri();
        this.resolved_Path = uri.toString();
    }

    private String resolved_Path;
    //TODO Address the null resolved_path.
    Media songAsMedia = new Media(resolved_Path);

    MediaPlayer playMedia = new MediaPlayer(songAsMedia);

    public void playSound(){

        playMedia.play();
    }

    public void pauseSound(){

        playMedia.stop();
    }

    public void loopSound(){
        //TODO
    }
}
