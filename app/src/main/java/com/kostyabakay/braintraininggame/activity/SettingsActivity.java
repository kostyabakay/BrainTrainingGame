package com.kostyabakay.braintraininggame.activity;

import android.os.Bundle;

import com.kostyabakay.braintraininggame.R;
import com.kostyabakay.braintraininggame.fragment.SettingsFragment;

/**
 * Created by Kostya on 06.03.2016.
 * This class represents Activity for settings of the game.
 */
public class SettingsActivity extends BaseActivity {

    //region BaseActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
        replaceFragment(R.id.settings_content_frame, SettingsFragment.newInstance());
    }
    //endregion
}