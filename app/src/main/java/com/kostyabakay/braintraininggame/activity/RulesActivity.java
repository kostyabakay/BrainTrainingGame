package com.kostyabakay.braintraininggame.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.kostyabakay.braintraininggame.R;

/**
 * Created by Kostya on 08.03.2016.
 * This class represents Activity for rules of the game.
 */
public class RulesActivity extends BaseActivity {

    public static void start(@NonNull Context context) {
        Intent starter = new Intent(context, RulesActivity.class);
        context.startActivity(starter);
    }

    //region BaseActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
    }
    //endregion
}