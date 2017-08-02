package com.carrie.lianlian.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.carrie.lianlian.App;
import com.carrie.lianlian.R;
import com.carrie.lianlian.dao.DBHelper;
import com.carrie.lianlian.utils.ColorDeeperUtil;
import com.carrie.lianlian.utils.LogUtil;

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = "BaseActivity";
    protected FrameLayout mBaseFrameLayout;
    protected Toolbar toolbar;
    /**
     * 是否加深状态栏的颜色。true,加深；false，则与ToolBar的颜色相同
     */
    protected boolean isDeeperStatusBar = false;

    protected SearchView searchView;
    protected int mMenuLayout = R.menu.menu_toolbar;
    protected DBHelper mDBHelper;
    private FloatingActionButton mFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mBaseFrameLayout = (FrameLayout) findViewById(R.id.frame_base_content);
        mFAB = (FloatingActionButton) findViewById(R.id.fab);
        initToolBar();
        TAG = getClass().getSimpleName();
        LogUtil.i(TAG, "TAG=" + TAG);

        mDBHelper = App.mDBHelper;
        mFAB.setOnClickListener(mListener);

        preView();
        initView(getLayoutInflater());
        initData();
    }

   View.OnClickListener mListener = new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           setOnFABClick();
       }
   };

   protected void setOnFABClick(){

   }

    protected abstract void preView();

    protected abstract View initView(LayoutInflater inflater);

    protected abstract void initData();

    protected void setToolBarColor(int color) {
        toolbar.setBackgroundColor(color);
        setStatusColor(isDeeperStatusBar ? ColorDeeperUtil.colorBurn(color) : color);
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle("日记本");
        }

    }

    private void setStatusColor(int color) {
        //状态栏透明，但ToolBar会延伸到状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            getWindow().setStatusBarColor(color);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0. ！！未测试
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            // 生成一个状态栏大小的矩形
            View statusView = createStatusView(this, color);
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) this.getWindow().getDecorView();
            decorView.addView(statusView);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @return 状态栏矩形条
     */
    private static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(mMenuLayout, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_search);
        if (menuItem != null) {
            searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (!searchView.isIconified()) {
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
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_label:
                Toast.makeText(getApplicationContext(), "label", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
