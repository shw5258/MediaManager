package com.example.light.mediamanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VideoPlayerActivity extends AppCompatActivity {

    public static final String VIDEO_PATH = "VIDEO_PATH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplay);
    }
}
