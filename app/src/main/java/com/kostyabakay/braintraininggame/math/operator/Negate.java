package com.kostyabakay.braintraininggame.math.operator;

import android.support.annotation.NonNull;

import com.kostyabakay.braintraininggame.common.constant.Const;
import com.kostyabakay.braintraininggame.math.expression.Expression;
import com.kostyabakay.braintraininggame.math.expression.UnaryExpression;

/**
 * Composite Design Pattern: Leaf
 */
public class Negate extends UnaryExpression {

    public Negate(@NonNull Expression right) {
        super(right);
    }

    //region Expression
    @Override
    public int calculate() {
        return -mRight.calculate();
    }
    //endregion

    //region Object
    @Override
    public String toString() {
        return String.valueOf(mRight);
    }
    //endregion
}