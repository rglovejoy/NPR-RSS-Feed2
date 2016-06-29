package com.example.rglovejoy.npr_rss_feed.event;

import com.example.rglovejoy.npr_rss_feed.model.Feed;

public class RSSFeedResponseEvent {
    private Feed _feed;

    public RSSFeedResponseEvent(Feed feed) {
        _feed = feed;
    }

    public Feed getFeed() {
        return _feed;
    }
}
