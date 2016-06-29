package com.example.rglovejoy.npr_rss_feed.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rglovejoy.npr_rss_feed.R;
import com.example.rglovejoy.npr_rss_feed.model.Feed;
import com.example.rglovejoy.npr_rss_feed.model.FeedItem;
import com.squareup.picasso.Picasso;

public class FeedItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TITLE_ITEM = 0;
    private static final int FEED_ITEM = 1;

    private Feed _feed = null;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TITLE_ITEM;
        } else {
            return FEED_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TITLE_ITEM) {
            View titleView =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.feedtitle_card_layout,
                            parent, false);
            return new FeedTitleHolder(titleView);
        } else {

            View itemView =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.feeditem_card_layout,
                            parent, false);
            return new FeedItemHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            FeedTitleHolder feedTitleHolder = (FeedTitleHolder) holder;
            if (_feed != null) {
                feedTitleHolder.feedTitle.setText(_feed.title);
                feedTitleHolder.feedDescription.setText(_feed.description);

                Picasso.with(feedTitleHolder
                        .itemView.getContext())
                        .load(_feed.image.url).
                        into(feedTitleHolder.feedImage);
            }
        } else {
            if (_feed != null) {
                FeedItem item = _feed.feedItems.get(position - 1);
                FeedItemHolder feedItemHolder = (FeedItemHolder) holder;
                feedItemHolder.setFeedItem(item);
                feedItemHolder.title.setText(item.title);
                feedItemHolder.summary.setText(item.description);
                feedItemHolder.creator.setText("Updated " + item.pubDate + " by " + item.creator);
            }
        }
    }

    @Override
    public int getItemCount() {
        int count = 1;
        if (_feed != null) {
            count += _feed.feedItems.size();
        }
        return count;
    }

    public void updateData(Feed feed) {
        _feed = feed;
        notifyDataSetChanged();
    }

}
