package com.carrie.lianlian.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.carrie.lianlian.App;
import com.carrie.lianlian.fragment.MainFragment;
import com.carrie.lianlian.model.Label;

import java.util.ArrayList;


/**
 * 数据库管理器
 * Created by Administrator on 2016/6/23 0023.
 */
public class DBHelper {
    private static final String TAG = "DBHelper";

    private DaoSession mDaoSession;
    private SQLiteDatabase db;
    private DiaryDao diaryDao;
    private MoneyDao moneyDao;
    private MoneyAccountDao moneyAccountDao;
    private MoneyClassifyDao moneyClassifyDao;
    private PlanDao planDao;
    private AnnDao annDao;

    private DBHelper (Builder builder){
        mDaoSession=builder.mDaoSession;
        db=builder.mDB;
        initDao();
    }

    public static final class Builder{
        private DaoSession mDaoSession;
        private SQLiteDatabase mDB;

        public Builder(DaoSession daoSession, SQLiteDatabase db) {
            this.mDaoSession = daoSession;
            this.mDB = db;
        }

        public DBHelper build(){
            return new DBHelper(this);
        }
    }

    private void initDao() {
        diaryDao = mDaoSession.getDiaryDao();
    }

    public String test(){
        return "哈哈哈哈哈";
    }


    //==============================日记Dairy===========================================
    public long insertDiary(Diary entity) {
        return diaryDao.insert(entity);
    }

    public void updateDiary(Diary entity) {
        diaryDao.update(entity);
    }

    public ArrayList<Diary> getDiaryAllList() {
        return (ArrayList<Diary>) diaryDao.queryBuilder().orderDesc(DiaryDao.Properties.UpdateDate).list();
    }

    public ArrayList<Diary> getDiaries(String label){
        return (ArrayList<Diary>)  diaryDao.queryBuilder().where(DiaryDao.Properties.Label.eq(label)).orderAsc(DiaryDao.Properties.UpdateDate).list();
    }

    public void deleteDiaryItem(Diary entity) {
        diaryDao.delete(entity);
    }


    //==============================标签===========================================
    public ArrayList<Label> getLabels() {
        ArrayList<Label> list=new ArrayList<>();
        Label label;
        Cursor cursor=db.rawQuery("select DISTINCT LABEL FROM DIARY",null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                label=new Label(cursor.getString(0));
                list.add(label);
            }
            cursor.close();
        }
       return list;
    }

    //==============================便利贴===========================================
    //==============================计划表===========================================


}
