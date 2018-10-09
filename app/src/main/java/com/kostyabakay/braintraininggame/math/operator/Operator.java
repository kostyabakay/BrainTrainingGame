package com.kostyabakay.braintraininggame.math.operator;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@IntDef({Operator.NEGATION, Operator.ADDITION, Operator.SUBTRACTION,
        Operator.MULTIPLICATION, Operator.DIVISION})
public @interface Operator {
    int NEGATION = 0;
    int ADDITION = 1;
    int SUBTRACTION = 2;
    int MULTIPLICATION = 3;
    int DIVISION = 4;
}