package com.example.rglovejoy.npr_rss_feed.rss;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rglovejoy.npr_rss_feed.event.BusProvider;
import com.example.rglovejoy.npr_rss_feed.event.RSSFeedResponseEvent;
import com.example.rglovejoy.npr_rss_feed.model.Feed;

public class RSSFeed {

    public static final String BASE_URL = "http://www.npr.org/rss/rss.php?id=";
    public static final String NEWS = "1001";

    private RequestQueue requestQueue;

    public RSSFeed(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void requestNewsFeed() {
        String url = BASE_URL + NEWS;
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Feed feed = XMLParser.parseXML(response);
                BusProvider.getInstance().post(new RSSFeedResponseEvent(feed));
                Log.i("RSSFeed", feed.toString());
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("RSSFeed", "Oops! Could not get response for news.");
            }
        };

        sendRequest(url, responseListener, errorListener);
    }

    private void sendRequest(String url, Response.Listener responseListener,
                             Response.ErrorListener errorListener) {
        StringRequest request =
                new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(request);
    }
}

