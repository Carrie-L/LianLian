package com.carrie.lianlian.viewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.carrie.lianlian.BR;
import com.carrie.lianlian.activity.DiaryEditActivity;
import com.carrie.lianlian.dao.Diary;
import com.carrie.lianlian.data.DiariesRepository;
import com.carrie.lianlian.inter.DataSource;
import com.carrie.lianlian.inter.DiaryDataSource;
import com.carrie.lianlian.utils.LogUtil;

import java.util.ArrayList;

import io.fabric.sdk.android.services.concurrency.Task;

/**
 * Created by new on 2017/7/28.
 */

public class DiaryViewModel extends BaseObservable {

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> updateDate = new ObservableField<>();
    public final ObservableField<String> content = new ObservableField<>();
    public final ObservableField<String> label = new ObservableField<>();

    public final ObservableArrayList<Diary> diaries = new ObservableArrayList<>();

    private final ObservableField<Diary> mDiaryObservable = new ObservableField<>();

    private DiariesRepository mRepository =new DiariesRepository();

    private Context mContext;

    public DiaryViewModel(Context context){
        mContext=context.getApplicationContext();
    }

    public ArrayList<Diary> getAllDiaries(){
        return mRepository.getDiaryAllList();
    }

    public void start(){
        diaries.clear();
        diaries.addAll(mRepository.getDiaryAllList());
    }







}
