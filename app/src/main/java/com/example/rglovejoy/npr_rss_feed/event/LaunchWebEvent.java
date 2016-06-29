package com.example.rglovejoy.npr_rss_feed.event;

public class LaunchWebEvent {
    private String _url;
    private String _title;

    public LaunchWebEvent(String url, String title) {
        _url = url;
        _title = title;
    }

    public String getUrl() {
        return _url;
    }

    public String getTitle() {
        return _title;
    }
}
