package org.andy.musicplayer_java;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlayMusic {
    Path path_song;
    URI uri;
    String resolved_Path;
    Media songAsMedia = null;
    MediaPlayer playMedia = null;
    Progress_Bar progressBar = null;

    private final ProgressBar progressBar_Constructor;

    public PlayMusic(String CS, ProgressBar import_Prog){
        this.path_song = Paths.get(CS);
        this.uri = path_song.toUri();
        this.resolved_Path = uri.toString();
        this.progressBar_Constructor = import_Prog;

        create_Obj(this.resolved_Path);
    }
    private void create_Obj(String resolved_Path){
        songAsMedia = new Media(resolved_Path);
        playMedia = new MediaPlayer(songAsMedia);
    }
    public void playSound(){
        playMedia.play();
        if (progressBar == null){
            progressBar = new Progress_Bar(playMedia.getTotalDuration(), this.progressBar_Constructor,0);
        }
        else {
            progressBar = null;
            this.progressBar_Constructor.setDisable(false);
            progressBar = new Progress_Bar(playMedia.getCurrentTime(), this.progressBar_Constructor,this.progressBar_Constructor.getProgress());
        }
        progressBar.start();
    }
    public void pauseSound(){
        playMedia.pause();
        progressBar.interrupt();

        this.progressBar_Constructor.setDisable(true);
    }

    public void loopSound(){
        //TODO Later.
        playMedia.setCycleCount(MediaPlayer.INDEFINITE);
    }
}
