package com.carrie.lldiary.base;

import android.os.Bundle;
import android.app.Activity;

import com.carrie.lldiary.R;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

}
