package com.kostyabakay.braintraininggame.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.kostyabakay.braintraininggame.R;

/**
 * Created by Kostya on 08.03.2016.
 * This class represents Activity with information about author of this application.
 */
public class AuthorActivity extends BaseActivity {
    private static final String TWITTER_URL = "https://twitter.com/Kostya_Bakay";
    private static final String INSTAGRAM_URL = "https://instagram.com/kostya_bakay";
    private static final String GITHUB_URL = "https://github.com/kostyabak";
    private static final String LINKEDIN_URL = "https://ua.linkedin.com/in/kostyabakay";

    public static void start(@NonNull Context context) {
        Intent starter = new Intent(context, AuthorActivity.class);
        context.startActivity(starter);
    }

    //region BaseActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        initView();
    }
    //endregion

    /**
     * Initialization view elements on the screen.
     */
    private void initView() {
        ImageView twitterImageView = (ImageView) findViewById(R.id.twitter_image);
        ImageView instagramImageView = (ImageView) findViewById(R.id.instagram_image);
        ImageView githubImageView = (ImageView) findViewById(R.id.github_image);
        ImageView linkedinImageView = (ImageView) findViewById(R.id.linkedin_image);

        twitterImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent twitterIntent = new Intent();
                twitterIntent.setAction(Intent.ACTION_VIEW);
                twitterIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                twitterIntent.setData(Uri.parse(TWITTER_URL));
                startActivity(twitterIntent);
            }
        });

        instagramImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent instagramIntent = new Intent();
                instagramIntent.setAction(Intent.ACTION_VIEW);
                instagramIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                instagramIntent.setData(Uri.parse(INSTAGRAM_URL));
                startActivity(instagramIntent);
            }
        });

        githubImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent githubIntent = new Intent();
                githubIntent.setAction(Intent.ACTION_VIEW);
                githubIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                githubIntent.setData(Uri.parse(GITHUB_URL));
                startActivity(githubIntent);
            }
        });

        linkedinImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent linkedinIntent = new Intent();
                linkedinIntent.setAction(Intent.ACTION_VIEW);
                linkedinIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                linkedinIntent.setData(Uri.parse(LINKEDIN_URL));
                startActivity(linkedinIntent);
            }
        });
    }
}
