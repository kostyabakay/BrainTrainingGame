package com.kostyabakay.braintraininggame.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

    private void initView() {
        Button newGameBtn = (Button) findViewById(R.id.menu_play_button);
        Button rulesBtn = (Button) findViewById(R.id.menu_rules_button);
        Button authorBtn = (Button) findViewById(R.id.menu_author_button);
        Button settingsBtn = (Button) findViewById(R.id.menu_settings_button);
        Button exitBtn = (Button) findViewById(R.id.menu_exit_button);

        newGameBtn.setOnClickListener(this);
        rulesBtn.setOnClickListener(this);
        authorBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
        exitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_play_button:
                break;

            case R.id.menu_rules_button:
                break;

            case R.id.menu_author_button:
                break;

            case R.id.menu_settings_button:
                break;

            case R.id.menu_exit_button:
                finish(); // Exit from the app
                break;
        }
    }
}
