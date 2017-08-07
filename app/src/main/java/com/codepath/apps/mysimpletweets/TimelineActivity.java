package com.codepath.apps.mysimpletweets;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.codepath.apps.mysimpletweets.models.Tweets;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class TimelineActivity extends AppCompatActivity {
   private TwitterClient client;
    private ArrayList<Tweets>tweet;
    private ListView lvTweets;
    private TweetArrayAdapter AdapterTweet;
    private ComposeTweetFragment composeTweetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvTweets=(ListView)findViewById(R.id.lvTweets);
        tweet=new ArrayList<>();
        AdapterTweet=new TweetArrayAdapter(this,tweet);
        lvTweets.setAdapter(AdapterTweet);
        client=new TwitterApplication().getTwitterClient();
        populateTimeline();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                composeTweetFragment = new ComposeTweetFragment();
                composeTweetFragment.show(fragmentManager, "COMPOSE_TWEET");
                composeTweetFragment.setListener(TimelineActivity.this);
            }
        });
    }

    public  void populateTimeline()
    {
        client.getHomeTimeline(1, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                Log.d("DEBUG", "timeline: " + jsonArray.toString());
                ArrayList<Tweets> tweets = Tweets.fromJSONArray(jsonArray);
                AdapterTweet.addAll(tweets);
                // Load json array into model classes
            }
        });
/*        client.getHomeTimeline(1,new JsonHttpResponseHandler()
        {
           @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
              //  Log.d("DEBUG", "timeline: " + jsonArray.toString());
               // AdapterTweet.addAll(Tweet.fromJSON(jsonArray));
                JSONArray JSONResults=null;
                try {
                   // JSONResults=jsonArray.getJSONObje
                    tweet.addAll(Tweet.fromJson(jsonArray));
                    AdapterTweet.notifyDataSetChanged();
                    Log.d("DEBUG",tweet.toString());
                }catch (JSONException e){e.printStackTrace();
                }
            }
        });*/
      /*  client.getHomeTimeline(1,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                JSONArray TweetJson=null;
                try {
                    TweetJson=jsonObject.getJSONArray("tweets");
                    tweet.addAll(Tweet.fromJson(TweetJson));
                    AdapterTweet.notifyDataSetChanged();
                    Log.d("DEBUG",tweet.toString());
                }catch (JSONException e){e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });*/
    }
}
