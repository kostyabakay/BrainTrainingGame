package com.kostyabakay.braintraininggame.common.logger;

import android.support.annotation.NonNull;
import android.util.Log;

public class L {

    // FIXME: android.arch.core.BuildConfig not found
    // private static final Boolean DEBUG = BuildConfig.DEBUG;
    private static final Boolean DEBUG = true;

    /**
     * Prints info message.
     *
     * @param msg message value
     */
    public static void i(@NonNull final String msg) {
        if (DEBUG) {
            final Throwable t = new Throwable();
            final StackTraceElement[] elements = t.getStackTrace();

            final String callerClassName = elements[1].getClassName();
            final String callerMethodName = elements[1].getMethodName();

            Log.i(callerClassName, callerMethodName + " :: " + msg);
        }
    }

    /**
     * Prints debug message.
     *
     * @param msg message value
     */
    public static void d(@NonNull final String msg) {
        if (DEBUG) {
            final Throwable t = new Throwable();
            final StackTraceElement[] elements = t.getStackTrace();

            final String callerClassName = elements[1].getClassName();
            final String callerMethodName = elements[1].getMethodName();

            Log.d(callerClassName, callerMethodName + " :: " + msg);
        }
    }

    /**
     * Prints error message.
     *
     * @param msg message value
     */
    public static void e(@NonNull final String msg) {
        if (DEBUG) {
            final Throwable t = new Throwable();
            final StackTraceElement[] elements = t.getStackTrace();

            final String callerClassName = elements[1].getClassName();
            final String callerMethodName = elements[1].getMethodName();

            Log.e(callerClassName, callerMethodName + " :: " + msg);
        }
    }

    /**
     * Prints error message.
     *
     * @param msg message value
     */
    public static void e(@NonNull final String msg, @NonNull Throwable error) {
        if (DEBUG) {
            final Throwable t = new Throwable();
            final StackTraceElement[] elements = t.getStackTrace();

            final String callerClassName = elements[1].getClassName();
            final String callerMethodName = elements[1].getMethodName();

            Log.e(callerClassName, callerMethodName + " :: " + msg, error);
        }
    }
}