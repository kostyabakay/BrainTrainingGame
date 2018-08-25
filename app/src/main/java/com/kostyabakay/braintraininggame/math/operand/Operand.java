package com.kostyabakay.braintraininggame.math.operand;

import com.kostyabakay.braintraininggame.math.expression.Expression;

/**
 * Composite Design Pattern: Leaf
 */
public class Operand implements Expression {

    private int mValue;

    public Operand(int value) {
        mValue = value;
    }

    //region Expression
    @Override
    public int calculate() {
        return mValue;
    }
    //endregion

    //region Object
    @Override
    public String toString() {
        return String.valueOf(mValue);
    }
    //endregion
}