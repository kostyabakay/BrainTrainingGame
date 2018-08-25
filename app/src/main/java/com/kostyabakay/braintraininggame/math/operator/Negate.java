package com.kostyabakay.braintraininggame.math.operator;

import android.support.annotation.NonNull;

import com.kostyabakay.braintraininggame.math.expression.Expression;
import com.kostyabakay.braintraininggame.math.expression.UnaryExpression;

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
}