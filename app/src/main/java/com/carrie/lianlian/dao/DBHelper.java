package com.carrie.lianlian.dao;

import android.database.sqlite.SQLiteDatabase;


/**
 * 数据库管理器
 * Created by Administrator on 2016/6/23 0023.
 */
public class DBHelper {
    private static final String TAG = "DBHelper";

    private DaoSession mDaoSession;
    public SQLiteDatabase db;
    public DiaryDao diaryDao;
    private MoneyDao moneyDao;
    private MoneyAccountDao moneyAccountDao;
    private MoneyClassifyDao moneyClassifyDao;
    private PlanDao planDao;
    private AnnDao annDao;

    private DBHelper(Builder builder) {
        mDaoSession = builder.mDaoSession;
        db = builder.mDB;
        initDao();
    }

    public static final class Builder {
        private DaoSession mDaoSession;
        private SQLiteDatabase mDB;

        public Builder(DaoSession daoSession, SQLiteDatabase db) {
            this.mDaoSession = daoSession;
            this.mDB = db;
        }

        public DBHelper build() {
            return new DBHelper(this);
        }
    }

    private void initDao() {
        diaryDao = mDaoSession.getDiaryDao();
    }

    public String test() {
        return "哈哈哈哈哈";
    }


    //==============================日记Dairy===========================================


    //==============================标签===========================================


    //==============================便利贴===========================================
    //==============================计划表===========================================


}
