package com.carrie.lianlian.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.carrie.lianlian.dao.Diary;
import com.carrie.lianlian.data.DiariesRepository;
import com.carrie.lianlian.inter.OnSavedListener;
import com.carrie.lianlian.utils.DateUtil;
import com.carrie.lianlian.utils.LogUtil;


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

    public final ObservableBoolean isShowFAB = new ObservableBoolean();

    public final ObservableField<Diary> mDiary = new ObservableField<>();

    private long mDiaryId;
    private boolean mIsNewTask;
    private boolean mIsDataLoaded = false;

    private DiariesRepository mDiaryRepository;

    private OnSavedListener mSaveListener;

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

    private void navigateOnDiarySaved(){
        if(mSaveListener!=null){
            mSaveListener.onSaved();
        }
    }

    private void createDiary(){
        Diary diary = new Diary();
        diary.setTitle(title.get());
        diary.setContent(content.get());
        diary.setCreateDate(DateUtil.getCurrentDateAndWeek());
        diary.setUpdateDate(DateUtil.getCurrentDateAndWeek());
        mDiaryRepository.saveItemData(diary);
        navigateOnDiarySaved();
    }

    private void updateDiary(){
        Diary diary = new Diary();
        diary.setId(mDiaryId);
        diary.setTitle(title.get());
        diary.setContent(content.get());
        diary.setCreateDate(createDate.get());
        diary.setUpdateDate(DateUtil.getCurrentDateAndWeek());
        mDiaryRepository.updateItemData(diary);
        navigateOnDiarySaved();
    }

    public void isShowFAB(boolean bool){
        isShowFAB.set(bool);
    }


}
