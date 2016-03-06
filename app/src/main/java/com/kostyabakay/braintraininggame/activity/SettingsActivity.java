package com.kostyabakay.braintraininggame.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.kostyabakay.braintraininggame.AppData;
import com.kostyabakay.braintraininggame.R;

/**
 * Created by Kostya on 06.03.2016.
 * This class represents Activity for settings of the game.
 */
public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton easyLevelRadioButton, mediumLevelRadioButton, hardLevelRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
    }

    /**
     * Initialization view elements on the screen.
     */
    private void initView() {
        easyLevelRadioButton = (RadioButton) findViewById(R.id.easy_level_radio_button);
        mediumLevelRadioButton = (RadioButton) findViewById(R.id.medium_level_radio_button);
        hardLevelRadioButton = (RadioButton) findViewById(R.id.hard_level_radio_button);

        easyLevelRadioButton.setOnClickListener(this);
        mediumLevelRadioButton.setOnClickListener(this);
        hardLevelRadioButton.setOnClickListener(this);

        checkDifficultyLevel();
    }

    /**
     * Checks actual difficulty level from AppData class and sets checked corresponding radio button.
     */
    private void checkDifficultyLevel() {
        if (AppData.easyLevel && !AppData.mediumLevel && !AppData.hardLevel) {
            easyLevelRadioButton.setChecked(true);
        } else if (!AppData.easyLevel && AppData.mediumLevel && !AppData.hardLevel) {
            mediumLevelRadioButton.setChecked(true);
        } else if (!AppData.easyLevel && !AppData.mediumLevel && AppData.hardLevel) {
            hardLevelRadioButton.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.easy_level_radio_button:
                easyLevelRadioButton.setChecked(true);
                AppData.easyLevel = true;
                AppData.mediumLevel = false;
                AppData.hardLevel = false;
                break;

            case R.id.medium_level_radio_button:
                mediumLevelRadioButton.setChecked(true);
                AppData.easyLevel = false;
                AppData.mediumLevel = true;
                AppData.hardLevel = false;
                break;

            case R.id.hard_level_radio_button:
                hardLevelRadioButton.setChecked(true);
                AppData.easyLevel = false;
                AppData.mediumLevel = false;
                AppData.hardLevel = true;
                break;
        }
    }
}
