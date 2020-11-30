package com.hda.widget;

import android.content.Context;
import android.util.Log;

public class ILog {
    static boolean SHOWLOG=true;
    public static void info(Class c,String info){
        if(SHOWLOG)
            Log.i(c.getName(),info);
    }
    public static void error(Class c,String error_info){
        if(SHOWLOG)
            Log.e(c.getName(),error_info);
    }
}
