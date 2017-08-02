package com.carrie.lianlian.inter;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Carrie on 2017/8/1.
 */

public interface DataSource<T> {

    //加载 多个数据
    interface LoadDataCallback<T> {
        void onDataLoaded(ArrayList<T> list);

        void onDataNotAvailable();
    }

    //获取 一个数据
    interface GetDataCallback<T> {
        void onDataLoaded(T list);

        void onDataNotAvailable();
    }

    void saveItemData(T t);

    void saveData(ArrayList<T> list);

    void getItemData(long _id, @NonNull GetDataCallback<T> callback);

    void getData(@NonNull LoadDataCallback<T> callback);

    void deleteItemData(int _id);

}
