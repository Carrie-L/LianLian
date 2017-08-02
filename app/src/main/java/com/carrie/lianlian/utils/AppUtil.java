package com.carrie.lianlian.utils;

import android.support.annotation.Nullable;

import com.carrie.lianlian.App;

/**
 * Created by new on 2017/7/14.
 */

public class AppUtil {

    public static void setSPDBUpdate(boolean update){
        App.mSPConfig.edit().putBoolean("DB_UPDATE",update).apply();
    }




    // \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\     以下是公共工具方法       \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public static <T> T checkNotNull(T reference) {
        if(reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if(reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }



}
