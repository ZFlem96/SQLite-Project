package com.videogamestorage.videogamestorage.dbobjects;

import com.videogamestorage.videogamestorage.orm.SugarRecord;
import com.videogamestorage.videogamestorage.orm.Time;
import com.videogamestorage.videogamestorage.orm.annotation.Ignore;

/**
 * Created by M on 2/26/2017.
 */

public class Game extends SugarRecord {

    private String videoGame;
    private boolean complete;
    private double progress;
    private double timeSpentPlaying;
    @Ignore
    private Time timeNumber = new Time();
    private String time;
    private String releaseYear;



    public Game(){}

    public String getVideoGame() {
        return videoGame;
    }

    public void setVideoGame(String videoGame) {
        this.videoGame = videoGame;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public double getTimeSpentPlaying() {
        return timeSpentPlaying;
    }

    public void setTimeSpentPlaying(double timeSpentPlaying) {
        this.timeSpentPlaying = timeSpentPlaying;
    }
    public Time getSecMinsHours(int secs, int mins, int hours, Time time){
   time.getSecMinsHours(secs, mins, hours);
        return time;
    }
    public Time getSecMinsHours(int secs, int mins, int hours){
        timeNumber.getSecMinsHours(secs, mins, hours);
        return timeNumber;
    }
    public Time getTimeNumber(){
        return timeNumber;
    }
    public void setTime(int secs, int mins, int hours, Time time){
        time.setTime(secs, mins, hours);
    }
    public void setTime(int secs, int mins, int hours){
        timeNumber.setTime(secs, mins, hours);
        time = getTimeNumber().getTime();
    }
    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String toString(Time time) {
        String completion = (complete) ? "[Complete]" : "[Incomplete";
        if (!complete && progress <  100) {
            completion += " " + progress + "%] Time Spent: "+ time;
        }
        return videoGame + " " + completion;
    }
    public String toString() {
        String completion = (complete) ? "[Complete]" : "[Incomplete";
        if (!complete && progress <  100) {
            completion += " " + progress + "%] Time Spent: "+ time;
        }
        return videoGame + " " + completion;
    }
}
