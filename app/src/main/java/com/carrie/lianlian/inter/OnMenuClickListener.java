package com.carrie.lianlian.inter;

/**
 * 主界面点击监听接口,传递位置和按钮颜色，位置用于确定是哪个功能按钮，颜色用于确定该功能的主题颜色
 * Created by new on 2017/7/28.
 */

public interface OnMenuClickListener {
    void onClick(int position,int resColor);
}
