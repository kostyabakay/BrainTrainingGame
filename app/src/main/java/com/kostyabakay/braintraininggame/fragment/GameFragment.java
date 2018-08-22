package com.kostyabakay.braintraininggame.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kostyabakay.braintraininggame.AppData;
import com.kostyabakay.braintraininggame.R;
import com.kostyabakay.braintraininggame.common.def.Difficulty;
import com.kostyabakay.braintraininggame.common.def.Digit;
import com.kostyabakay.braintraininggame.model.EasyExpression;
import com.kostyabakay.braintraininggame.model.HardExpression;
import com.kostyabakay.braintraininggame.model.MediumExpression;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kostya on 04.03.2016.
 * This class represents fragment for game and GameActivity is the host of this fragment.
 */
public class GameFragment extends Fragment {
    private EasyExpression easyExpression;
    private MediumExpression mediumExpression;
    private HardExpression hardExpression;
    private CountDownTimer timer;
    private int expressionAnswer, userAnswer;
    private long answerTime;
    private boolean isNumberNegative = false;
    private boolean isAnswerEmpty = true;
    private boolean isGameFinished = false;

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
        userAnswer = 0;
        isAnswerEmpty = true;
        isNumberNegative = false;
    }

    @OnClick(R.id.button_sign)
    void onSignButtonClick() {
        mEvaluationTextView.setText("");

        if (!isNumberNegative) {
            isNumberNegative = true;
            if (userAnswer != 0) {
                userAnswer = Integer.parseInt(mAnswerTextView.getText().toString());
                userAnswer = changeSign(userAnswer);
                String numberStr = Integer.toString(userAnswer);
                mAnswerTextView.setText(numberStr);
            } else {
                if (!mAnswerTextView.getText().toString().contains("-")) {
                    mAnswerTextView.setText("-");
                }
            }
        } else {
            isNumberNegative = false;
            if (userAnswer != 0) {
                userAnswer = Integer.parseInt(mAnswerTextView.getText().toString());
                userAnswer = changeSign(userAnswer);
                String numberStr = Integer.toString(userAnswer);
                mAnswerTextView.setText(numberStr);
            } else {
                mAnswerTextView.setText("");
            }
        }
    }

    @OnClick(R.id.button_check)
    void onCheckButtonClick() {
        if (isGameFinished) {
            showGameScoreFragment();
        } else {
            if (mCheckButton.getText().equals(getString(R.string.game_button_next))) {
                mCheckButton.setText(R.string.game_button_check);
                mEvaluationTextView.setText("");
            } else {
                checkAnswer(userAnswer);
            }

            nextExpression();
        }
    }
    //endregion

    /**
     * Starts game.
     */
    private void startGame() {
        AppData.gamesCount = 0;
        AppData.correctAnswers = 0;
        AppData.score = 0;
        nextExpression();
    }

    /**
     * Shows next expression to the user.
     */
    private void nextExpression() {
        if (!isGameFinished) {
            checkGamesCount();
            clearData();
            if (!isGameFinished) {
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
        if (AppData.gamesCount < 10) {
            AppData.gamesCount++;
        } else {
            isGameFinished = true;
        }
    }

    /**
     * Clears views for user and prepares variables for next the expression.
     */
    private void clearData() {
        mExpressionTextView.setText("");
        userAnswer = 0;
        mAnswerTextView.setText(Integer.toString(userAnswer));
        answerTime = 0;
        isAnswerEmpty = true;
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
                easyExpression = new EasyExpression();
                expressionAnswer = easyExpression.getCalculationResult();
                break;
            case Difficulty.MEDIUM:
                mediumExpression = new MediumExpression();
                expressionAnswer = mediumExpression.getCalculationResult();
                break;
            case Difficulty.HARD:
                hardExpression = new HardExpression();
                expressionAnswer = hardExpression.getCalculationResult();
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
                mExpressionTextView.setText("" + easyExpression.getFirstTerm() + " "
                        + easyExpression.getOperator() + " " + easyExpression.getSecondTerm());
                break;
            case Difficulty.MEDIUM:
                mExpressionTextView.setText("" + mediumExpression.getFirstTerm() + " "
                        + mediumExpression.getFirstOperator() + " "
                        + mediumExpression.getSecondTerm() + " "
                        + mediumExpression.getSecondOperator() + " "
                        + mediumExpression.getThirdTerm());
                break;
            case Difficulty.HARD:
                mExpressionTextView.setText("" + hardExpression.getFirstTerm() + " "
                        + hardExpression.getFirstOperator() + " "
                        + hardExpression.getSecondTerm() + " " + hardExpression.getSecondOperator()
                        + " " + hardExpression.getThirdTerm() + " "
                        + hardExpression.getThirdOperator() + " " + hardExpression.getFourthTerm());
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
        if (isAnswerEmpty) {
            mAnswerTextView.setText("");
            userAnswer = digit;
            if (isNumberNegative) {
                if (!mAnswerTextView.getText().toString().contains("-")) {
                    mAnswerTextView.setText("-" + Integer.toString(digit));
                } else {
                    mAnswerTextView.setText(Integer.toString(digit));
                }
                if (userAnswer > 0) userAnswer = userAnswer * (-1);
            } else {
                mAnswerTextView.setText(Integer.toString(digit));
            }
            isAnswerEmpty = false;
        } else {
            String userAnswerStr = Integer.toString(userAnswer);
            String userClickStr = Integer.toString(digit);
            userAnswerStr = userAnswerStr + userClickStr;
            userAnswer = Integer.parseInt(userAnswerStr); // FIXME: java.lang.NumberFormatException: For input string: "4563210000"
            if (isNumberNegative) {
                if (!mAnswerTextView.getText().toString().contains("-")) {
                    mAnswerTextView.setText("-" + userAnswerStr);
                } else {
                    mAnswerTextView.setText(userAnswerStr);
                }
                if (userAnswer > 0) userAnswer = userAnswer * (-1);
            } else {
                mAnswerTextView.setText(userAnswerStr);
            }
        }
    }

    /**
     * Starts timer per question.
     */
    private void startTimer() {
        timer = new CountDownTimer(11000, 1000) {

            public void onTick(long millisUntilFinished) {
                answerTime = millisUntilFinished / 1000;
                mTimerTextView.setText(Long.toString(answerTime));
            }

            public void onFinish() {
                mTimerTextView.setText("0");
                checkAnswer(userAnswer);
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
        timer.cancel();
        if (expressionAnswer == answer) {
            mEvaluationTextView.setText(R.string.correct_answer);
            mEvaluationTextView.setTextColor(Color.GREEN);
            if (!mCheckButton.getText().equals(getString(R.string.score))) {
                AppData.correctAnswers++;
                calculateScore();
            }
        } else {
            mEvaluationTextView.setText(R.string.wrong_answer);
            mEvaluationTextView.setTextColor(Color.RED);
        }

        isNumberNegative = false;
    }

    /**
     * Calculates score of every answer and adds them.
     */
    private void calculateScore() {
        if (answerTime == 10) {
            AppData.score = AppData.score + 100;
        } else if (answerTime > 0 && answerTime < 10) {
            long k = 100 / (10 - answerTime);
            AppData.score = AppData.score + k;
        }
    }

    /**
     * If game session was finished this method will replace new fragment with score of the game.
     */
    private void showGameScoreFragment() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_game, ScoreFragment.newInstance());
        fragmentTransaction.commit();
    }
}