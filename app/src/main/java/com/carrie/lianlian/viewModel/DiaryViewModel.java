package com.carrie.lianlian.viewModel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.carrie.lianlian.dao.Diary;
import com.carrie.lianlian.inter.DataSource;
import com.carrie.lianlian.inter.DiaryDataSource;

import java.util.ArrayList;

/**
 * Created by new on 2017/7/28.
 */

public class DiaryViewModel extends BaseObservable {

    public final ObservableField<String> title = new ObservableField<>();



}
