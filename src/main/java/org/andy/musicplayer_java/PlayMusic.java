package org.andy.musicplayer_java;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlayMusic {
    Path path_song;
    URI uri;
    String resolved_Path;
    Media songAsMedia = null;
    MediaPlayer playMedia = null;

    public PlayMusic(String CS){
        this.path_song = Paths.get(CS);
        this.uri = path_song.toUri();
        this.resolved_Path = uri.toString();

        //Careful.
        create_Obj(this.resolved_Path);
    }

    private void create_Obj(String resolved_Path){
        songAsMedia = new Media(resolved_Path);
        playMedia = new MediaPlayer(songAsMedia);
    }
    public void playSound(){
        playMedia.play();
    }

    public void pauseSound(){
        playMedia.stop();
    }

    public void loopSound(){
        playMedia.setCycleCount(MediaPlayer.INDEFINITE);
    }
}
