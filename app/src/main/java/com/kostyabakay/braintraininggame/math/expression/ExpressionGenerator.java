package com.kostyabakay.braintraininggame.math.expression;

import com.kostyabakay.braintraininggame.common.def.Difficulty;
import com.kostyabakay.braintraininggame.math.operand.Operand;
import com.kostyabakay.braintraininggame.math.operator.Adder;

import java.util.Random;

/**
 * Singleton Design Pattern
 */
public class ExpressionGenerator {

    private static volatile ExpressionGenerator sInstance;

    private ExpressionGenerator() {
    }

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
                return new Adder(new Operand(generateOperand()), new Operand(generateOperand()));
            default:
                throw new UnsupportedOperationException();
        }
    }

    //region Utility API
    private int generateOperand() {
        return new Random().nextInt(10);
    }
    //endregion
}