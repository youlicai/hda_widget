package com.hda.widget;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by shichaohui on 2015/7/10 0010.
 */
public class DimensionConvert {


    public static int width=1080;
//    public static int width=720;
    //pxValue  手机宽度
    public static void setwidth(int pxValue){
        width=pxValue;
    }

    public static int my_demins(int demins){
        return width*demins/100;
    }
    public static int my_demins2(int demins){
        return width*demins/1000;
    }

}