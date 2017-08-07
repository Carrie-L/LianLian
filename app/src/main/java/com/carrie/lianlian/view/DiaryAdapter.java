package com.carrie.lianlian.view;

import android.app.Activity;
import android.databinding.ViewDataBinding;

import com.carrie.lianlian.BR;
import com.carrie.lianlian.R;
import com.carrie.lianlian.base.LLBaseAdapter;
import com.carrie.lianlian.dao.Diary;
import com.carrie.lianlian.utils.LogUtil;

import java.util.ArrayList;

/**
 * 日记列表适配器
 * Created by Carrie on 2017/8/3.
 */

public class DiaryAdapter extends LLBaseAdapter {
    private ArrayList<Diary> list;
    private Activity activity;

    public DiaryAdapter(ArrayList<Diary> list, Activity activity) {
        this.list = list;
        this.activity=activity;
    }

    public void setList(ArrayList<Diary> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    protected Object getObjForPosition(int position) {
        return list.get(position);
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.item_diary;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    protected void bindVariable(ViewDataBinding binding) {
        super.bindVariable(binding);
        binding.setVariable(BR.activity,activity);
    }
}
