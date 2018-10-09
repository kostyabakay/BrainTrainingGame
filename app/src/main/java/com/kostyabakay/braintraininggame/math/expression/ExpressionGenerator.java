package com.kostyabakay.braintraininggame.math.expression;

import android.support.annotation.NonNull;

import com.kostyabakay.braintraininggame.common.def.Difficulty;
import com.kostyabakay.braintraininggame.math.operand.Operand;
import com.kostyabakay.braintraininggame.math.operator.Adder;
import com.kostyabakay.braintraininggame.math.operator.Divider;
import com.kostyabakay.braintraininggame.math.operator.Multiplier;
import com.kostyabakay.braintraininggame.math.operator.Operator;
import com.kostyabakay.braintraininggame.math.operator.Subtractor;

import java.util.Random;

/**
 * Singleton Design Pattern
 */
public class ExpressionGenerator {

    private static volatile ExpressionGenerator sInstance;

    private ExpressionGenerator() {
    }

    @NonNull
    public static ExpressionGenerator getInstance() {
        if (sInstance == null) {
            synchronized (ExpressionGenerator.class) {
                if (sInstance == null) {
                    sInstance = new ExpressionGenerator();
                }
            }
        }
        return sInstance;
    }

    public Expression generate(@Difficulty int difficulty) {
        // TODO: Implement expression generation
        switch (difficulty) {
            case Difficulty.EASY:
                // return new Adder(new Operand(generateOperand()), new Operand(generateOperand()));
                return generateOperator(new Operand(generateOperand()), new Operand(generateOperand()));
            default:
                throw new UnsupportedOperationException();
        }
    }

    //region Utility API
    // TODO: Temp parameters
    private BinaryExpression generateOperator(@NonNull Operand operand1, @NonNull Operand operand2) {
        // TODO: Implement operator generation
        int operatorCode = new Random().nextInt(3) + 1;
        switch (operatorCode) {
            case Operator.ADDITION:
                return new Adder(operand1, operand2);
            case Operator.SUBTRACTION:
                return new Subtractor(operand1, operand2);
            case Operator.MULTIPLICATION:
                return new Multiplier(operand1, operand2);
            case Operator.DIVISION:
                return new Divider(operand1, operand2);
            default:
                throw new UnsupportedOperationException();
        }
    }

    private int generateOperand() {
        return new Random().nextInt(10);
    }
    //endregion
}