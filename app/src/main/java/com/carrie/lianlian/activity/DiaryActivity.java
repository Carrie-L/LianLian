package com.carrie.lianlian.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.carrie.lianlian.R;
import com.carrie.lianlian.base.BaseActivity;
import com.carrie.lianlian.dao.Diary;
import com.carrie.lianlian.utils.Configure;
import com.carrie.lianlian.utils.LogUtil;
import com.carrie.lianlian.view.DiaryAdapter;
import com.carrie.lianlian.viewModel.DiaryViewModel;

import java.util.ArrayList;

import static android.R.attr.id;

public class DiaryActivity extends BaseActivity {

    private DiaryViewModel mViewModel;

    @Override
    protected void preView() {
        mMenuLayout = R.menu.menu_diary_list;
    }

    @Override
    protected View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.activity_diary, mBaseFrameLayout);
    }

    @Override
    protected void initData() {
        RecyclerView rvDiary = (RecyclerView) findViewById(R.id.rv_diary);
        rvDiary.setHasFixedSize(true);
        rvDiary.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvDiary.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        mViewModel = new DiaryViewModel(getApplicationContext());

        ArrayList<Diary> list = mViewModel.diaries;
        LogUtil.i(TAG, "list=" + list.size() + "," + list.toString());

        mViewModel.start();

        DiaryAdapter adapter = new DiaryAdapter(list,this);
        rvDiary.setAdapter(adapter);

        Intent intent = getIntent();
        setToolBarColor(intent.getIntExtra("Color", 0));

        LogUtil.i(TAG, mDBHelper.test());

    }

    @Override
    protected void setOnFABClick() {
        super.setOnFABClick();
        Intent intent = new Intent(this, DiaryEditActivity.class);
        intent.putExtra(Configure.ID,0);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_label:
                Toast.makeText(getApplicationContext(), "label", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mViewModel.start();
    }

    public void itemClicked(long id){
        LogUtil.i(TAG,"id : "+ id);
        Intent intent=new Intent(this, DiaryEditActivity.class);
        intent.putExtra(Configure.ID,id);
        startActivity(intent);
    }
}
