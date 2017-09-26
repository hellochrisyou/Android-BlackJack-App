package com.example.chris.bj_final;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Intro extends AppCompatActivity {
    MediaPlayer intro, openSplash;
    Boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        intro = MediaPlayer.create(this, R.raw.splashintro);
        openSplash = MediaPlayer.create(this, R.raw.opensplash);
        intro.setLooping(true);
        intro.start();


    }

    public void onClickStart(View view) {
        //Run splash activity in a separate thread, wait 2 sec, and terminate
        if (clicked == false) {
            Thread timer = new Thread() {
                public void run() {
                    try {
                        clicked = true;
                        intro.stop();
                        openSplash.start();
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent myIntent = new Intent(Intro.this, Login.class);
                        startActivity(myIntent);
                    }
                }
            };
            timer.start();
        }
        else {
            return;
        }
    }
}