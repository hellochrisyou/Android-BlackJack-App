package com.example.chris.bj_final;

/**
 * Created by chris on 9/9/2017.
 */

public class Card {
    int value;
    String faceValue;
    boolean softAce = false;

    int GetValue() {
        return this.value;
    }

    void SetValue(int value) {
        //faceCards
        if (value == 11) {
            this.value = 10;
            this.faceValue = "J";
        }
        if (value == 12) {
            this.value = 10;
            this.faceValue = "Q";
        }
        if (value == 13) {
            this.value = 10;
            this.faceValue = "K";
        }
        //ace
        else if (value == 1) {
            softAce = true;
            this.value = 1;
        }
        //otherwise
        else {
            this.value = value;
        }
    }
}