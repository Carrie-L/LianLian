package com.carrie.lianlian.utils;

import android.graphics.Color;

/**
 * Created by Carrie on 2017/7/31.
 */

public class ColorDeeperUtil {

    /**
     * 颜色加深处理
     * from: http://blog.csdn.net/jdsjlzx/article/details/41441083
     *
     * @param color resColor
     * RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *                  Android中我们一般使用它的16进制，
     *                  例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *                  red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *                  所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    public static int colorBurn(int color) {
        int rgbValues=Color.rgb(Color.red(color),Color.green(color),Color.blue(color));

        int alpha = rgbValues >> 24;
        int red = rgbValues >> 16 & 0xFF; //右移16位，并和0xFF作与运算
        int green = rgbValues >> 8 & 0xFF;
        int blue = rgbValues & 0xFF;

        LogUtil.i("colorBurn","red="+red);
        LogUtil.i("colorBurn","green="+green);
        LogUtil.i("colorBurn","blue="+blue);

        red = (int) Math.floor(red * (1 - 0.24));
        green = (int) Math.floor(green * (1 - 0.23));
        blue = (int) Math.floor(blue * (1 - 0.12));

        LogUtil.i("-----colorBurn-----","red="+red);
        LogUtil.i("colorBurn","green="+green);
        LogUtil.i("colorBurn","blue="+blue);

        return Color.rgb(red, green, blue);
    }

}
