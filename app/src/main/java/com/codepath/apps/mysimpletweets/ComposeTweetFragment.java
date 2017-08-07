package com.codepath.apps.mysimpletweets;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.models.User;
import com.squareup.picasso.Picasso;

/**
 * Created by PJS on 8/7/2017.
 */

public class ComposeTweetFragment extends DialogFragment {
    private static final int MAX_CHARS = 140;
    private static final String TAG = "COMPOSE_TWEET";
    private TwitterClient client;
    private TextView tvCharsLeft;
    private EditText etTweetText;
    private Long inReplyToStatusId = null;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_compose_tweet, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setupUserDetails(view);
        setupCharacterLimit(view);
        setupTweetButton(view);
        return view;
    }
    private void setupUserDetails(final View view) {
        client = TwitterApplication.getTwitterClient();
        client.getP (new TwitterClient. {
            @Override
            public void onSuccess(User user) {
                TextView tvUserName = (TextView) view.findViewById(R.id.tvUserName);
                TextView tvUserScreenName = (TextView) view.findViewById(R.id.tvUserScreenName);
                ImageView ivUserPhoto = (ImageView) view.findViewById(R.id.ivUserPhoto);
                tvUserName.setText(user.getName());
                tvUserScreenName.setText("@" + user.getScreenName());
                Picasso.with(getContext()).load(user.getProfileImageUrl()).into(ivUserPhoto);
            }

            @Override
            public void onFailure(Throwable error) {
                Log.d(TAG, "Failed to retrieve user's details", error);
            }
        });
    }

}
