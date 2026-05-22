package org.andy.musicplayer_java;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;
import java.time.*;

class Progress_Bar extends Thread{
    private final double getDuration;
    private ProgressBar progBar;
    private double offset;
    Progress_Bar(Duration duration, ProgressBar progBar, double Offset){
        this.getDuration = duration.toMillis();
        this.progBar = progBar;
        this.offset = Offset;
    }

    @Override
    public void run(){
        try {
            long startTime = (System.currentTimeMillis());
            long songDurationsLong = Double.valueOf(this.getDuration).longValue();
            long endTime = startTime + songDurationsLong;

            while (System.currentTimeMillis() < endTime) {
                double percentage = 1-(((((double) endTime /1000)-((double) System.currentTimeMillis() /1000)))/((this.getDuration)/1000));
                sleep(200);
                Platform.runLater(()->{
                    this.progBar.setProgress(offset + percentage);
                });
            }
        }
        catch (Exception e){
            System.out.println("Something went wrong!");

        }
    }

    }

