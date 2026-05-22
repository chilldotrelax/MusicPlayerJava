package org.andy.musicplayer_java;

import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

class Progress_Bar extends Thread{
    private final double getDuration;
    private final ProgressBar progressBar;

    Progress_Bar(Duration duration, ProgressBar progress_Bar){
        this.getDuration = duration.toSeconds();
        this.progressBar = progress_Bar;

    }
    @Override
    public void run(){
        try {
            long startTime = (System.currentTimeMillis());
            long songDurationasLong = Double.valueOf(this.getDuration).longValue();
            long endTime = startTime + songDurationasLong;

            while (System.currentTimeMillis() < endTime) {
                double percentage = (1 - ((double) startTime) / ((double) endTime));
                progressBar.setProgress(percentage);
            }
        }
        catch (Exception e){
            System.out.println("Exception!");
        }
    }
}
