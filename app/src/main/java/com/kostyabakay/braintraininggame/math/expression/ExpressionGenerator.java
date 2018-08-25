package com.kostyabakay.braintraininggame.math.expression;

import com.kostyabakay.braintraininggame.common.def.Difficulty;
import com.kostyabakay.braintraininggame.math.operand.Operand;
import com.kostyabakay.braintraininggame.math.operator.Adder;

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
        return new Adder(new Operand(4), new Operand(5));
    }
}