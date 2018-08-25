package com.kostyabakay.braintraininggame.math.operator;

import android.support.annotation.NonNull;

import com.kostyabakay.braintraininggame.math.expression.BinaryExpression;
import com.kostyabakay.braintraininggame.math.expression.Expression;

public class Multiplier extends BinaryExpression {

    public Multiplier(@NonNull Expression left, @NonNull Expression right) {
        super(left, right);
    }

    //region Expression
    @Override
    public int calculate() {
        return mLeft.calculate() * mRight.calculate();
    }
    //endregion
}