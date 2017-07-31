package com.carrie.lianlian.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.carrie.lianlian.R;
import com.carrie.lianlian.base.BaseActivity;
import com.carrie.lianlian.utils.ColorDeeperUtil;
import com.carrie.lianlian.utils.LogUtil;
import com.carrie.lianlian.view.DiaryAdapter;

import static com.carrie.lianlian.utils.ColorDeeperUtil.colorBurn;


public class DiaryActivity extends BaseActivity {

    private SearchView searchView;

    @Override
    protected void preView() {

    }

    @Override
    protected View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.activity_diary,mBaseFrameLayout);
    }

    @Override
    protected void initData() {
        RecyclerView rvDiary = (RecyclerView) findViewById(R.id.rv_diary);
        rvDiary.setHasFixedSize(true);
        rvDiary.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        DiaryAdapter adapter = new DiaryAdapter();
        rvDiary.setAdapter(adapter);

        Intent intent=getIntent();
        setToolBarColor(intent.getIntExtra("Color",0));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu_toolbar, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                menuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_label:
                Toast.makeText(getApplicationContext(),"label",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
