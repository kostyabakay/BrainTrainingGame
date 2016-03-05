package com.kostyabakay.braintraininggame.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kostyabakay.braintraininggame.R;
import com.kostyabakay.braintraininggame.fragment.GameFragment;

/**
 * Created by Kostya on 04.03.2016.
 * This class represents Activity for game.
 */
public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initGameFragment();
    }

    private void initGameFragment() {
        GameFragment gameFragment = new GameFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_game, gameFragment);
        fragmentTransaction.commit();
    }
}
