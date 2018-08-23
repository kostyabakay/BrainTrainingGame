package com.kostyabakay.braintraininggame.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kostyabakay.braintraininggame.R;
import com.kostyabakay.braintraininggame.common.constant.Const;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kostya on 04.03.2016.
 * This class represents fragment for score of finished game and GameActivity is the host of this fragment.
 */
public class ScoreFragment extends Fragment {

    //region ButterKnife BindView
    @BindView(R.id.correct_answers_text_view)
    TextView mCorrectAnswersTextView;

    @BindView(R.id.score_text_view)
    TextView mScoreTextView;
    //endregion

    public static ScoreFragment newInstance(int gamesCount, int correctAnswers, long score) {
        Bundle args = new Bundle();
        ScoreFragment fragment = new ScoreFragment();
        args.putInt(Const.BundleKey.GAMES_COUNT, gamesCount);
        args.putInt(Const.BundleKey.CORRECT_ANSWERS, correctAnswers);
        args.putLong(Const.BundleKey.SCORE, score);
        fragment.setArguments(args);
        return fragment;
    }

    //region Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            int gamesCount = args.getInt(Const.BundleKey.GAMES_COUNT);
            int correctAnswers = args.getInt(Const.BundleKey.CORRECT_ANSWERS);
            int score = args.getInt(Const.BundleKey.SCORE);
            mCorrectAnswersTextView.setText(Integer.toString(correctAnswers) + " / " + (Integer.toString(gamesCount)));
            mScoreTextView.setText(Long.toString(score));
        }
    }
    //endregion
}