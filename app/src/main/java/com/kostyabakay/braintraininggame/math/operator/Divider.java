package com.kostyabakay.braintraininggame.math.operator;

import android.support.annotation.NonNull;

import com.kostyabakay.braintraininggame.common.constant.Const;
import com.kostyabakay.braintraininggame.math.expression.BinaryExpression;
import com.kostyabakay.braintraininggame.math.expression.Expression;

/**
 * Composite Design Pattern: Leaf
 */
public class Divider extends BinaryExpression {

    @Priority
    private int mPriority;

    public Divider(@NonNull Expression left, @NonNull Expression right, @Priority int priority) {
        super(left, right);
        mPriority = priority;
    }

    //region Expression
    @Override
    public int calculate() {
        return mLeft.calculate() / mRight.calculate(); // FIXME: java.lang.ArithmeticException: divide by zero
    }
    //endregion

    //region BinaryExpression
    @Override
    public int getPriority() {
        return mPriority;
    }
    //endregion

    //region Object
    @Override
    public String toString() {
        switch (mPriority) {
            case Priority.LOW:
                return mLeft + Const.Symbol.SPACE + Const.Symbol.SLASH + Const.Symbol.SPACE + mRight;
            case Priority.HIGH:
                return Const.Symbol.LEFT_BRACKET + mLeft
                        + Const.Symbol.SPACE + Const.Symbol.SLASH
                        + Const.Symbol.SPACE + mRight + Const.Symbol.RIGHT_BRACKET;
            default:
                throw new IllegalArgumentException("Illegal Priority type");
        }
    }
    //endregion
}