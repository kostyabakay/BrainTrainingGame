package com.kostyabakay.braintraininggame.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kostyabakay.braintraininggame.common.constant.Const;
import com.kostyabakay.braintraininggame.common.logger.L;
import com.kostyabakay.braintraininggame.databinding.FragmentScoreBinding;

public class ScoreFragment extends Fragment {

    private FragmentScoreBinding binding;

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
        binding = FragmentScoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showScore(getArguments());
    }
    //endregion

    //region Utility API
    private void showScore(@Nullable Bundle args) {
        if (args != null) {
            int gamesCount = args.getInt(Const.BundleKey.GAMES_COUNT);
            int correctAnswers = args.getInt(Const.BundleKey.CORRECT_ANSWERS);
            long score = args.getLong(Const.BundleKey.SCORE);
            binding.correctAnswersTextView.setText(formatCorrectAnswers(correctAnswers, gamesCount));
            binding.scoreTextView.setText(String.valueOf(score));
        } else {
            L.i("Bundle is null");
        }
    }

    @NonNull
    private String formatCorrectAnswers(int correctAnswers, int gamesCount) {
        return String.valueOf(correctAnswers) + Const.Symbol.SPACE + Const.Symbol.SLASH + Const.Symbol.SPACE + (String.valueOf(gamesCount));
    }
    //endregion
}