package com.example.rglovejoy.npr_rss_feed.module;

import android.app.Application;
import android.content.Context;

import com.example.rglovejoy.npr_rss_feed.application.NPRApplication;
import com.example.rglovejoy.npr_rss_feed.rss.RSSFeed;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class ApplicationModule {
    private final NPRApplication application;
    private static RSSFeed rssFeed;

    public ApplicationModule(NPRApplication application) {
        this.application = application;
    }

    @Provides
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    RSSFeed provideRSSFeed(@ApplicationContext Context context) {
        if (context != null && rssFeed == null) {
            rssFeed = new RSSFeed(context);
        } else {
            rssFeed = null;
        }
        return rssFeed;
    }
}
