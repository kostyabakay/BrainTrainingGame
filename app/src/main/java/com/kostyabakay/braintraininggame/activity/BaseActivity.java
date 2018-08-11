package com.kostyabakay.braintraininggame.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    public void replaceFragment(@IdRes int containerViewId, @NonNull Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }
}