package com.kostyabakay.braintraininggame.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kostyabakay.braintraininggame.constant.SocialUrl;

public class BaseActivity extends AppCompatActivity {
    public void replaceFragment(@IdRes int containerViewId, @NonNull Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    public void openWebPage(@Nullable Uri webPageUri) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(webPageUri);
        startActivity(intent);
    }
}