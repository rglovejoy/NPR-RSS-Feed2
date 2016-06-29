package com.example.rglovejoy.npr_rss_feed.model;

import java.util.ArrayList;

public class Feed {
    public String title;
    public String link;
    public String description;
    public String language;
    public String copyright;
    public String generator;
    public String lastBuildDate;
    public Image image;
    public ArrayList<FeedItem> feedItems;

    public Feed() {
        feedItems = new ArrayList<>();
        image = new Image();
    }

    @Override
    public String toString() {
        String outString = String.format("Feed [title = %s, description = %s, last build date = %s\n",
                title, description, lastBuildDate);


        for (FeedItem item: feedItems) {
            outString += item.toString() + "\n";
        }
        return outString;
    }
}
