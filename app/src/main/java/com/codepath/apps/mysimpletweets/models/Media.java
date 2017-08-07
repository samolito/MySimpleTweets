package com.codepath.apps.mysimpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PJS on 8/6/2017.
 */

public class Media {
    private String type;
    private String mediaUrl;

    public String getType() {
        return type;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }
    public boolean isPhoto() {
        return "photo".equals(type);
    }
    public static Media fromJSON(JSONObject json)
    {
        Media m = new Media();

        try {
            m.type=json.getString("type");
            m.mediaUrl=json.getString("media_url");
    }catch (JSONException e)
        {e.printStackTrace();}
        return  m;
    }
}
