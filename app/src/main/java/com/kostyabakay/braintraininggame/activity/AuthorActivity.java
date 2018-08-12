package com.kostyabakay.braintraininggame.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.kostyabakay.braintraininggame.R;
import com.kostyabakay.braintraininggame.constant.SocialUrl;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthorActivity extends BaseActivity {

    public static void start(@NonNull Context context) {
        Intent starter = new Intent(context, AuthorActivity.class);
        context.startActivity(starter);
    }

    //region BaseActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        ButterKnife.bind(this);
    }
    //endregion

    //region ButterKnife
    @OnClick(R.id.twitter_image)
    void onTwitterClick() {
        openWebPage(Uri.parse(SocialUrl.TWITTER));
    }

    @OnClick(R.id.instagram_image)
    void onInstagramClick() {
        openWebPage(Uri.parse(SocialUrl.INSTAGRAM));
    }

    @OnClick(R.id.github_image)
    void onGitHubClick() {
        openWebPage(Uri.parse(SocialUrl.GITHUB));
    }

    @OnClick(R.id.linkedin_image)
    void onLinkedInClick() {
        openWebPage(Uri.parse(SocialUrl.LINKED_IN));
    }
    //endregion
}