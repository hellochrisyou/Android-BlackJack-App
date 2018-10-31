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
        this.SetName(name);
        this.SetHighScore(highScore);
    }

    public void SetName(String name) {
        this.name = name;
    }

    public void SetHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String GetName() {
        return name;
    }

    public int GetHighScore() {
        return highScore;
    }
}