package com.carrie.lianlian.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.carrie.lianlian.R;
import com.carrie.lianlian.databinding.ActivityDiaryEditBinding;
import com.carrie.lianlian.utils.Configure;
import com.carrie.lianlian.viewModel.DiaryEditViewModel;
import com.rengwuxian.materialedittext.MaterialEditText;

import static android.R.attr.id;

public class DiaryEditActivity extends AppCompatActivity {
    private static final String TAG = "DiaryEditActivity";
    private MaterialEditText metTitle;
    private EditText metContent;
    private ActivityDiaryEditBinding binding;
    private DiaryEditViewModel mViewModel;
    private long mDiaryId;
    private FloatingActionButton fab;

    private AppBarState state;
    private enum AppBarState{
        EXPANDED,//展开
        COLLAPSED,//折叠
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_diary_edit);

        bindViewModel();
        setupToolBar();

        metTitle = binding.includeContent.etDiaryTitle;
        metContent = binding.includeContent.etDiary;

        mDiaryId=getIntent().getLongExtra(Configure.ID,0);
        mViewModel.start(mDiaryId);


        fab = binding.includeContent.fabEditDiary;

        binding.appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset==0){//展开状态
                    state=AppBarState.EXPANDED;
                    fab.setVisibility(View.INVISIBLE);
                    fab.setClickable(false);
                }else if(Math.abs(verticalOffset)>=binding.appBar.getTotalScrollRange()){//折叠状态
                    state=AppBarState.COLLAPSED;
                    fab.setVisibility(View.VISIBLE);
                    fab.setClickable(true);
                }
            }
        });

        metContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                binding.appBar.setExpanded(false);
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.save();
//                showSnackBar();
            }
        });

    }

    private void showSnackBar(){
        Snackbar.make(binding.diaryContainer, getString(R.string.save_success), Snackbar.LENGTH_LONG).setActionTextColor(getResources().getColor(R.color.pink_light)).setAction(getString(R.string.label), new View.OnClickListener() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_diary_dtl, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_edit:
                Toast.makeText(getApplicationContext(), "edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_share:
                Toast.makeText(getApplicationContext(), "share", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindViewModel(){
        mViewModel=new DiaryEditViewModel(getApplicationContext());
        binding.setViewmodel(mViewModel);
        binding.includeContent.setViewmodel(mViewModel);
        binding.includeHeader.setViewmodel(mViewModel);
    }

    private void setupToolBar(){
        setSupportActionBar(binding.toolbarEditDiary);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(R.string.title_diary);
        }
    }
}
