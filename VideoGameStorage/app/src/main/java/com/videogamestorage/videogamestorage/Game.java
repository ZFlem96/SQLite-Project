package com.videogamestorage.videogamestorage;

/**
 * Created by ZFleezy on 2/21/2017.
 */

public class Game {
    private int id;
    private String name;
    private String releaseYear;

    public Game(){}

    public Game(int id, String name, String releaseYear){
        this.id=id;
        this.name=name;
        this.releaseYear=releaseYear;
    }
    public Game(String name, String releaseYear){
        this.name=name;
        this.releaseYear=releaseYear;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }
    public int getId() {
        return id;
    }
    public String getReleaseYear() {
        return releaseYear;
    }
    public String getName() {
        return name;
    }
}
