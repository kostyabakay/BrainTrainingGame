package com.kostyabakay.braintraininggame.math.expression;

import android.support.annotation.NonNull;

import com.kostyabakay.braintraininggame.math.expression.Expression;

public abstract class UnaryExpression implements Expression {

    @NonNull
    protected Expression mRight;

    public UnaryExpression(@NonNull Expression right) {
        mRight = right;
    }
}