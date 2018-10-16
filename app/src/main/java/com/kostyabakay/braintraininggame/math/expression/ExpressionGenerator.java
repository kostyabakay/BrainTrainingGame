package com.kostyabakay.braintraininggame.math.expression;

import android.support.annotation.NonNull;

import com.kostyabakay.braintraininggame.common.def.Difficulty;
import com.kostyabakay.braintraininggame.math.operand.Operand;
import com.kostyabakay.braintraininggame.math.operator.Adder;
import com.kostyabakay.braintraininggame.math.operator.Divider;
import com.kostyabakay.braintraininggame.math.operator.Multiplier;
import com.kostyabakay.braintraininggame.math.operator.Operator;
import com.kostyabakay.braintraininggame.math.operator.Priority;
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

    @NonNull
    public Expression generate(@Difficulty int difficulty) {
        switch (difficulty) {
            case Difficulty.EASY:
                return generateOperator(generateOperand(), generateOperand());
            case Difficulty.MEDIUM:
                return generateOperator(generateOperator(generateOperand(), generateOperand()), generateOperand());
            case Difficulty.HARD:
                return generateOperator(generateOperator(generateOperand(), generateOperand()), generateOperator(generateOperand(), generateOperand()));
            default:
                throw new IllegalArgumentException("Illegal Difficulty type");
        }
    }

    //region Utility API
    @NonNull
    private Expression generateOperator(@NonNull Expression left, @NonNull Expression right) {
        int operatorCode = new Random().nextInt(4) + 1;
        int priority;
        if (left instanceof Operand && right instanceof Operand) {
            priority = Priority.HIGH;
        } else {
            priority = Priority.LOW;
        }

        switch (operatorCode) {
            case Operator.ADDITION:
                return new Adder(left, right, priority);
            case Operator.SUBTRACTION:
                return new Subtractor(left, right, priority);
            case Operator.MULTIPLICATION:
                return new Multiplier(left, right, priority);
            case Operator.DIVISION:
                return new Divider(left, right, priority);
            default:
                throw new IllegalArgumentException("Illegal Operator type");
        }
    }

    @NonNull
    private Operand generateOperand() {
        return new Operand(new Random().nextInt(10));
    }
    //endregion
}