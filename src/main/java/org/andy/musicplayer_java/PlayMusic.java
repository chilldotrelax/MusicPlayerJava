package org.andy.musicplayer_java;

import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.*;


import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlayMusic {
    Path path_song;
    URI uri;
    String resolved_Path;
    Media songAsMedia = null;
    MediaPlayer playMedia = null;
    Thread progress = null;

    public PlayMusic(String CS){
        this.path_song = Paths.get(CS);
        this.uri = path_song.toUri();
        this.resolved_Path = uri.toString();

        create_Obj(this.resolved_Path);

    }

    private void create_Obj(String resolved_Path){
        songAsMedia = new Media(resolved_Path);
        playMedia = new MediaPlayer(songAsMedia);
    }
    public void playSound(ProgressBar passToProgress){
        if (playMedia.getCurrentTime().toSeconds() == 0) {
            progress = new Progress_Bar(playMedia.getTotalDuration(), passToProgress);
            progress.start();

        }
        else {
            progress = new Progress_Bar(playMedia.getCurrentTime(),passToProgress);
            progress.start();
        }

        playMedia.play();

    }

    public void pauseSound(ProgressBar passToSelf){
        passToSelf.getProgress();
        playMedia.stop();

    }

    public void loopSound(){
        playMedia.setCycleCount(MediaPlayer.INDEFINITE);
    }
    //TODO Later.
}
