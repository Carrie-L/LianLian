package com.carrie.lianlian.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import static android.R.attr.value;

/**
 * Created by Carrie on 2017/8/3.
 */

public abstract class LLBaseAdapter extends RecyclerView.Adapter<LLBaseViewHolder> {

    @Override
    public LLBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding  binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),viewType,parent,false);
        bindVariable(binding);
        return new LLBaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(LLBaseViewHolder holder, int position) {
        Object object = getObjForPosition(position);
        holder.bind(object);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    protected abstract Object getObjForPosition(int position);

    protected abstract int getLayoutIdForPosition(int position);

    protected void bindVariable(ViewDataBinding binding){

    }

}
