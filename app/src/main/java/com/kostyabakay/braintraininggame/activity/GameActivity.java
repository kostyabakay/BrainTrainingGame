package com.kostyabakay.braintraininggame.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.kostyabakay.braintraininggame.R;
import com.kostyabakay.braintraininggame.fragment.GameFragment;

/**
 * Created by Kostya on 04.03.2016.
 * This class represents Activity for game.
 */
public class GameActivity extends BaseActivity {

    public static void start(@NonNull Context context) {
        Intent starter = new Intent(context, GameActivity.class);
        context.startActivity(starter);
    }

    //region BaseActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        replaceFragment(R.id.activity_game, GameFragment.newInstance());
    }
    //endregion
}