package com.carrie.lianlian.data;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.carrie.lianlian.activity.DiaryEditActivity;
import com.carrie.lianlian.dao.Diary;
import com.carrie.lianlian.utils.Configure;
import com.carrie.lianlian.view.DiaryAdapter;

import java.util.ArrayList;

import static android.R.attr.id;

/**
 * Contains {@link BindingAdapter}s for the {@link Diary} list.
 * Created by Carrie on 2017/8/4.
 */
public class DiaryListBindings {

    @SuppressWarnings("unchecked")
    @BindingAdapter("app:items")
    public static void setItems(RecyclerView recyclerView, ArrayList<Diary> list) {
        DiaryAdapter adapter = (DiaryAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setList(list);
        }
    }

//    @BindingAdapter("app:onClick")
//    public static void setOnClick(Context context,long id){
//        Intent intent=new Intent(context, DiaryEditActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra(Configure.ID,id);
//        context.startActivity(intent);
//    }

//    public static void setOnWipeClick(long id,RecyclerView recyclerView){
//
//    }



}
