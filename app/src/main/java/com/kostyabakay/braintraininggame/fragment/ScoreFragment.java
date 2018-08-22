package com.kostyabakay.braintraininggame.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kostyabakay.braintraininggame.AppData;
import com.kostyabakay.braintraininggame.R;

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

    public static ScoreFragment newInstance() {
        Bundle args = new Bundle();
        ScoreFragment fragment = new ScoreFragment();
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
        mCorrectAnswersTextView.setText(Integer.toString(AppData.correctAnswers) + " / " + (Integer.toString(AppData.gamesCount)));
        mScoreTextView.setText(Long.toString(AppData.score));
    }
    //endregion
}