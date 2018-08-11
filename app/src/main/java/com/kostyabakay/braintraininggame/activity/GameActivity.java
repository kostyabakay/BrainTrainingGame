package com.kostyabakay.braintraininggame.activity;

import android.os.Bundle;

import com.kostyabakay.braintraininggame.R;
import com.kostyabakay.braintraininggame.fragment.GameFragment;

/**
 * Created by Kostya on 04.03.2016.
 * This class represents Activity for game.
 */
public class GameActivity extends BaseActivity {

    //region BaseActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        replaceFragment(R.id.activity_game, new GameFragment());
    }
    //endregion
}