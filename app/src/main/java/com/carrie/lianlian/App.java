package com.carrie.lianlian;

import android.app.Application;
import android.content.SharedPreferences;

import com.carrie.lianlian.utils.Configure;
import com.carrie.lianlian.utils.CrashHandler;

/**
 * Created by new on 2017/7/28.
 */

public class App extends Application {
    public static SharedPreferences mSPConfig;
    public static int mScreenWidth;
    public static int mScreenHeight;


    @Override
    public void onCreate() {
        super.onCreate();

        setSharedPreference();
        initCrashHandler();

    }

    private void setSharedPreference(){
        mSPConfig=getSharedPreferences(Configure.SP_CONFIG_NAME,MODE_PRIVATE);
    }

    private void initCrashHandler(){

        CrashHandler crashHandler= CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

    }

}
