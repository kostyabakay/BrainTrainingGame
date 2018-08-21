package com.kostyabakay.braintraininggame.common.def;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@StringDef({DifficultyDef.EASY, DifficultyDef.MEDIUM, DifficultyDef.HARD})
public @interface DifficultyDef {
    String EASY = "1"; // TODO: Implement IntDef
    String MEDIUM = "2";
    String HARD = "3";
}