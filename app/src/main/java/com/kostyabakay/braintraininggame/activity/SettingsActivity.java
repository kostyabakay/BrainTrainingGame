package com.kostyabakay.braintraininggame.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.kostyabakay.braintraininggame.R;
import com.kostyabakay.braintraininggame.fragment.SettingsFragment;

/**
 * Created by Kostya on 06.03.2016.
 * This class represents Activity for settings of the game.
 */
public class SettingsActivity extends BaseActivity {

    public static void start(@NonNull Context context) {
        Intent starter = new Intent(context, SettingsActivity.class);
        context.startActivity(starter);
    }

    //region BaseActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
        replaceFragment(R.id.settings_content_frame, SettingsFragment.newInstance());
    }
    //endregion
}