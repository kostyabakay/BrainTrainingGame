package com.kostyabakay.braintraininggame.math.expression;

import android.support.annotation.NonNull;

/**
 * Composite Design Pattern: Container
 */
public abstract class BinaryExpression extends UnaryExpression {

    @NonNull
    protected Expression mLeft;

    public BinaryExpression(@NonNull Expression left, @NonNull Expression right) {
        super(right);
        mLeft = left;
    }

    public abstract int getPriority();
}