package com.kostyabakay.braintraininggame.math.operator;

import android.support.annotation.NonNull;

import com.kostyabakay.braintraininggame.common.constant.Const;
import com.kostyabakay.braintraininggame.math.expression.BinaryExpression;
import com.kostyabakay.braintraininggame.math.expression.Expression;

/**
 * Composite Design Pattern: Leaf
 */
public class Subtractor extends BinaryExpression {

    public Subtractor(@NonNull Expression left, @NonNull Expression right) {
        super(left, right);
    }

    //region Expression
    @Override
    public int calculate() {
        return mLeft.calculate() - mRight.calculate();
    }
    //endregion

    //region Object
    @Override
    public String toString() {
        return mLeft + Const.Symbol.SPACE + Const.Symbol.MINUS + Const.Symbol.SPACE + mRight;
    }
    //endregion
}