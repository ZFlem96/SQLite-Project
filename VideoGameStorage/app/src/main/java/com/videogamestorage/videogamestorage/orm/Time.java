package com.videogamestorage.videogamestorage.orm;

/**
 * Created by ZFleezy on 2/28/2017.
 */

public class Time {
    private int secs =0, mins=0, hours=0;
    private String time = hours+"."+mins+"."+secs;
    public String getTime(){
        return time;
    }
    public void getSecMinsHours(int secs, int mins, int hours){
        secs = this.secs;
        mins = this.mins;
        hours = this.hours;
        time = hours+"."+mins+"."+secs;
    }
    public void setTime(int secs, int mins, int hours){
        if(secs==60){
            this.secs = 0 ;
            mins++;
        }
        else {
            this.secs = secs;
        }
        if(mins==60){
            this.mins = 0 ;
            hours++;
        }
        else{
            this.mins = mins;
        }
        this.hours = hours;
        time = this.hours+"."+this.mins+"."+this.secs;
    }
    public Time(int secs, int mins, int hours){
        this.secs = secs;
        this.mins = mins;
        this.hours = hours;
        time = hours+"."+mins+"."+secs;
    }
    public Time(){

    }
}
