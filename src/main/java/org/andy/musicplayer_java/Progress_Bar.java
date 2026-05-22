package org.andy.musicplayer_java;

import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

class Progress_Bar extends Thread{
    private final double getDuration;
    private final ProgressBar progressBar;

    Progress_Bar(Duration duration, ProgressBar progress_Bar){
        this.getDuration = duration.toMillis();
        this.progressBar = progress_Bar;

    }
    @Override
    public void run(){
        try {
            long startTime = (System.currentTimeMillis());
            long songDurationsLong = Double.valueOf(this.getDuration).longValue();
            long endTime = startTime + songDurationsLong;

            while (System.currentTimeMillis() < endTime) {

                double percentage = 1-(((((double) endTime /1000)-((double) System.currentTimeMillis() /1000)))/((this.getDuration)/1000));
                //TODO Add delay to prevent excessive amounts of checks.
                progressBar.setProgress(percentage); //TODO Suboptimal implementation -- Fix later.
            }
        }
        catch (Exception e){
            System.out.println("Exception!");
        }
    }
}
