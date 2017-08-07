package com.codepath.apps.mysimpletweets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.models.Media;
import com.codepath.apps.mysimpletweets.models.Tweets;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by PJS on 8/4/2017.
 */

public class TweetArrayAdapter extends ArrayAdapter<Tweets> {
  private ImageView ivProfilImage;
    private ImageView ivPhoto;
    private TextView tvProfil;
    private TextView tvtweetbody;
    private TextView tvuser_screen_name;
    public TweetArrayAdapter(Context context, List<Tweets>tweets)
    {
        super(context,android.R.layout.simple_list_item_1,tweets);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Tweets tweet = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
        }
        tvtweetbody = (TextView) convertView.findViewById(R.id.tvtweetbody);
        tvuser_screen_name=(TextView)convertView.findViewById(R.id.tvUserScreenName);
        tvProfil = (TextView) convertView.findViewById(R.id.tvprofile);
        ivProfilImage= (ImageView) convertView.findViewById(R.id.ivProfilImage);
         ivPhoto=(ImageView)convertView.findViewById(R.id.ivPhoto) ;
        //888888//
        tvProfil.setText(tweet.getUser().getScreenName());
        tvtweetbody.setText(tweet.getBody());
        tvuser_screen_name.setText(tweet.getUser().getScreenName());
        ivProfilImage.setImageResource(android.R.color.transparent);
        ivPhoto.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfilImage);
        Media media=new Media();
        if (media.isPhoto()) {
            Picasso.with(getContext()).load(media.getMediaUrl()).into(ivPhoto);
            ivPhoto.setVisibility(View.VISIBLE);
        }
        return  convertView;
    }

}
