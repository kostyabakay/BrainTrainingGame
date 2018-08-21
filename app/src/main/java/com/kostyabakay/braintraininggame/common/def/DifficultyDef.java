package com.kostyabakay.braintraininggame.common.def;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@IntDef({DifficultyDef.EASY, DifficultyDef.MEDIUM, DifficultyDef.HARD})
public @interface DifficultyDef {
    int EASY = 1;
    int MEDIUM = 2;
    int HARD = 3;
}