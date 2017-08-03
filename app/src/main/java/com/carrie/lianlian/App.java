package com.carrie.lianlian;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.carrie.lianlian.dao.DBHelper;
import com.carrie.lianlian.dao.DaoMaster;
import com.carrie.lianlian.dao.DaoSession;
import com.carrie.lianlian.dao.Diary;
import com.carrie.lianlian.dao.DiaryDao;
import com.carrie.lianlian.utils.Configure;
import com.carrie.lianlian.utils.CrashHandler;
import com.carrie.lianlian.utils.LogUtil;


/**
 * Created by new on 2017/7/28.
 */

public class App extends Application {
    private static final String TAG = "App";
    public static SharedPreferences mSPConfig;
    public static int mScreenWidth;
    public static int mScreenHeight;

    private static final String DATABASE_NAME = "LLDiary.db";

    private DaoMaster daoMaster;
    public SQLiteDatabase db;
    public static String mAppVersion;
    public static int mVersionCode;
    private long mDiaryId;
    private DaoSession daoSession;
    public static DBHelper mDBHelper;


    @Override
    public void onCreate() {
        super.onCreate();

        setSharedPreference();
        initCrashHandler();
        getPackageVersion();
        getDbHelper();
        checkUpdateDB();

    }

    private void setSharedPreference() {
        mSPConfig = getSharedPreferences(Configure.SP_CONFIG_NAME, MODE_PRIVATE);
    }

    private void initCrashHandler() {
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    private void getPackageVersion() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            mAppVersion = packageInfo.versionName;
            mVersionCode = packageInfo.versionCode;
            LogUtil.i("App", "mAppVersion=" + mAppVersion + ",mVersionCode=" + mVersionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getDbHelper(){
        mDBHelper = new DBHelper.Builder(getDaoSession(), db).build();
    }

    private void checkUpdateDB() {
        boolean hasUpdate = mSPConfig.getBoolean("DB_UPDATE", false);
        if (hasUpdate) {
            updateDB();
            if (mDiaryId > 0) {//因为id从1开始自增，所以如果插入成功，返回的id一定是大于0的
                mSPConfig.edit().putBoolean("DB_UPDATE", false).apply();
            }
        }
    }

    private void updateDB() {
        //第一步，先将原有数据全部储存到TempDb中
        //第二步，删除现有表，新建新表

        //第一步，获取是哪个表发生了改变
        //第二步，将这个表中的所有数据存储到临时表中
        //第三部，新建新表
        //第四步，将数据从临时表中转移到新表中
        //第五步，删除临时表

        String sqlDiary = "ALTER TABLE DIARY RENAME TO DIARY_TEMP";
        db.execSQL(sqlDiary);
        //		String sqlAnn = "ALTER TABLE ANN RENAME TO ANN_TEMP";
//		db.execSQL(sqlAnn);

        DiaryDao.createTable(db, true);
        Cursor cursor = db.rawQuery("SELECT * FROM DIARY_TEMP", null);
        Diary diary;
        if (cursor != null) {
            while (cursor.moveToNext()) {
//                diary = new Diary();
//                diary.setData(cursor, diary);
//                LogUtil.i("DaoMaster", "diary=" + diary.toString());
//                mDiaryId = mDBHelper.insertDiary(diary);
//                LogUtil.i("DaoMaster", "mDiaryId=" + mDiaryId);
            }
            cursor.close();
        }
    }

    public DaoSession getDaoSession() {
        LogUtil.i(TAG, "getDaoSession_____________________");
        if (daoMaster == null) {
            daoMaster = getDaoMaster();
            LogUtil.i(TAG, "getDaoSession: daoMaster == null");
        }
        if (daoSession == null) {
            daoSession = daoMaster.newSession();
            LogUtil.i(TAG, "getDaoSession: daoSession == null");
        }
        return daoSession;
    }

    public DaoMaster getDaoMaster() {
        DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), DATABASE_NAME, null);
        db = openHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        return daoMaster;
    }

}
