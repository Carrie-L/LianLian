package com.carrie.lianlian.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Carrie on 2017/7/28.
 */

public class DateUtil {
    public static final Integer TIME_MODE_HH_MM = 0;
    public static final Integer TIME_MODE_HH_MM_SS = 1;

    public static String setTimeMode(int timeMode){
        switch (timeMode) {
            case 1:
                return "HH:mm:ss";
            case 0:
                return "HH:mm";
        }
        return "HH:mm";
    }


    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }

    public static String getCurrentTime1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }

    public static String getCurrentTime2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }

    public static String getCurrentTime3() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }


}
