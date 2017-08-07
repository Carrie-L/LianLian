package com.carrie.lianlian.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.carrie.lianlian.dao.Diary;
import com.carrie.lianlian.data.DiariesRepository;
import com.carrie.lianlian.inter.DataSource;
import com.carrie.lianlian.inter.DiaryDataSource;
import com.carrie.lianlian.utils.LogUtil;

import java.util.ArrayList;


/**
 * Created by new on 2017/7/28.
 */

public class DiaryViewModel extends BaseObservable implements DiaryDataSource.SearchData {
    private static final String TAG = "DiaryViewModel";

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> updateDate = new ObservableField<>();
    public final ObservableField<String> content = new ObservableField<>();
    public final ObservableField<String> label = new ObservableField<>();

    public final ObservableArrayList<Diary> diaries = new ObservableArrayList<>();

    private ArrayList<Diary> mCacheDiaries = new ArrayList<>();

    private DiariesRepository mRepository;

    private Context mContext;

    public DiaryViewModel(Context context, DiariesRepository repository) {
        mContext = context.getApplicationContext();
        mRepository = repository;
    }

    public ArrayList<Diary> getAllDiaries() {
        return mRepository.getDiaryAllList();
    }

    public void start() {
        diaries.clear();
        if (!mCacheDiaries.isEmpty()) {
            diaries.addAll(mCacheDiaries);
            LogUtil.i(TAG, "start: mCacheDiaries != empty, " + mCacheDiaries.size());
        } else {
            diaries.addAll(getAllDiaries());
            mCacheDiaries.addAll(diaries);
            LogUtil.i(TAG, "start: mCacheDiaries == empty, " + mCacheDiaries.size());
        }

    }

    @Override
    public void queryData(String queryText) {
        diaries.clear();
        diaries.addAll(mRepository.queryData(queryText));
    }

    @Override
    public void clearData() {
        if (!diaries.isEmpty()) {
            diaries.clear();
        }
        diaries.addAll(mCacheDiaries);
    }
}
