package com.example.rglovejoy.npr_rss_feed.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rglovejoy.npr_rss_feed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedTitleHolder extends RecyclerView.ViewHolder {
//    ImageView feedImage;
//    TextView feedTitle;
//    TextView feedDescription;

    @BindView(R.id.feed_image)
    ImageView feedImage;

    @BindView(R.id.feed_title)
    TextView feedTitle;

    @BindView(R.id.feed_description)
    TextView feedDescription;

    public FeedTitleHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);


//        feedImage = (ImageView) itemView.findViewById(R.id.feed_image);
//        feedTitle = (TextView) itemView.findViewById(R.id.feed_title);
//        feedDescription = (TextView) itemView.findViewById(R.id.feed_description);
    }

    public ImageView getTitleImageView() {
        return feedImage;
    }

    public void setFeedTitleText(String titleString) {
        feedTitle.setText(titleString);
    }

    public void setFeedDescriptionText(String descriptionString) {
        feedDescription.setText(descriptionString);
    }
}
