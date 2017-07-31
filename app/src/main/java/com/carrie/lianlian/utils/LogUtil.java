package com.carrie.lianlian.utils;

import android.util.Log;

/**
 * Created by new on 2017/7/14.
 */

public class LogUtil {
    /**
     * true : 打印Log ; false :不打印
     */
    public static final boolean LOG = ReleaseHelper.LOG_DEBUG;
    public static final String PREFIX = "LLDiary:";

    public static void v(String tag, String msg) {
        if (LOG)
            Log.v(PREFIX + tag, msg);
    }

    public static void d(String tag, String msg) {
        if (LOG)
            Log.d(PREFIX + tag, msg);
    }

    public static void i(String tag, String msg) {
        if (LOG) {
            Log.i(PREFIX + tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LOG) {
            Log.w(PREFIX + tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LOG) {
            Log.e(PREFIX + tag, msg);
        }
    }


}
