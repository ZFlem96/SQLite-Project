package com.videogamestorage.videogamestorage.dbobjects;

import com.videogamestorage.videogamestorage.orm.SugarRecord;

/**
 * Created by M on 2/26/2017.
 */

public class Game extends SugarRecord {

    private String videoGame;
    private boolean complete;
    private double progress;
    private double timeSpentPlaying;
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

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        String completion = (complete) ? "[Complete]" : "[Incomplete";
        if (!complete && progress < 100) {
            completion += " " + progress + "%]";
        }
        return videoGame + " " + completion;
    }
}
