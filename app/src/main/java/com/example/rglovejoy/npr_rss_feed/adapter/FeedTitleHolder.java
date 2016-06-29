package com.example.rglovejoy.npr_rss_feed.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rglovejoy.npr_rss_feed.R;

public class FeedTitleHolder extends RecyclerView.ViewHolder {
    ImageView feedImage;
    TextView feedTitle;
    TextView feedDescription;

    public FeedTitleHolder(View itemView) {
        super(itemView);

        feedImage = (ImageView) itemView.findViewById(R.id.feed_image);
        feedTitle = (TextView) itemView.findViewById(R.id.feed_title);
        feedDescription = (TextView) itemView.findViewById(R.id.feed_description);
    }

}
