package com.example.rglovejoy.npr_rss_feed.model;

public class FeedItem {

    public String title;
    public String description;
    public String pubDate;
    public String link;
    public String guid;
    public String encodedContent;
    public String creator;

    @Override
    public String toString() {
        return String.format("FeedItem [title: %s, description: %s, publication date: %s]",
                title, description, pubDate);
    }
}
