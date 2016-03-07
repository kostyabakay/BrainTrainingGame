package com.kostyabakay.braintraininggame.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kostyabakay.braintraininggame.AppData;
import com.kostyabakay.braintraininggame.R;
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
    private boolean isNumberNegative = false;
    private boolean isAnswerEmpty = true;
    private boolean isGameFinished = false;

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

    /**
     * Initialization view elements on the screen.
     */
    private void initView() {
        evaluationTextView = (TextView) getActivity().findViewById(R.id.evaluation_text_view);
        expressionTextView = (TextView) getActivity().findViewById(R.id.expression_text_view);
        answerTextView = (TextView) getActivity().findViewById(R.id.answer_text_view);
        timerTextView = (TextView) getActivity().findViewById(R.id.timer_text_view);

        Button oneBtn = (Button) getActivity().findViewById(R.id.button_1);
        Button twoBtn = (Button) getActivity().findViewById(R.id.button_2);
        Button threeBtn = (Button) getActivity().findViewById(R.id.button_3);
        Button fourthBtn = (Button) getActivity().findViewById(R.id.button_4);
        Button fiveBtn = (Button) getActivity().findViewById(R.id.button_5);
        Button sixBtn = (Button) getActivity().findViewById(R.id.button_6);
        Button sevenBtn = (Button) getActivity().findViewById(R.id.button_7);
        Button eightBtn = (Button) getActivity().findViewById(R.id.button_8);
        Button nineBtn = (Button) getActivity().findViewById(R.id.button_9);
        Button zeroBtn = (Button) getActivity().findViewById(R.id.button_0);
        Button deleteBtn = (Button) getActivity().findViewById(R.id.button_delete);
        Button signBtn = (Button) getActivity().findViewById(R.id.button_sign);
        checkBtn = (Button) getActivity().findViewById(R.id.button_check);

        oneBtn.setOnClickListener(this);
        twoBtn.setOnClickListener(this);
        threeBtn.setOnClickListener(this);
        fourthBtn.setOnClickListener(this);
        fiveBtn.setOnClickListener(this);
        sixBtn.setOnClickListener(this);
        sevenBtn.setOnClickListener(this);
        eightBtn.setOnClickListener(this);
        nineBtn.setOnClickListener(this);
        zeroBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        signBtn.setOnClickListener(this);
        checkBtn.setOnClickListener(this);
    }

    /**
     * Starts game.
     */
    private void startGame() {
        AppData.gamesCount = 0;
        nextExpression();
    }

    /**
     * Shows next expression to the user.
     */
    private void nextExpression() {
        if (!isGameFinished) {
            checkGamesCount();
            clearData();
            createExpression();
            startTimer();
            showExpression();
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
        answerTextView.setText("");
        isAnswerEmpty = true;
        userAnswer = 0;
    }

    /**
     * Creates expression depending by difficulty level.
     */
    private void createExpression() {
        if (AppData.easyLevel) {
            easyExpression = new EasyExpression();
            expressionAnswer = easyExpression.getCalculationResult();
        } else if (AppData.mediumLevel) {
            mediumExpression = new MediumExpression();
            expressionAnswer = mediumExpression.getCalculationResult();
        } else if (AppData.hardLevel) {
            hardExpression = new HardExpression();
            expressionAnswer = hardExpression.getCalculationResult();
        }
    }

    /**
     * Shows random generated expression to user.
     */
    private void showExpression() {
        if (AppData.easyLevel) {
            expressionTextView.setText("" + easyExpression.getFirstTerm() + " " + easyExpression.getOperator() + " " + easyExpression.getSecondTerm());
        } else if (AppData.mediumLevel) {
            expressionTextView.setText("" + mediumExpression.getFirstTerm() + " " + mediumExpression.getFirstOperator() + " " + mediumExpression.getSecondTerm() + " " + mediumExpression.getSecondOperator() + " " + mediumExpression.getThirdTerm());
        } else if (AppData.hardLevel) {
            expressionTextView.setText("" + hardExpression.getFirstTerm() + " " + hardExpression.getFirstOperator() + " " + hardExpression.getSecondTerm() + " " + hardExpression.getSecondOperator() + " " + hardExpression.getThirdTerm() + " " + hardExpression.getThirdOperator() + " " + hardExpression.getFourthTerm());
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
            answerTextView.setText(Integer.toString(clickId));
            isAnswerEmpty = false;
        } else {
            String userAnswerStr = Integer.toString(userAnswer);
            String userClickStr = Integer.toString(clickId);
            userAnswerStr = userAnswerStr + userClickStr;
            userAnswer = Integer.parseInt(userAnswerStr);
            answerTextView.setText(userAnswerStr);
        }
    }

    /**
     * Starts timer per question.
     */
    private void startTimer() {
        timer = new CountDownTimer(11000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Long.toString(millisUntilFinished / 1000));
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
        } else {
            evaluationTextView.setText(R.string.wrong_answer);
            evaluationTextView.setTextColor(Color.RED);
        }
    }

    /**
     * If game session was finished this method will replace new fragment with score of the game.
     */
    private void showGameScoreFragment() {
        ScoreFragment scoreFragment = new ScoreFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_game, scoreFragment);
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
                answerTextView.setText("");
                userAnswer = 0;
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
                        answerTextView.setText("-");
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
                if (checkBtn.getText().equals(getString(R.string.game_button_next))) {
                    checkBtn.setText(R.string.game_button_check);
                    evaluationTextView.setText("");
                } else {
                    checkAnswer(userAnswer);
                }

                if (checkBtn.getText().equals(getString(R.string.score))) {
                    showGameScoreFragment();
                } else if (isGameFinished && !checkBtn.getText().equals(getString(R.string.score))) {
                    checkBtn.setText(R.string.score);
                }

                nextExpression();
                break;
        }
    }
}
