package com.carrie.lldiary.viewModel.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.carrie.lldiary.App;
import com.carrie.lldiary.R;
import com.carrie.lldiary.inter.OnMenuClickListener;
import com.carrie.lldiary.utils.DisplayUtil;
import com.carrie.lldiary.utils.LogUtil;


/**
 * Created by Carrie on 2017/7/28.
 * <p>
 * 圆心坐标（SW/2,SH/2）
 * 大圆半径 R
 * 6个小圆的坐标（最上方的点为第0点，记为A，顺时针依次为A-F）：
 * A（sw/2,sh/2 -R）
 * B（sw/2+√3/2 *R， sh/2 - R/2）
 * C(sw/2+√3/2 *R, sh/2 + R/2)
 * D(sw/2, sh/2 + R)
 * E(sw/2 -√3/2 *R, sh/2 + R/2)
 * F(sw/2 - 根号3/2 *R, sh/2 - R/2)
 */

public class CircleView extends View {
    private static final String TAG = "CircleView";
    private final int mSW = App.mScreenWidth;
    private final int mSH = App.mScreenHeight;
    /**
     * 设置小圆半径
     **/
    private static final int RADIUS_SMALL = 56;
    /**
     * 大圆半径，由 屏幕宽度 和 RADIUS_SMALL 决定
     */
    private int RADIUS_BIG;

    private Paint mPaintBigCircle;
    private Paint mPaintSmallCircle;
    private int[] colors;
    private String[] menus;
    private Paint mPaintText;
    private Context mContext;
    private float mTouchX;
    private float mTouchY;
    private OnMenuClickListener mClickListener;

    public CircleView(Context context) {
        super(context);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setPaint();

        RADIUS_BIG = mSW / 2 - DisplayUtil.dip2px(context, 16 + RADIUS_SMALL);

        colors = getResources().getIntArray(R.array.menu_colors);
        menus = new String[]{"便利贴", "记账本", "日记本", "计划表", "恋恋", "纪念日"};
    }

    public void setMenuNames(String[] menus){
        this.menus=menus;
    }

    private void setPaint() {
        mPaintBigCircle = new Paint();
        mPaintSmallCircle = new Paint();
        mPaintText = new Paint();

        mPaintBigCircle.setStyle(Paint.Style.STROKE);
        mPaintBigCircle.setAntiAlias(true);
        mPaintBigCircle.setStrokeWidth(1);
        mPaintBigCircle.setColor(getResources().getColor(R.color.dash_circle));

        mPaintSmallCircle.setStyle(Paint.Style.FILL);
        mPaintSmallCircle.setAntiAlias(true);
        mPaintSmallCircle.setColor(getResources().getColor(R.color.colorAccent));

        mPaintText.setColor(Color.WHITE);
        mPaintText.setAntiAlias(true);
        mPaintText.setTextSize(DisplayUtil.sp2px(mContext, 20));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画外圈大圆
        canvas.drawCircle(mSW / 2, mSH / 2, RADIUS_BIG, mPaintBigCircle);
        canvas.save();

        //画四周的6个小圆
        for (int i = 0; i < 24; i++) {
            //在这些点画圆
            if (i == 0 || i == 4 || i == 8 || i == 12 || i == 16 || i == 20) {
                mPaintSmallCircle.setColor(colors[i / 4]);
                canvas.drawCircle(mSW / 2, mSH / 2 - RADIUS_BIG, RADIUS_SMALL, mPaintSmallCircle);
                canvas.drawText(menus[i / 4], mSW / 2 - 40, mSH / 2 - RADIUS_BIG, mPaintText);
            }
            //画布旋转15度，简化坐标运算
            canvas.rotate(15, mSW / 2, mSH / 2);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = event.getX();
                mTouchY = event.getY();
                LogUtil.i(TAG, "onTouchEvent: ACTION_DOWN  mTouchX=" + mTouchX + ",mTouchY=" + mTouchY);

                //顺时针方向
                if (conditionCoordinate(0, -1, 0)) {//0
                    mClickListener.onClick(0);
                    Toast.makeText(mContext, "this is circle0 ", Toast.LENGTH_SHORT).show();
                } else if (conditionCoordinate(1, -1, 60)) {//1
                    mClickListener.onClick(1);
                    Toast.makeText(mContext, "this is circle1 ", Toast.LENGTH_SHORT).show();
                } else if (conditionCoordinate(1, 1, 60)) {//2
                    mClickListener.onClick(2);
                    Toast.makeText(mContext, "this is circle2 ", Toast.LENGTH_SHORT).show();
                } else if (conditionCoordinate(0, 1, 0)) {//3
                    mClickListener.onClick(3);
                } else if (conditionCoordinate(-1, 1, 60)) {//4
                    mClickListener.onClick(4);
                    Toast.makeText(mContext, "this is circle4 ", Toast.LENGTH_SHORT).show();
                } else if (conditionCoordinate(-1, -1, 60)) {//5
                    mClickListener.onClick(5);
                    Toast.makeText(mContext, "this is circle5 ", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    public void setOnMenuClickListener(OnMenuClickListener listener) {
        this.mClickListener = listener;
    }

    /**
     * @param ax        正负，x方向坐标， 1 表示 正 ；-1 表示 负
     * @param ay        正负，y方向坐标
     * @param cosDegree y方向 角度，取0或60，cos0=1,cos60=1/2
     * @return boolean
     */
    private boolean conditionCoordinate(float ax, float ay, int cosDegree) {
        float mSmallCenterX = getSmallCircleCenterCoordX(ax);
        float mSmallCenterY = getSmallCircleCenterCoordY(ay, cosDegree);
        return mTouchX >= mSmallCenterX - RADIUS_SMALL && mTouchX <= mSmallCenterX + RADIUS_SMALL
                && mTouchY >= mSmallCenterY - RADIUS_SMALL && mTouchY <= mSmallCenterY + RADIUS_SMALL;
    }

    /**
     * 获取小圆圆心的 X 坐标
     * a表示正负 ,可取 0，1，-1
     */
    private float getSmallCircleCenterCoordX(float a) {
        return (float) (mSW / 2 + a * Math.cos(Math.toRadians(30)) * RADIUS_BIG);
    }

    /**
     * 获取小圆圆心的 Y 坐标
     *
     * @param a         1,-1
     * @param cosDegree 0或60；cos0=1;cos60=1/2
     * @return float
     */
    private float getSmallCircleCenterCoordY(float a, int cosDegree) {
        return (float) (mSH / 2 + a * Math.cos(Math.toRadians(cosDegree)) * RADIUS_BIG);
    }


}
