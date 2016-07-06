package com.example.rglovejoy.npr_rss_feed.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rglovejoy.npr_rss_feed.R;
import com.example.rglovejoy.npr_rss_feed.event.BusProvider;
import com.example.rglovejoy.npr_rss_feed.event.LaunchWebEvent;
import com.example.rglovejoy.npr_rss_feed.model.FeedItem;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//    protected TextView title;
//    protected TextView summary;
//    protected TextView creator;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.summary)
    TextView summary;

    @BindView(R.id.creator)
    TextView creator;

    @BindString(R.string.created_by)
    String createdByString;

    protected FeedItem feedItem;

    public FeedItemHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

//        title = (TextView) itemView.findViewById(R.id.title);
//        summary = (TextView) itemView.findViewById(R.id.summary);
//        creator = (TextView) itemView.findViewById(R.id.creator);

        itemView.setOnClickListener(this);
        feedItem = null;
    }

    @Override
    public void onClick(View v) {
        if (feedItem != null) {
            BusProvider.getInstance().post(new LaunchWebEvent(feedItem.link, feedItem.title));
        }
    }

    public void setFeedItem(FeedItem item) {
        feedItem = item;
    }

    public void setTitleText(String titleString) {
        title.setText(titleString);
    }

    public void setSummaryText(String summaryString) {
        summary.setText(summaryString);
    }

    public void setCreatorText(String date, String name) {
        String creatorString = String.format(createdByString, date, name);
        creator.setText(creatorString);
    }
}
