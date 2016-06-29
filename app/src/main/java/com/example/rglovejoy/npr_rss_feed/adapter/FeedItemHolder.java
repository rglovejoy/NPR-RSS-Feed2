package com.example.rglovejoy.npr_rss_feed.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rglovejoy.npr_rss_feed.R;
import com.example.rglovejoy.npr_rss_feed.event.BusProvider;
import com.example.rglovejoy.npr_rss_feed.event.LaunchWebEvent;
import com.example.rglovejoy.npr_rss_feed.model.FeedItem;

public class FeedItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected TextView title;
    protected TextView summary;
    protected TextView creator;

    protected FeedItem feedItem;

    public FeedItemHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.title);
        summary = (TextView) itemView.findViewById(R.id.summary);
        creator = (TextView) itemView.findViewById(R.id.creator);

        itemView.setOnClickListener(this);
        feedItem = null;
    }

    public void setFeedItem(FeedItem item) {
        feedItem = item;
    }

    @Override
    public void onClick(View v) {
        if (feedItem != null) {

            BusProvider.getInstance().post(new LaunchWebEvent(feedItem.link, feedItem.title));
        }
    }

}
