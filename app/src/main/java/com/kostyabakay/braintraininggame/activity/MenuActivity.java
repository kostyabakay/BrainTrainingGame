package com.kostyabakay.braintraininggame.activity;

import android.os.Bundle;

import com.kostyabakay.braintraininggame.databinding.ActivityMenuBinding;

public class MenuActivity extends BaseActivity {

    private ActivityMenuBinding binding;

    //region BaseActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupClickListeners();
    }
    //endregion

    private void setupClickListeners() {
        binding.menuPlayButton.setOnClickListener(v -> GameActivity.start(this));
        binding.menuRulesButton.setOnClickListener(v -> RulesActivity.start(this));
        binding.menuAuthorButton.setOnClickListener(v -> AuthorActivity.start(this));
        binding.menuSettingsButton.setOnClickListener(v -> SettingsActivity.start(this));
        binding.menuExitButton.setOnClickListener(v -> finish());
    }
}