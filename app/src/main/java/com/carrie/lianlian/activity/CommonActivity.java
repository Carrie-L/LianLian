package com.carrie.lianlian.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.carrie.lianlian.R;
import com.carrie.lianlian.base.BaseActivity;
import com.carrie.lianlian.utils.LogUtil;

import static android.graphics.BitmapFactory.decodeResource;

public class CommonActivity extends BaseActivity {

    @Override
    protected void preView() {

    }

    @Override
    protected View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.activity_common,mBaseFrameLayout);
    }

    @Override
    protected void initData() {
        setToolBarColor(getIntent().getIntExtra("Color",0));



    }
}
