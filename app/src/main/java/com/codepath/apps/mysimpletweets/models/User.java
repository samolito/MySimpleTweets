package com.codepath.apps.mysimpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PJS on 8/6/2017.
 */

public class User {
    //List attributes
    private  String name;
    private  Long uid;
    private  String screenName;
    private  String profileImageUrl;
    public String getName() {
        return name;
    }

    public Long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }



    public static User fromJSON(JSONObject json)
    {
        User u=new User();
try {
    u.name=json.getString("name");
    u.uid=json.getLong("id");
    u.screenName=json.getString("screen_name");
    u.profileImageUrl=json.getString("profile_image_url");
}catch (JSONException e)
{e.printStackTrace();}

        return u;
    }
}
