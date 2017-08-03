package com.carrie.lianlian.viewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.carrie.lianlian.BR;
import com.carrie.lianlian.R;
import com.carrie.lianlian.activity.DiaryEditActivity;
import com.carrie.lianlian.dao.Diary;
import com.carrie.lianlian.data.DiariesRepository;
import com.carrie.lianlian.utils.DateUtil;
import com.carrie.lianlian.utils.LogUtil;

import static android.R.attr.id;
import static com.carrie.lianlian.R.string.diary;

/**
 * Created by Carrie on 2017/8/1.
 */

public class DiaryEditViewModel extends BaseObservable {
    private static final String TAG = "DiaryEditViewModel";
    private final Context mContext;  // To avoid leaks, this must be an Application Context.

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> content = new ObservableField<>();
    public final ObservableField<String> createDate = new ObservableField<>();
    public final ObservableField<String> updateDate = new ObservableField<>();
    public final ObservableInt mood = new ObservableInt();
    public final ObservableInt weather = new ObservableInt();
    public final ObservableInt bg = new ObservableInt();
    public final ObservableInt textSize = new ObservableInt();

    public final ObservableField<Diary> mDiary = new ObservableField<>();

    private long mDiaryId;
    private boolean mIsNewTask;
    private boolean mIsDataLoaded = false;

    private DiariesRepository mDiaryRepository;

    public DiaryEditViewModel(Context context) {
        mContext = context;
        mDiaryRepository=DiariesRepository.getInstance();
    }

    /**
     * 判断是add diary，还是 diary detail
     */
    public void start(long diaryId) {
        mDiaryId = diaryId;
        if (diaryId == 0) {
            mIsNewTask = true;
            return;
        }

        mIsNewTask = false;

        Diary diary =mDiaryRepository.getItemData(diaryId);
        LogUtil.i(TAG,"diary="+diary.toString());
        mDiary.set(diary);
        title.set(diary.getTitle());
        content.set(mDiary.get().getContent());
        createDate.set(diary.getCreateDate());


    }

    public void openEditable(){
        if(mIsNewTask){
           return;
        }


    }

    public void save(){
       if(mIsNewTask){
           createDiary();
       }else{
           updateDiary();
       }
    }

    private void createDiary(){
        Diary diary = new Diary();
        diary.setTitle(title.get());
        diary.setContent(content.get());

        LogUtil.i(TAG, "title=" + title.get());
        LogUtil.i(TAG, "content=" + content.get());

        diary.setCreateDate(DateUtil.getCurrentDateAndWeek());
        diary.setUpdateDate(DateUtil.getCurrentTime());
        mDiaryRepository.saveItemData(diary);
    }

    private void updateDiary(){
        Diary diary = new Diary();
        diary.setTitle(title.get());
        diary.setContent(content.get());
        diary.setCreateDate(createDate.get());
        diary.setUpdateDate(DateUtil.getCurrentDateAndWeek());
        mDiaryRepository.saveItemData(diary);
    }

    private void setupSnackBar(View container){
        Snackbar.make(container, mContext.getString(R.string.save_success), Snackbar.LENGTH_LONG).setAction(mContext.getString(R.string.label), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, LabelAddActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                mContext.startActivity(intent);
//                intent.putExtra(BUNDLE, diary);
//                startActivityForResult(intent, REQUEST_EDIT);
            }
        }).show();
    }


}
