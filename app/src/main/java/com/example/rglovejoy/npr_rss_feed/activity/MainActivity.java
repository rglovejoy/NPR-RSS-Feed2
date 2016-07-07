package com.example.rglovejoy.npr_rss_feed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rglovejoy.npr_rss_feed.R;
import com.example.rglovejoy.npr_rss_feed.adapter.FeedItemAdapter;
import com.example.rglovejoy.npr_rss_feed.event.BusProvider;
import com.example.rglovejoy.npr_rss_feed.event.LaunchWebEvent;
import com.example.rglovejoy.npr_rss_feed.event.RSSFeedResponseEvent;
import com.example.rglovejoy.npr_rss_feed.rss.RSSFeed;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Inject
    RSSFeed rssFeed;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private FeedItemAdapter feedItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        swipeRefreshLayout.setOnRefreshListener(this);

        feedItemAdapter = new FeedItemAdapter();

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(feedItemAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
        rssFeed.requestNewsFeed();
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onRSSFeedReceived(RSSFeedResponseEvent event) {
        feedItemAdapter.updateData(event.getFeed());
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Subscribe
    public void onLaunchWebStory(LaunchWebEvent event) {
        Log.i("MainActivity", "Launching " + event.getUrl());
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra(ArticleActivity.EXTRA_URL, event.getUrl());
        intent.putExtra(ArticleActivity.EXTRA_TITLE, event.getTitle());
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    // for SwipeRefreshLayout
    @Override
    public void onRefresh() {
        rssFeed.requestNewsFeed();
    }
}
