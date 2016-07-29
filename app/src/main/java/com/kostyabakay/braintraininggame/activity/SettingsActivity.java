package com.kostyabakay.braintraininggame.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kostyabakay.braintraininggame.R;
import com.kostyabakay.braintraininggame.fragment.SettingsFragment;

/**
 * Created by Kostya on 06.03.2016.
 * This class represents Activity for settings of the game.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
        getFragmentManager().beginTransaction()
                .replace(R.id.settings_content_frame, new SettingsFragment()).commit();
    }
}
