package com.carrie.lianlian.activity;

import android.databinding.DataBindingUtil;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;

import com.carrie.lianlian.App;
import com.carrie.lianlian.fragment.MainFragment;
import com.carrie.lianlian.R;
import com.carrie.lianlian.databinding.ActivityMainBinding;
import com.carrie.lianlian.utils.LogUtil;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        // Set up the navigation drawer.
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
//            setupDrawerContent(navigationView);
        }

        mDrawerLayout = binding.drawerLayout;
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);

        MainFragment mainFragment = MainFragment.newInstance();
        getFragmentManager().beginTransaction().add(R.id.contentFrame, mainFragment).commit();

        getScreenSize();
    }

    private void getScreenSize(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        App.mScreenWidth= metrics.widthPixels;
        App.mScreenHeight=metrics.heightPixels;
        LogUtil.i(TAG,"设备的屏幕尺寸为，宽："+App.mScreenWidth+"，高："+App.mScreenHeight);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            Log.i(TAG, "onOptionsItemSelected");
        }
        return super.onOptionsItemSelected(item);
    }
}
