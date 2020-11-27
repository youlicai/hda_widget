package com.hda.widget;
import java.util.regex.Pattern;
import android.graphics.Color;

public class ColorUtil {

    /**
     * 将十六进制 颜色代码 转换为 int
     *
     * @return
     */
    public static int HextoColor(String color) {

        // #ff00CCFF
        String reg = "#[a-f0-9A-F]{8}";
        if (!Pattern.matches(reg, color)) {
            color = "#00ffffff";
        }

        return Color.parseColor(color);
    }

    /**
     * 修改颜色透明度
     * @param color
     * @param alpha
     * @return
     */
    public static int changeAlpha(int color, int alpha) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        return Color.argb(alpha, red, green, blue);
    }

}