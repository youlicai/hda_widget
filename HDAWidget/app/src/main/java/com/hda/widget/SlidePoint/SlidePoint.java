package com.hda.widget.SlidePoint;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.LinearLayout;
import static com.hda.widget.DimensionConvert.my_demins;

public class SlidePoint extends View {
    private Context context;
    private int selected_bg= Color.parseColor("#00ac8e");
    private int unselected_bg=Color.LTGRAY;

    public SlidePoint(Context context) {
        super(context);
        this.context=context;
        init();
    }
    public void init(){
        unselected();
    }

    public void selected(){
        LinearLayout.LayoutParams selected_params = new LinearLayout.LayoutParams(my_demins(11), my_demins(2));
        selected_params.setMargins(my_demins(2),my_demins(2),my_demins(2),my_demins(2));
        this.setLayoutParams(selected_params);
        setRoundRadius(selected_bg);
    }
    public void unselected(){
        LinearLayout.LayoutParams selected_params = new LinearLayout.LayoutParams(my_demins(2), my_demins(2));
        selected_params.setMargins(my_demins(2),my_demins(2),my_demins(2),my_demins(2));
        this.setLayoutParams(selected_params);
        setRoundRadius(unselected_bg);
    }

    private void setRoundRadius(int bg){
        GradientDrawable backGround_dot = new GradientDrawable();//创建drawable
        backGround_dot.setCornerRadius(my_demins(2));
        backGround_dot.setColor(bg);
        this.setBackground(backGround_dot);
    }
}
