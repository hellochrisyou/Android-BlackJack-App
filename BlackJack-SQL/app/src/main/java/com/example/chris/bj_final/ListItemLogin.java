package com.example.chris.bj_final;

/**
 * Created by chris on 9/9/2017.
 */

public class ListItemLogin {
    public String name;
    public int highScore;

    public ListItemLogin() {
        //empty
    }

    public ListItemLogin(String name, int highScore) {
        this.setName(name);
        this.setHighScore(highScore);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getName() {
        return name;
    }

    public int getHighScore() {
        return highScore;
    }
}