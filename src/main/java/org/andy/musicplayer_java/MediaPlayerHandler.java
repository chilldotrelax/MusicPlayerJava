package org.andy.musicplayer_java;

import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MediaPlayerHandler {
    Path path_song;
    URI uri;
    String resolved_Path;
    Media songAsMedia = null;
    MediaPlayer playMedia = null;
    ProgressBarHandler progressBar = null;

    private final ProgressBar PROGRESSBARCONSTRUCTOR;

    public MediaPlayerHandler(String CS, ProgressBar import_Prog){
        this.path_song = Paths.get(CS);
        this.uri = path_song.toUri();
        this.resolved_Path = uri.toString();
        this.PROGRESSBARCONSTRUCTOR = import_Prog;

        create_Obj(this.resolved_Path);
    }
    private void create_Obj(String resolved_Path){
        songAsMedia = new Media(resolved_Path);
        playMedia = new MediaPlayer(songAsMedia);
    }
    public void playSound(){
        playMedia.play();
        if (progressBar == null){
            progressBar = new ProgressBarHandler(playMedia.getTotalDuration(),0,false, this.PROGRESSBARCONSTRUCTOR,0);
        }
        else {
            progressBar = null;
            this.PROGRESSBARCONSTRUCTOR.setDisable(false);
            progressBar = new ProgressBarHandler(playMedia.getTotalDuration(),playMedia.getCurrentTime().toMillis(),true, this.PROGRESSBARCONSTRUCTOR,this.PROGRESSBARCONSTRUCTOR.getProgress());
        }
        progressBar.start();
    }
    public void pauseSound(){
        playMedia.pause();
        progressBar.interrupt();
        this.PROGRESSBARCONSTRUCTOR.setDisable(true);
    }

    public void loopSound(){
        //TODO Later.
        playMedia.setCycleCount(MediaPlayer.INDEFINITE);
    }
}
