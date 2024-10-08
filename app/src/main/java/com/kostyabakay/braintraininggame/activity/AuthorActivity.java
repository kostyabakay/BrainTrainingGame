package com.kostyabakay.braintraininggame.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.kostyabakay.braintraininggame.common.constant.SocialUrl;
import com.kostyabakay.braintraininggame.databinding.ActivityAuthorBinding;

public class AuthorActivity extends BaseActivity {

    private ActivityAuthorBinding binding;

    public static void start(@NonNull Context context) {
        Intent starter = new Intent(context, AuthorActivity.class);
        context.startActivity(starter);
    }

    //region BaseActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupClickListeners();
    }
    //endregion

    private void setupClickListeners() {
        binding.twitterImage.setOnClickListener(view -> openWebPage(Uri.parse(SocialUrl.TWITTER)));
        binding.instagramImage.setOnClickListener(view -> openWebPage(Uri.parse(SocialUrl.INSTAGRAM)));
        binding.githubImage.setOnClickListener(view -> openWebPage(Uri.parse(SocialUrl.GITHUB)));
        binding.linkedinImage.setOnClickListener(view -> openWebPage(Uri.parse(SocialUrl.LINKED_IN)));
    }
}