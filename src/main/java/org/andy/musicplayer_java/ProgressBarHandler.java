package org.andy.musicplayer_java;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

class ProgressBarHandler extends Thread{
    private final double getDuration;
    private final double getOffsetDuration;
    private final ProgressBar getProgressBar;
    private final double getOffsetPercentage;
    private final boolean offsetDurationFlag;

    ProgressBarHandler(Duration full_duration, double offset_duration, boolean isOffset, ProgressBar progBar, double Offset){
        this.getDuration = full_duration.toMillis();
        this.getProgressBar = progBar;
        this.getOffsetPercentage = Offset;
        this.getOffsetDuration = offset_duration;
        this.offsetDurationFlag = isOffset;
    }
    @Override
    public void run(){
        try {
            if (!offsetDurationFlag){
                long startTime = (System.currentTimeMillis());
                long songDurationsLong = Double.valueOf(this.getDuration).longValue();
                long endTime = startTime + songDurationsLong;
                while(System.currentTimeMillis() < endTime){
                    double percentage = 1-(((((double) endTime /1000)-((double) System.currentTimeMillis() /1000)))/((this.getDuration)/1000));
                    sleep(150);
                    Platform.runLater(()->{
                        this.getProgressBar.setProgress((percentage));
                    });
                }
            }
            if (offsetDurationFlag){
                long startTime = System.currentTimeMillis();
                long timeRemaining = Double.valueOf(this.getDuration).longValue() - Double.valueOf(this.getOffsetDuration).longValue();
                long endTime = startTime + timeRemaining;

                while(System.currentTimeMillis() < endTime){
                    double percentage = 1-(((((double) endTime /1000)-((double) System.currentTimeMillis() /1000)))/((timeRemaining)/1000));
                    sleep(150);
                    Platform.runLater(()->{
                        this.getProgressBar.setProgress((this.getOffsetPercentage + percentage));
                    });
                }

            }
        }
        catch (Exception e){
            System.out.println("Something went wrong!");

        }
    }

    }

