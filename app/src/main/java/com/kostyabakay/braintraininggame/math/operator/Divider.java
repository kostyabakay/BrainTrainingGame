package com.kostyabakay.braintraininggame.math.operator;

import android.support.annotation.NonNull;

import com.kostyabakay.braintraininggame.common.constant.Const;
import com.kostyabakay.braintraininggame.math.expression.BinaryExpression;
import com.kostyabakay.braintraininggame.math.expression.Expression;

/**
 * Composite Design Pattern: Leaf
 */
public class Divider extends BinaryExpression {

    public Divider(@NonNull Expression left, @NonNull Expression right) {
        super(left, right);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    //region Expression
    @Override
    public int calculate() {
        return mLeft.calculate() / mRight.calculate(); // FIXME: java.lang.ArithmeticException: divide by zero
    }
    //endregion

    //region Object
    @Override
    public String toString() {
        return mLeft + Const.Symbol.SPACE + Const.Symbol.SLASH + Const.Symbol.SPACE + mRight;
    }
    //endregion
}