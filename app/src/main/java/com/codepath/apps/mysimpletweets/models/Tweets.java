package com.codepath.apps.mysimpletweets.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by PJS on 8/6/2017.
 */

public class Tweets {
    private Long userId;
    private User user;
    private String created_at;
    private String body;
    private Media media;

    public Long getUserId() {
        return userId;
    }

    public User getUser() {
        return user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getBody() {
        return body;
    }

    public Media getMedia() {
        return media;
    }

    // Parse model from JSON
    public static Tweets fromJson(JSONObject object){
        Tweets tweet=new Tweets();

        try {
            tweet.userId = object.getLong("id");
            tweet.created_at = object.getString("created_at");
            tweet.body = object.getString("text");
            tweet.user=User.fromJSON(object.getJSONObject("user"));
            tweet.media=Media.fromJSON(object.getJSONObject("media_url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  tweet;
    }
    public static ArrayList<Tweets> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Tweets> tweets = new ArrayList<>();

        for (int i=0; i < jsonArray.length(); i++) {
            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweets tweet=Tweets.fromJson(tweetJson);
                if(tweet!=null)
                {tweets.add(tweet);}
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }

        }

        return tweets;
    }
}
