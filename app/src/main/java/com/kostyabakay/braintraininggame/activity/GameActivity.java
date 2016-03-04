package com.kostyabakay.braintraininggame.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kostyabakay.braintraininggame.R;
import com.kostyabakay.braintraininggame.model.EasyExpression;

/**
 * Created by Kostya on 04.03.2016.
 * This class represents Activity for game.
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private final int[] USER_DIGIT_CLICK = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private TextView evaluationTextView, expressionTextView, answerTextView;
    private EasyExpression easyExpression;
    private int expressionAnswer, userAnswer;
    private boolean isNumberNegative = false;
    private boolean isAnswerEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initView();
        startGame();
    }

    /**
     * Initialization view elements on the screen.
     */
    private void initView() {
        evaluationTextView = (TextView) findViewById(R.id.evaluation_text_view);
        expressionTextView = (TextView) findViewById(R.id.expression_text_view);
        answerTextView = (TextView) findViewById(R.id.answer_text_view);

        Button oneBtn = (Button) findViewById(R.id.button_1);
        Button twoBtn = (Button) findViewById(R.id.button_2);
        Button threeBtn = (Button) findViewById(R.id.button_3);
        Button fourthBtn = (Button) findViewById(R.id.button_4);
        Button fiveBtn = (Button) findViewById(R.id.button_5);
        Button sixBtn = (Button) findViewById(R.id.button_6);
        Button sevenBtn = (Button) findViewById(R.id.button_7);
        Button eightBtn = (Button) findViewById(R.id.button_8);
        Button nineBtn = (Button) findViewById(R.id.button_9);
        Button zeroBtn = (Button) findViewById(R.id.button_0);
        Button deleteBtn = (Button) findViewById(R.id.button_delete);
        Button signBtn = (Button) findViewById(R.id.button_sign);
        Button checkBtn = (Button) findViewById(R.id.button_check);

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
        easyExpression = new EasyExpression();
        expressionAnswer = easyExpression.getCalculationResult();
        showExpression();
    }

    /**
     * Shows random generated expression to user.
     */
    private void showExpression() {
        expressionTextView.setText("" + easyExpression.getFirstTerm() + " " + easyExpression.getOperator() + " " + easyExpression.getSecondTerm());
    }

    /**
     * Shows user answer on the screen. If answer was empty before method will show which digit
     * button user clicked. If answer was not empty before method will add digit at right side
     * to the previous answer.
     * @param clickId
     */
    private void showUserAnswer(int clickId) {
        evaluationTextView.setText("");
        if (isAnswerEmpty) {
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
     * Changes sign of the number. Negative number becomes positive, positive number becomes negative.
     * @param number
     * @return number with changed sign
     */
    private int changeSign(int number) {
        return number * (-1);
    }

    /**
     * Compares user answer with correct answer of the expression.
     * @param answer
     */
    private void checkAnswer(int answer) {
        if (expressionAnswer == answer) {
            evaluationTextView.setText(R.string.correct_answer);
            evaluationTextView.setTextColor(Color.GREEN);
        } else {
            evaluationTextView.setText(R.string.wrong_answer);
            evaluationTextView.setTextColor(Color.RED);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_0:
                showUserAnswer(USER_DIGIT_CLICK[0]);
                break;

            case R.id.button_1:
                showUserAnswer(USER_DIGIT_CLICK[1]);
                break;

            case R.id.button_2:
                showUserAnswer(USER_DIGIT_CLICK[2]);
                break;

            case R.id.button_3:
                showUserAnswer(USER_DIGIT_CLICK[3]);
                break;

            case R.id.button_4:
                showUserAnswer(USER_DIGIT_CLICK[4]);
                break;

            case R.id.button_5:
                showUserAnswer(USER_DIGIT_CLICK[5]);
                break;

            case R.id.button_6:
                showUserAnswer(USER_DIGIT_CLICK[6]);
                break;

            case R.id.button_7:
                showUserAnswer(USER_DIGIT_CLICK[7]);
                break;

            case R.id.button_8:
                showUserAnswer(USER_DIGIT_CLICK[8]);
                break;

            case R.id.button_9:
                showUserAnswer(USER_DIGIT_CLICK[9]);
                break;

            case R.id.button_delete:
                answerTextView.setText("");
                evaluationTextView.setText("");
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
                checkAnswer(userAnswer);
                break;
        }
    }
}
