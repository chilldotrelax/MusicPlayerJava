package org.andy.musicplayer_java;

import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MediaPlayerHandler {
    private Path path_song;
    private URI uri;
    private String resolved_Path;
    private Media songAsMedia = null;
    private MediaPlayer playMedia = null;
    private ProgressBarHandler progressBar = null;

    private final ProgressBar progressbarconstructor;

    public MediaPlayerHandler(String CS, ProgressBar import_Prog){
        this.path_song = Paths.get(CS);
        this.uri = path_song.toUri();
        this.resolved_Path = uri.toString();
        this.progressbarconstructor = import_Prog;

        create_Obj(this.resolved_Path);
    }
    private void create_Obj(String resolved_Path){
        songAsMedia = new Media(resolved_Path);
        playMedia = new MediaPlayer(songAsMedia);
    }
    public void playSound(){
        playMedia.play();
        if (progressBar == null){
            progressBar = new ProgressBarHandler(playMedia.getTotalDuration(),0,false, this.progressbarconstructor,0);
        }
        else {
            progressBar = null;
            this.progressbarconstructor.setDisable(false);
            progressBar = new ProgressBarHandler(playMedia.getTotalDuration(),playMedia.getCurrentTime().toMillis(),true, this.progressbarconstructor,this.progressbarconstructor.getProgress());
        }
        progressBar.start();
    }
    public void pauseSound(){
        playMedia.pause();

        if (progressBar != null){
            progressBar.interrupt();
        }

        this.progressbarconstructor.setDisable(true);
    }

    public void loopSound(){
        //TODO Later.
        playMedia.setCycleCount(MediaPlayer.INDEFINITE);
    }
}
