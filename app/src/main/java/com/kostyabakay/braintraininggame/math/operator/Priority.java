package com.kostyabakay.braintraininggame.math.operator;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@IntDef({Priority.LOW, Priority.HIGH})
public @interface Priority {
    int LOW = 1;
    int HIGH = 2;
}