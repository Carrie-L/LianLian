package com.carrie.lianlian.data;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.carrie.lianlian.dao.Diary;
import com.carrie.lianlian.dao.DiaryDao;
import com.carrie.lianlian.inter.DataSource;
import com.carrie.lianlian.inter.DiaryDataSource;
import com.carrie.lianlian.model.Label;

import java.util.ArrayList;

import static com.carrie.lianlian.App.mDBHelper;

/**
 * Created by Carrie on 2017/8/3.
 */

public class DiariesRepository implements DiaryDataSource {
    private DiaryDao diaryDao = mDBHelper.diaryDao;
    //    private final DiaryDataSource mDiaryLocalDataSource;
    private static DiariesRepository INSTANCE;

//    private DiariesRepository(@NonNull DiaryDataSource diaryLocalDataSource){
//        mDiaryLocalDataSource=diaryLocalDataSource;
//    }

    public static DiariesRepository getInstance() {
        if (INSTANCE == null) {
            return new DiariesRepository();
        }
        return INSTANCE;
    }

    public void updateItemData(Diary diary) {
        updateDiary(diary);
    }

    @Override
    public void saveItemData(Diary diary) {
        insertDiary(diary);
    }

    @Override
    public void saveData(ArrayList<Diary> list) {

    }

    @Override
    public void deleteItemData(int _id) {

    }

    @Override
    public void getItemData(long _id, @NonNull GetDataCallback<Diary> callback) {

    }

    @Override
    public void getData(@NonNull LoadDataCallback<Diary> callback) {

    }

    public ArrayList<Diary> queryData(String queryText) {
        return queryDiaries(queryText);
    }

    public Diary getItemData(long id) {
        return getDiary(id);
    }

    // \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public long insertDiary(Diary entity) {
        return diaryDao.insert(entity);
    }

    public void updateDiary(Diary entity) {
        diaryDao.update(entity);
    }

    public ArrayList<Diary> getDiaryAllList() {
        return (ArrayList<Diary>) diaryDao.queryBuilder().orderDesc(DiaryDao.Properties.UpdateDate).list();
    }

    public ArrayList<Diary> getDiaries(String label) {
        return (ArrayList<Diary>) diaryDao.queryBuilder().where(DiaryDao.Properties.Label.eq(label)).orderAsc(DiaryDao.Properties.UpdateDate).list();
    }

    public Diary getDiary(long id) {
        return diaryDao.load(id);
    }

    public void deleteDiaryItem(Diary entity) {
        diaryDao.delete(entity);
    }

    private ArrayList<Diary> queryDiaries(String queryText) {
        return (ArrayList<Diary>) diaryDao.queryBuilder().whereOr(DiaryDao.Properties.Title.like("%"+queryText+"%"), DiaryDao.Properties.Content.like("%"+queryText+"%")).list();
    }


    //  \\\\\\\\\\\\\\\     标签      \\\\\\\\\\\\
    public ArrayList<Label> getLabels() {
        ArrayList<Label> list = new ArrayList<>();
        Label label;
        Cursor cursor = mDBHelper.db.rawQuery("select DISTINCT LABEL FROM DIARY", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                label = new Label(cursor.getString(0));
                list.add(label);
            }
            cursor.close();
        }
        return list;
    }

}
