package com.carrie.lianlian.activity;

import android.content.Intent;
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
import com.carrie.lianlian.databinding.ActivityDiaryBinding;
import com.carrie.lianlian.utils.Configure;
import com.carrie.lianlian.utils.LogUtil;
import com.carrie.lianlian.view.DiaryAdapter;
import com.carrie.lianlian.viewModel.DiaryViewModel;

import java.util.ArrayList;

public class DiaryActivity extends BaseActivity {
    private DiaryViewModel mViewModel;
    private ActivityDiaryBinding binding;

    @Override
    protected void preView() {
        mMenuLayout = R.menu.menu_diary_list;
    }

    @Override
    protected View initView(LayoutInflater inflater) {
        binding = ActivityDiaryBinding.inflate(inflater,mBaseFrameLayout,true);
        return binding.getRoot();
    }

    @Override
    protected void initData() {
        mViewModel = new DiaryViewModel(getApplicationContext(),mDiariesRepository);
        binding.setViewmodel(mViewModel);

        RecyclerView rvDiary =binding.rvDiaries;
        rvDiary.setHasFixedSize(true);
        rvDiary.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvDiary.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        DiaryAdapter adapter = new DiaryAdapter(new ArrayList<Diary>(0),this);
        rvDiary.setAdapter(adapter);

        Intent intent = getIntent();
        setToolBarColor(intent.getIntExtra("Color", 0));

        setSearchData(mViewModel);
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
        mViewModel.start();
    }

    /**
     * 这个方法用databinding在xml中绑定监听
     * @param id
     */
    public void itemClicked(long id){
        LogUtil.i(TAG,"search是否展开："+menuItem.isActionViewExpanded());
        if(menuItem.isActionViewExpanded()){
            menuItem.collapseActionView();
        }
        Intent intent=new Intent(this, DiaryEditActivity.class);
        intent.putExtra(Configure.ID,id);
        startActivity(intent);
    }
}
