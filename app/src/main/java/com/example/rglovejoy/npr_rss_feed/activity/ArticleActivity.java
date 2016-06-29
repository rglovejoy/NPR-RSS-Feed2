package com.example.rglovejoy.npr_rss_feed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.webkit.WebView;

import com.example.rglovejoy.npr_rss_feed.R;

public class ArticleActivity extends BaseActivity {
    public static final String EXTRA_URL = "extra_url";
    public static final String EXTRA_TITLE = "extra_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Intent intent = getIntent();
        String url = intent.getStringExtra(EXTRA_URL);
        String title = intent.getStringExtra(EXTRA_TITLE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);

        WebView articleWebView = (WebView) findViewById(R.id.article_webview);
        articleWebView.getSettings().setJavaScriptEnabled(true);
        articleWebView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
