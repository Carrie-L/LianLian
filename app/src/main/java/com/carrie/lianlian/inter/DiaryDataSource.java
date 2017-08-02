package com.carrie.lianlian.inter;

import android.support.annotation.NonNull;

import com.carrie.lianlian.dao.Diary;

import java.util.ArrayList;

/**
 * Created by Carrie on 2017/8/1.
 */

public interface DiaryDataSource extends DataSource<Diary>{
    @Override
    void saveItemData(Diary diary);

    @Override
    void saveData(ArrayList<Diary> list);

    @Override
    void deleteItemData(int _id);

    @Override
    void getItemData(long _id, @NonNull GetDataCallback<Diary> callback);

    @Override
    void getData(@NonNull LoadDataCallback<Diary> callback);
}
