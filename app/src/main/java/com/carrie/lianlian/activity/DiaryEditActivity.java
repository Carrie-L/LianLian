package com.carrie.lianlian.activity;

import android.animation.FloatArrayEvaluator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.carrie.lianlian.App;
import com.carrie.lianlian.R;
import com.carrie.lianlian.dao.DBHelper;
import com.carrie.lianlian.dao.Diary;
import com.carrie.lianlian.utils.DateUtil;
import com.carrie.lianlian.utils.LogUtil;
import com.rengwuxian.materialedittext.MaterialEditText;

public class DiaryEditActivity extends AppCompatActivity {
    private static final String TAG = "DiaryEditActivity";
    private MaterialEditText metTitle;
    private EditText metContent;
    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_edit_diary);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle("日记本");
        }

        metTitle = (MaterialEditText) findViewById(R.id.et_diary_title);
        metContent = (EditText) findViewById(R.id.et_diary);

        mDBHelper = App.mDBHelper;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_edit_diary);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });


    }

    private void save() {
        String title = metTitle.getText().toString();
        String content = metContent.getText().toString();
        String date= DateUtil.getCurrentTime();
        LogUtil.i(TAG, "title=" + title);
        LogUtil.i(TAG, "content=" + content);
        LogUtil.i(TAG, "date=" + date);

        Diary diary = new Diary();
        diary.setTitle(title);
        diary.setContent(content);
        diary.setCreateDate(date);
        diary.setUpdateDate(date);
        mDBHelper.insertDiary(diary);


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
}
