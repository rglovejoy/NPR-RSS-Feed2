package com.example.rglovejoy.npr_rss_feed.activity;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends BaseActivity {

    @Inject
    RSSFeed rssFeed;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private FeedItemAdapter feedItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onRSSFeedReceived(RSSFeedResponseEvent event) {
        feedItemAdapter.updateData(event.getFeed());
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

}
