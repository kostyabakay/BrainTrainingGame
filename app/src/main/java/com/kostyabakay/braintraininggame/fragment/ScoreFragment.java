package com.kostyabakay.braintraininggame.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kostyabakay.braintraininggame.AppData;
import com.kostyabakay.braintraininggame.R;

/**
 * Created by Kostya on 04.03.2016.
 * This class represents fragment for score of finished game and GameActivity is the host of this fragment.
 */
public class ScoreFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
    }

    /**
     * Initialization view elements on the screen.
     */
    private void initView() {
        TextView correctAnswersTextView = (TextView) getActivity().findViewById(R.id.correct_answers_text_view);
        TextView scoreTextView = (TextView) getActivity().findViewById(R.id.score_text_view);

        correctAnswersTextView.setText(Integer.toString(AppData.correctAnswers) + " / " + (Integer.toString(AppData.gamesCount)));
        scoreTextView.setText(Long.toString(AppData.score));
    }
}
