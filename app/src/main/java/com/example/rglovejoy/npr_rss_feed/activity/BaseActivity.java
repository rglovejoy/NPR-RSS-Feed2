package com.example.rglovejoy.npr_rss_feed.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.rglovejoy.npr_rss_feed.application.NPRApplication;

public class BaseActivity extends AppCompatActivity {

    protected ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((NPRApplication) getApplication()).inject(this);

        actionBar = getSupportActionBar();
    }
}
