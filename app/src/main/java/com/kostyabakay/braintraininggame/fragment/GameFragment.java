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

import com.kostyabakay.braintraininggame.R;
import com.kostyabakay.braintraininggame.common.def.Difficulty;
import com.kostyabakay.braintraininggame.common.def.Digit;
import com.kostyabakay.braintraininggame.databinding.FragmentGameBinding;
import com.kostyabakay.braintraininggame.math.expression.Expression;
import com.kostyabakay.braintraininggame.math.expression.ExpressionGenerator;

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

    private FragmentGameBinding binding;

    public static GameFragment newInstance() {
        Bundle args = new Bundle();
        GameFragment fragment = new GameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //region Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        startGame();
        setupClickListeners();
    }
    //endregion

    private void setupClickListeners() {
        binding.buttonZero.setOnClickListener(v -> onDigitButtonClick(Digit.ZERO));
        binding.buttonOne.setOnClickListener(v -> onDigitButtonClick(Digit.ONE));
        binding.buttonTwo.setOnClickListener(v -> onDigitButtonClick(Digit.TWO));
        binding.buttonThree.setOnClickListener(v -> onDigitButtonClick(Digit.THREE));
        binding.buttonFour.setOnClickListener(v -> onDigitButtonClick(Digit.FOUR));
        binding.buttonFive.setOnClickListener(v -> onDigitButtonClick(Digit.FIVE));
        binding.buttonSix.setOnClickListener(v -> onDigitButtonClick(Digit.SIX));
        binding.buttonSeven.setOnClickListener(v -> onDigitButtonClick(Digit.SEVEN));
        binding.buttonEight.setOnClickListener(v -> onDigitButtonClick(Digit.EIGHT));
        binding.buttonNine.setOnClickListener(v -> onDigitButtonClick(Digit.NINE));

        binding.buttonDelete.setOnClickListener(v -> onDeleteButtonClick());
        binding.buttonSign.setOnClickListener(v -> onSignButtonClick());
        binding.buttonCheck.setOnClickListener(v -> onCheckButtonClick());
    }

    private void onDigitButtonClick(@Digit int digit) {
        if (digit == 0) {
            onZeroButtonClick();
        } else if (digit == 1) {
            onOneButtonClick();
        } else if (digit == 2) {
            onTwoButtonClick();
        } else if (digit == 3) {
            onThreeButtonClick();
        } else if (digit == 4) {
            onFourButtonClick();
        } else if (digit == 5) {
            onFiveButtonClick();
        } else if (digit == 6) {
            onSixButtonClick();
        } else if (digit == 7) {
            onSevenButtonClick();
        } else if (digit == 8) {
            onEightButtonClick();
        } else if (digit == 9) {
            onNineButtonClick();
        }
    }

    //region ButterKnife OnClick
    private void onZeroButtonClick() {
        binding.evaluationTextView.setText("");
        showUserAnswer(Digit.ZERO);
    }

    private void onOneButtonClick() {
        binding.evaluationTextView.setText("");
        showUserAnswer(Digit.ONE);
    }

    void onTwoButtonClick() {
        binding.evaluationTextView.setText("");
        showUserAnswer(Digit.TWO);
    }

    void onThreeButtonClick() {
        binding.evaluationTextView.setText("");
        showUserAnswer(Digit.THREE);
    }

    void onFourButtonClick() {
        binding.evaluationTextView.setText("");
        showUserAnswer(Digit.FOUR);
    }

    void onFiveButtonClick() {
        binding.evaluationTextView.setText("");
        showUserAnswer(Digit.FIVE);
    }

    void onSixButtonClick() {
        binding.evaluationTextView.setText("");
        showUserAnswer(Digit.SIX);
    }

    void onSevenButtonClick() {
        binding.evaluationTextView.setText("");
        showUserAnswer(Digit.SEVEN);
    }

    void onEightButtonClick() {
        binding.evaluationTextView.setText("");
        showUserAnswer(Digit.EIGHT);
    }

    void onNineButtonClick() {
        binding.evaluationTextView.setText("");
        showUserAnswer(Digit.NINE);
    }

    void onDeleteButtonClick() {
        binding.evaluationTextView.setText("");
        binding.answerTextView.setText("0");
        mUserAnswer = 0;
        mIsAnswerEmpty = true;
        mIsNumberNegative = false;
    }

    void onSignButtonClick() {
        binding.evaluationTextView.setText("");

        if (!mIsNumberNegative) {
            mIsNumberNegative = true;
            if (mUserAnswer != 0) {
                mUserAnswer = Integer.parseInt(binding.answerTextView.getText().toString());
                mUserAnswer = changeSign(mUserAnswer);
                String numberStr = Integer.toString(mUserAnswer);
                binding.answerTextView.setText(numberStr);
            } else {
                if (!binding.answerTextView.getText().toString().contains("-")) {
                    binding.answerTextView.setText("-");
                }
            }
        } else {
            mIsNumberNegative = false;
            if (mUserAnswer != 0) {
                mUserAnswer = Integer.parseInt(binding.answerTextView.getText().toString());
                mUserAnswer = changeSign(mUserAnswer);
                String numberStr = Integer.toString(mUserAnswer);
                binding.answerTextView.setText(numberStr);
            } else {
                binding.answerTextView.setText("");
            }
        }
    }

    void onCheckButtonClick() {
        if (mIsGameFinished) {
            showScore();
        } else {
            if (binding.buttonCheck.getText().equals(getString(R.string.game_button_next))) {
                binding.buttonCheck.setText(R.string.game_button_check);
                binding.evaluationTextView.setText("");
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
        binding.expressionTextView.setText("");
        mUserAnswer = 0;
        binding.answerTextView.setText(Integer.toString(mUserAnswer));
        mAnswerTime = 0;
        mIsAnswerEmpty = true;
    }

    /**
     * If game was finished prints to user hint how to open score of the game.
     */
    private void printHint() {
        binding.answerTextView.setText(R.string.game_finished);
        binding.timerTextView.setText(R.string.click_score);
        binding.buttonCheck.setText(R.string.score);
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
                    binding.expressionTextView.setText(mEasyExpression.toString());
                }
                break;
            case Difficulty.MEDIUM:
                if (mMediumExpression != null) {
                    binding.expressionTextView.setText(mMediumExpression.toString());
                }
                break;
            case Difficulty.HARD:
                if (mHardExpression != null) {
                    binding.expressionTextView.setText(mHardExpression.toString());
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
        binding.evaluationTextView.setText("");
        if (mIsAnswerEmpty) {
            binding.answerTextView.setText("");
            mUserAnswer = digit;
            if (mIsNumberNegative) {
                if (!binding.answerTextView.getText().toString().contains("-")) {
                    binding.answerTextView.setText("-" + Integer.toString(digit));
                } else {
                    binding.answerTextView.setText(Integer.toString(digit));
                }
                if (mUserAnswer > 0) mUserAnswer = mUserAnswer * (-1);
            } else {
                binding.answerTextView.setText(Integer.toString(digit));
            }
            mIsAnswerEmpty = false;
        } else {
            String userAnswerStr = Integer.toString(mUserAnswer);
            String userClickStr = Integer.toString(digit);
            userAnswerStr = userAnswerStr + userClickStr;
            mUserAnswer = Integer.parseInt(userAnswerStr); // FIXME: java.lang.NumberFormatException: For input string: "4563210000"
            if (mIsNumberNegative) {
                if (!binding.answerTextView.getText().toString().contains("-")) {
                    binding.answerTextView.setText("-" + userAnswerStr);
                } else {
                    binding.answerTextView.setText(userAnswerStr);
                }
                if (mUserAnswer > 0) mUserAnswer = mUserAnswer * (-1);
            } else {
                binding.answerTextView.setText(userAnswerStr);
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
                binding.timerTextView.setText(Long.toString(mAnswerTime));
            }

            public void onFinish() {
                binding.timerTextView.setText("0");
                checkAnswer(mUserAnswer);
                binding.buttonCheck.setText(R.string.game_button_next);
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
            binding.evaluationTextView.setText(R.string.correct_answer);
            binding.evaluationTextView.setTextColor(Color.GREEN);
            if (!binding.buttonCheck.getText().equals(getString(R.string.score))) {
                mCorrectAnswers++;
                calculateScore();
            }
        } else {
            binding.evaluationTextView.setText(R.string.wrong_answer);
            binding.evaluationTextView.setTextColor(Color.RED);
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