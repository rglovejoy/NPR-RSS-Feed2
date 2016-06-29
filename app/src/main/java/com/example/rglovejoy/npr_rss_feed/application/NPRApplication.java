package com.example.rglovejoy.npr_rss_feed.application;

import android.app.Application;

import com.example.rglovejoy.npr_rss_feed.module.ApplicationModule;
import com.example.rglovejoy.npr_rss_feed.module.CommonModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

public class NPRApplication extends Application {

    public ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.asList(new ApplicationModule(this), new CommonModule());
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }
}
