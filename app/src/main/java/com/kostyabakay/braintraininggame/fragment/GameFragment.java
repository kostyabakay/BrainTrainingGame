package com.kostyabakay.braintraininggame.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kostyabakay.braintraininggame.R;
import com.kostyabakay.braintraininggame.common.def.Difficulty;
import com.kostyabakay.braintraininggame.common.def.Digit;
import com.kostyabakay.braintraininggame.math.expression.Expression;
import com.kostyabakay.braintraininggame.math.expression.ExpressionGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kostya on 04.03.2016.
 * This class represents fragment for game and GameActivity is the host of this fragment.
 */
public class GameFragment extends Fragment {

    @Nullable
    private Expression mEasyExpression;

    @Nullable
    private Expression mMediumExpression;

    @Nullable
    private Expression mHardExpression;

    private CountDownTimer mTimer;
    private int mExpressionAnswer, mUserAnswer;
    private long mAnswerTime;
    private boolean mIsNumberNegative = false;
    private boolean mIsAnswerEmpty = true;
    private boolean mIsGameFinished = false;
    private int mGamesCount;
    private int mCorrectAnswers;
    private long mScore;

    //region ButterKnife BindView
    @BindView(R.id.evaluation_text_view)
    TextView mEvaluationTextView;

    @BindView(R.id.expression_text_view)
    TextView mExpressionTextView;

    @BindView(R.id.answer_text_view)
    TextView mAnswerTextView;

    @BindView(R.id.timer_text_view)
    TextView mTimerTextView;

    @BindView(R.id.button_check)
    Button mCheckButton;
    //endregion

    public static GameFragment newInstance() {
        Bundle args = new Bundle();
        GameFragment fragment = new GameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //region Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        startGame();
    }
    //endregion

    //region ButterKnife OnClick
    @OnClick(R.id.button_zero)
    void onZeroButtonClick() {
        mEvaluationTextView.setText("");
        showUserAnswer(Digit.ZERO);
    }

    @OnClick(R.id.button_one)
    void onOneButtonClick() {
        mEvaluationTextView.setText("");
        showUserAnswer(Digit.ONE);
    }

    @OnClick(R.id.button_two)
    void onTwoButtonClick() {
        mEvaluationTextView.setText("");
        showUserAnswer(Digit.TWO);
    }

    @OnClick(R.id.button_three)
    void onThreeButtonClick() {
        mEvaluationTextView.setText("");
        showUserAnswer(Digit.THREE);
    }

    @OnClick(R.id.button_four)
    void onFourButtonClick() {
        mEvaluationTextView.setText("");
        showUserAnswer(Digit.FOUR);
    }

    @OnClick(R.id.button_five)
    void onFiveButtonClick() {
        mEvaluationTextView.setText("");
        showUserAnswer(Digit.FIVE);
    }

    @OnClick(R.id.button_six)
    void onSixButtonClick() {
        mEvaluationTextView.setText("");
        showUserAnswer(Digit.SIX);
    }

    @OnClick(R.id.button_seven)
    void onSevenButtonClick() {
        mEvaluationTextView.setText("");
        showUserAnswer(Digit.SEVEN);
    }

    @OnClick(R.id.button_eight)
    void onEightButtonClick() {
        mEvaluationTextView.setText("");
        showUserAnswer(Digit.EIGHT);
    }

    @OnClick(R.id.button_nine)
    void onNineButtonClick() {
        mEvaluationTextView.setText("");
        showUserAnswer(Digit.NINE);
    }

    @OnClick(R.id.button_delete)
    void onDeleteButtonClick() {
        mEvaluationTextView.setText("");
        mAnswerTextView.setText("0");
        mUserAnswer = 0;
        mIsAnswerEmpty = true;
        mIsNumberNegative = false;
    }

    @OnClick(R.id.button_sign)
    void onSignButtonClick() {
        mEvaluationTextView.setText("");

        if (!mIsNumberNegative) {
            mIsNumberNegative = true;
            if (mUserAnswer != 0) {
                mUserAnswer = Integer.parseInt(mAnswerTextView.getText().toString());
                mUserAnswer = changeSign(mUserAnswer);
                String numberStr = Integer.toString(mUserAnswer);
                mAnswerTextView.setText(numberStr);
            } else {
                if (!mAnswerTextView.getText().toString().contains("-")) {
                    mAnswerTextView.setText("-");
                }
            }
        } else {
            mIsNumberNegative = false;
            if (mUserAnswer != 0) {
                mUserAnswer = Integer.parseInt(mAnswerTextView.getText().toString());
                mUserAnswer = changeSign(mUserAnswer);
                String numberStr = Integer.toString(mUserAnswer);
                mAnswerTextView.setText(numberStr);
            } else {
                mAnswerTextView.setText("");
            }
        }
    }

    @OnClick(R.id.button_check)
    void onCheckButtonClick() {
        if (mIsGameFinished) {
            showScore();
        } else {
            if (mCheckButton.getText().equals(getString(R.string.game_button_next))) {
                mCheckButton.setText(R.string.game_button_check);
                mEvaluationTextView.setText("");
            } else {
                checkAnswer(mUserAnswer);
            }

            nextExpression();
        }
    }
    //endregion

    /**
     * Starts game.
     */
    private void startGame() {
        mGamesCount = 0;
        mCorrectAnswers = 0;
        mScore = 0;
        nextExpression();
    }

    /**
     * Shows next expression to the user.
     */
    private void nextExpression() {
        if (!mIsGameFinished) {
            checkGamesCount();
            clearData();
            if (!mIsGameFinished) {
                createExpression();
                startTimer();
                showExpression();
            } else {
                printHint();
            }
        }
    }

    /**
     * Checks count of the game in one session.
     */
    private void checkGamesCount() {
        if (mGamesCount < 10) {
            mGamesCount++;
        } else {
            mIsGameFinished = true;
        }
    }

    /**
     * Clears views for user and prepares variables for next the expression.
     */
    private void clearData() {
        mExpressionTextView.setText("");
        mUserAnswer = 0;
        mAnswerTextView.setText(Integer.toString(mUserAnswer));
        mAnswerTime = 0;
        mIsAnswerEmpty = true;
    }

    /**
     * If game was finished prints to user hint how to open score of the game.
     */
    private void printHint() {
        mAnswerTextView.setText(R.string.game_finished);
        mTimerTextView.setText(R.string.click_score);
        mCheckButton.setText(R.string.score);
    }

    /**
     * Creates expression depending by difficulty level.
     */
    private void createExpression() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String difficultyString = preferences.getString(getString(R.string.pref_difficulty_key),
                getString(R.string.pref_difficulty_default));
        int difficulty = Integer.parseInt(difficultyString); // TODO: Handle NumberFormatException

        switch (difficulty) {
            case Difficulty.EASY:
                mEasyExpression = ExpressionGenerator.getInstance().generate(Difficulty.EASY);
                mExpressionAnswer = mEasyExpression.calculate();
                break;
            case Difficulty.MEDIUM:
                mMediumExpression = ExpressionGenerator.getInstance().generate(Difficulty.MEDIUM);
                mExpressionAnswer = mMediumExpression.calculate();
                break;
            case Difficulty.HARD:
                mHardExpression = ExpressionGenerator.getInstance().generate(Difficulty.HARD);
                mExpressionAnswer = mHardExpression.calculate();
                break;
        }
    }

    /**
     * Shows random generated expression to user.
     */
    private void showExpression() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String difficultyString = preferences.getString(getString(R.string.pref_difficulty_key),
                getString(R.string.pref_difficulty_default));
        int difficulty = Integer.parseInt(difficultyString); // TODO: Handle NumberFormatException

        switch (difficulty) {
            case Difficulty.EASY:
                if (mEasyExpression != null) {
                    mExpressionTextView.setText(mEasyExpression.toString());
                }
                break;
            case Difficulty.MEDIUM:
                if (mMediumExpression != null) {
                    mExpressionTextView.setText(mMediumExpression.toString());
                }
                break;
            case Difficulty.HARD:
                if (mHardExpression != null) {
                    mExpressionTextView.setText(mHardExpression.toString());
                }
                break;
        }
    }

    /**
     * Shows user answer on the screen. If answer was empty before method will show which digit
     * button user clicked. If answer was not empty before method will add digit at right side
     * to the previous answer.
     *
     * @param digit
     */
    private void showUserAnswer(@Digit int digit) {
        mEvaluationTextView.setText("");
        if (mIsAnswerEmpty) {
            mAnswerTextView.setText("");
            mUserAnswer = digit;
            if (mIsNumberNegative) {
                if (!mAnswerTextView.getText().toString().contains("-")) {
                    mAnswerTextView.setText("-" + Integer.toString(digit));
                } else {
                    mAnswerTextView.setText(Integer.toString(digit));
                }
                if (mUserAnswer > 0) mUserAnswer = mUserAnswer * (-1);
            } else {
                mAnswerTextView.setText(Integer.toString(digit));
            }
            mIsAnswerEmpty = false;
        } else {
            String userAnswerStr = Integer.toString(mUserAnswer);
            String userClickStr = Integer.toString(digit);
            userAnswerStr = userAnswerStr + userClickStr;
            mUserAnswer = Integer.parseInt(userAnswerStr); // FIXME: java.lang.NumberFormatException: For input string: "4563210000"
            if (mIsNumberNegative) {
                if (!mAnswerTextView.getText().toString().contains("-")) {
                    mAnswerTextView.setText("-" + userAnswerStr);
                } else {
                    mAnswerTextView.setText(userAnswerStr);
                }
                if (mUserAnswer > 0) mUserAnswer = mUserAnswer * (-1);
            } else {
                mAnswerTextView.setText(userAnswerStr);
            }
        }
    }

    /**
     * Starts mTimer per question.
     */
    private void startTimer() {
        mTimer = new CountDownTimer(11000, 1000) {

            public void onTick(long millisUntilFinished) {
                mAnswerTime = millisUntilFinished / 1000;
                mTimerTextView.setText(Long.toString(mAnswerTime));
            }

            public void onFinish() {
                mTimerTextView.setText("0");
                checkAnswer(mUserAnswer);
                mCheckButton.setText(R.string.game_button_next);
            }
        }.start();
    }

    /**
     * Changes sign of the number. Negative number becomes positive, positive number becomes negative.
     *
     * @param number
     * @return number with changed sign
     */
    private int changeSign(int number) {
        return number * (-1);
    }

    /**
     * Compares user answer with correct answer of the expression.
     *
     * @param answer
     */
    private void checkAnswer(int answer) {
        mTimer.cancel();
        if (mExpressionAnswer == answer) {
            mEvaluationTextView.setText(R.string.correct_answer);
            mEvaluationTextView.setTextColor(Color.GREEN);
            if (!mCheckButton.getText().equals(getString(R.string.score))) {
                mCorrectAnswers++;
                calculateScore();
            }
        } else {
            mEvaluationTextView.setText(R.string.wrong_answer);
            mEvaluationTextView.setTextColor(Color.RED);
        }

        mIsNumberNegative = false;
    }

    /**
     * Calculates score of every answer and adds them.
     */
    private void calculateScore() {
        if (mAnswerTime == 10) {
            mScore = mScore + 100;
        } else if (mAnswerTime > 0 && mAnswerTime < 10) {
            long k = 100 / (10 - mAnswerTime);
            mScore = mScore + k;
        }
    }

    /**
     * If game session was finished this method will replace new fragment with score of the game.
     */
    // TODO: Add Activity interaction or implement it with ViewModel from Architecture Components
    private void showScore() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_game, ScoreFragment.newInstance(mGamesCount, mCorrectAnswers, mScore));
        fragmentTransaction.commit();
    }
}