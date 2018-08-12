package com.kostyabakay.braintraininggame.activity;

import android.os.Bundle;

import com.kostyabakay.braintraininggame.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends BaseActivity {

    //region BaseActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
    }
    //endregion

    //region ButterKnife
    @OnClick(R.id.menu_play_button)
    void onPlayButtonClick() {
        GameActivity.start(this);
    }

    @OnClick(R.id.menu_rules_button)
    void onRulesButtonClick() {
        RulesActivity.start(this);
    }

    @OnClick(R.id.menu_author_button)
    void onAuthorButtonClick() {
        AuthorActivity.start(this);
    }

    @OnClick(R.id.menu_settings_button)
    void onSettingsButtonClick() {
        SettingsActivity.start(this);
    }

    @OnClick(R.id.menu_exit_button)
    void onExitButtonClick() {
        finish();
    }
    //endregion
}