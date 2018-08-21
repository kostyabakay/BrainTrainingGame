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
import com.kostyabakay.braintraininggame.common.def.DifficultyDef;
import com.kostyabakay.braintraininggame.model.EasyExpression;
import com.kostyabakay.braintraininggame.model.HardExpression;
import com.kostyabakay.braintraininggame.model.MediumExpression;

/**
 * Created by Kostya on 04.03.2016.
 * This class represents fragment for game and GameActivity is the host of this fragment.
 */
public class GameFragment extends Fragment implements View.OnClickListener {
    private final int[] USER_DIGIT_CLICK = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private TextView evaluationTextView, expressionTextView, answerTextView, timerTextView;
    private Button checkBtn;
    private EasyExpression easyExpression;
    private MediumExpression mediumExpression;
    private HardExpression hardExpression;
    private CountDownTimer timer;
    private int expressionAnswer, userAnswer;
    private long answerTime;
    private boolean isNumberNegative = false;
    private boolean isAnswerEmpty = true;
    private boolean isGameFinished = false;

    public static GameFragment newInstance() {
        Bundle args = new Bundle();
        GameFragment fragment = new GameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //region Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
        startGame();
    }
    //endregion

    /**
     * Initialization view elements on the screen.
     */
    private void initView() {
        // FIXME: getActivity can be null
        evaluationTextView = getActivity().findViewById(R.id.evaluation_text_view);
        expressionTextView = getActivity().findViewById(R.id.expression_text_view);
        answerTextView = getActivity().findViewById(R.id.answer_text_view);
        timerTextView = getActivity().findViewById(R.id.timer_text_view);

        getActivity().findViewById(R.id.button_1).setOnClickListener(this);
        getActivity().findViewById(R.id.button_2).setOnClickListener(this);
        getActivity().findViewById(R.id.button_3).setOnClickListener(this);
        getActivity().findViewById(R.id.button_4).setOnClickListener(this);
        getActivity().findViewById(R.id.button_5).setOnClickListener(this);
        getActivity().findViewById(R.id.button_6).setOnClickListener(this);
        getActivity().findViewById(R.id.button_7).setOnClickListener(this);
        getActivity().findViewById(R.id.button_8).setOnClickListener(this);
        getActivity().findViewById(R.id.button_9).setOnClickListener(this);
        getActivity().findViewById(R.id.button_0).setOnClickListener(this);
        getActivity().findViewById(R.id.button_delete).setOnClickListener(this);
        getActivity().findViewById(R.id.button_sign).setOnClickListener(this);

        checkBtn = getActivity().findViewById(R.id.button_check);
        checkBtn.setOnClickListener(this);
    }

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
        expressionTextView.setText("");
        userAnswer = 0;
        answerTextView.setText(Integer.toString(userAnswer));
        answerTime = 0;
        isAnswerEmpty = true;
    }

    /**
     * If game was finished prints to user hint how to open score of the game.
     */
    private void printHint() {
        answerTextView.setText(R.string.game_finished);
        timerTextView.setText(R.string.click_score);
        checkBtn.setText(R.string.score);
    }

    /**
     * Creates expression depending by difficulty level.
     */
    private void createExpression() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String difficulty = preferences.getString(getString(R.string.pref_difficulty_key),
                getString(R.string.pref_difficulty_default));

        switch (difficulty) {
            case DifficultyDef.EASY:
                easyExpression = new EasyExpression();
                expressionAnswer = easyExpression.getCalculationResult();
                break;
            case DifficultyDef.MEDIUM:
                mediumExpression = new MediumExpression();
                expressionAnswer = mediumExpression.getCalculationResult();
                break;
            case DifficultyDef.HARD:
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
        String difficulty = preferences.getString(getString(R.string.pref_difficulty_key),
                getString(R.string.pref_difficulty_default));

        switch (difficulty) {
            case DifficultyDef.EASY:
                expressionTextView.setText("" + easyExpression.getFirstTerm() + " "
                        + easyExpression.getOperator() + " " + easyExpression.getSecondTerm());
                break;
            case DifficultyDef.MEDIUM:
                expressionTextView.setText("" + mediumExpression.getFirstTerm() + " "
                        + mediumExpression.getFirstOperator() + " "
                        + mediumExpression.getSecondTerm() + " "
                        + mediumExpression.getSecondOperator() + " "
                        + mediumExpression.getThirdTerm());
                break;
            case DifficultyDef.HARD:
                expressionTextView.setText("" + hardExpression.getFirstTerm() + " "
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
     * @param clickId
     */
    private void showUserAnswer(int clickId) {
        evaluationTextView.setText("");
        if (isAnswerEmpty) {
            answerTextView.setText("");
            userAnswer = clickId;
            if (isNumberNegative) {
                if (!answerTextView.getText().toString().contains("-")) {
                    answerTextView.setText("-" + Integer.toString(clickId));
                } else {
                    answerTextView.setText(Integer.toString(clickId));
                }
                if (userAnswer > 0) userAnswer = userAnswer * (-1);
            } else {
                answerTextView.setText(Integer.toString(clickId));
            }
            isAnswerEmpty = false;
        } else {
            String userAnswerStr = Integer.toString(userAnswer);
            String userClickStr = Integer.toString(clickId);
            userAnswerStr = userAnswerStr + userClickStr;
            userAnswer = Integer.parseInt(userAnswerStr);
            if (isNumberNegative) {
                if (!answerTextView.getText().toString().contains("-")) {
                    answerTextView.setText("-" + userAnswerStr);
                } else {
                    answerTextView.setText(userAnswerStr);
                }
                if (userAnswer > 0) userAnswer = userAnswer * (-1);
            } else {
                answerTextView.setText(userAnswerStr);
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
                timerTextView.setText(Long.toString(answerTime));
            }

            public void onFinish() {
                timerTextView.setText("0");
                checkAnswer(userAnswer);
                checkBtn.setText(R.string.game_button_next);
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
            evaluationTextView.setText(R.string.correct_answer);
            evaluationTextView.setTextColor(Color.GREEN);
            if (!checkBtn.getText().equals(getString(R.string.score))) {
                AppData.correctAnswers++;
                calculateScore();
            }
        } else {
            evaluationTextView.setText(R.string.wrong_answer);
            evaluationTextView.setTextColor(Color.RED);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_0:
                evaluationTextView.setText("");
                showUserAnswer(USER_DIGIT_CLICK[0]);
                break;

            case R.id.button_1:
                evaluationTextView.setText("");
                showUserAnswer(USER_DIGIT_CLICK[1]);
                break;

            case R.id.button_2:
                evaluationTextView.setText("");
                showUserAnswer(USER_DIGIT_CLICK[2]);
                break;

            case R.id.button_3:
                evaluationTextView.setText("");
                showUserAnswer(USER_DIGIT_CLICK[3]);
                break;

            case R.id.button_4:
                evaluationTextView.setText("");
                showUserAnswer(USER_DIGIT_CLICK[4]);
                break;

            case R.id.button_5:
                evaluationTextView.setText("");
                showUserAnswer(USER_DIGIT_CLICK[5]);
                break;

            case R.id.button_6:
                evaluationTextView.setText("");
                showUserAnswer(USER_DIGIT_CLICK[6]);
                break;

            case R.id.button_7:
                evaluationTextView.setText("");
                showUserAnswer(USER_DIGIT_CLICK[7]);
                break;

            case R.id.button_8:
                evaluationTextView.setText("");
                showUserAnswer(USER_DIGIT_CLICK[8]);
                break;

            case R.id.button_9:
                evaluationTextView.setText("");
                showUserAnswer(USER_DIGIT_CLICK[9]);
                break;

            case R.id.button_delete:
                evaluationTextView.setText("");
                answerTextView.setText("0");
                userAnswer = 0;
                isAnswerEmpty = true;
                isNumberNegative = false;
                break;

            case R.id.button_sign:
                evaluationTextView.setText("");

                if (!isNumberNegative) {
                    isNumberNegative = true;
                    if (userAnswer != 0) {
                        userAnswer = Integer.parseInt(answerTextView.getText().toString());
                        userAnswer = changeSign(userAnswer);
                        String numberStr = Integer.toString(userAnswer);
                        answerTextView.setText(numberStr);
                    } else {
                        if (!answerTextView.getText().toString().contains("-")) {
                            answerTextView.setText("-");
                        }
                    }
                } else {
                    isNumberNegative = false;
                    if (userAnswer != 0) {
                        userAnswer = Integer.parseInt(answerTextView.getText().toString());
                        userAnswer = changeSign(userAnswer);
                        String numberStr = Integer.toString(userAnswer);
                        answerTextView.setText(numberStr);
                    } else {
                        answerTextView.setText("");
                    }
                }

                break;

            case R.id.button_check:
                if (isGameFinished) {
                    showGameScoreFragment();
                } else {
                    if (checkBtn.getText().equals(getString(R.string.game_button_next))) {
                        checkBtn.setText(R.string.game_button_check);
                        evaluationTextView.setText("");
                    } else {
                        checkAnswer(userAnswer);
                    }

                    nextExpression();
                    break;
                }
        }
    }
}