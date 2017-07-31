package com.carrie.lianlian.activity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.carrie.lianlian.R;
import com.carrie.lianlian.base.BaseActivity;
import com.carrie.lianlian.utils.LogUtil;

public class PlanActivity extends BaseActivity {


    @Override
    protected View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.activity_plan,mBaseFrameLayout);
    }

    @Override
    protected void preView() {

    }

    @Override
    protected void initData() {
        setToolBarColor(getIntent().getIntExtra("Color",0));

        int color= ResourcesCompat.getColor(getResources(), R.color.yellow, null);
        LogUtil.i(TAG,"color="+color);
    }
}
