package com.kostyabakay.braintraininggame.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kostyabakay.braintraininggame.R;

/**
 * Created by Kostya on 04.03.2016.
 * This class represents Activity for game menu. This is the main Activity of application.
 */
public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initView();
    }

    /**
     * Initialization view elements on the screen.
     */
    private void initView() {
        findViewById(R.id.menu_play_button).setOnClickListener(this);
        findViewById(R.id.menu_rules_button).setOnClickListener(this);
        findViewById(R.id.menu_author_button).setOnClickListener(this);
        findViewById(R.id.menu_settings_button).setOnClickListener(this);
        findViewById(R.id.menu_exit_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_play_button:
                GameActivity.start(this);
                break;

            case R.id.menu_rules_button:
                RulesActivity.start(this);
                break;

            case R.id.menu_author_button:
                AuthorActivity.start(this);
                break;

            case R.id.menu_settings_button:
                SettingsActivity.start(this);
                break;

            case R.id.menu_exit_button:
                finish(); // Exit from the app
                break;
        }
    }
}