package com.example.rglovejoy.npr_rss_feed.model;

public class Image {
    public String url;
    public String title;
    public String link;

    @Override
    public String toString() {
        return String.format("Image [title: %s, url: %s, link: %s]", title, url, link);
    }
}
