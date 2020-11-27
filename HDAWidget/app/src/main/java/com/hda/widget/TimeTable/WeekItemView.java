package com.hda.widget.TimeTable;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hda.widget.DimensionConvert;
import com.hda.widget.R;


public class WeekItemView extends LinearLayout {

    private TextView mTextView;
    private View line;
    Context context;
    public WeekItemView(Context context) {
        super(context);
        this.context=context;
        init();
        LayoutParams params = new LayoutParams(TimeTableView.width/7 ,(int)(TimeTableView.width/8.3));
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        setLayoutParams(params);
        restored();
    }

    private void init(){
        mTextView=new TextView(context);
        line=new View(context);
        LayoutParams params = new LayoutParams(TimeTableView.width/7 ,(int)(TimeTableView.width/10.3));
        LayoutParams params_line = new LayoutParams(DimensionConvert.my_demins(13) ,DimensionConvert.my_demins(1));
        line.setLayoutParams(params_line);
        mTextView.setLayoutParams(params);
        addView(mTextView);
        addView(line);
        mTextView.setGravity(Gravity.CENTER);
    }
    public void selected(){
        mTextView.setTextColor(context.getApplicationContext().getResources().getColor(R.color.time_item_bg_selected));
        line.setBackgroundColor(context.getApplicationContext().getResources().getColor(R.color.time_item_bg_selected));
    }

    public void setText(String str){
        mTextView.setText(str);
    }
    public void restored(){
        mTextView.setTextColor(context.getApplicationContext().getResources().getColor(R.color.time_item_text_unselected));
        line.setBackgroundColor(context.getApplicationContext().getResources().getColor(R.color.touming));
    }
}