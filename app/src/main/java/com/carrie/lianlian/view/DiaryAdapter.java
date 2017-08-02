package com.carrie.lianlian.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carrie.lianlian.R;


/**
 * Created by Carrie on 2017/7/31.
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder> {


    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiaryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diary,parent,false));
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    @Override
    public void onBindViewHolder(DiaryViewHolder holder, int position) {
        holder.tvDiary.setText("呵呵");
    }

    public static class DiaryViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDiary;
        public DiaryViewHolder(View itemView) {
            super(itemView);
            tvDiary= (TextView) itemView.findViewById(R.id.tv_diary);
        }
    }


}
