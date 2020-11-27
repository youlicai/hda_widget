package com.hda.widget.TimeTable;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;

import com.hda.widget.DimensionConvert;

import com.hda.widget.R;
public class TableItemView  extends LinearLayout {
    Context context;
    AppCompatTextView text;

    public TableItemView(Context context) {
        super(context);
        this.context=context;
        init();
    }
    private void init(){
        setGravity(Gravity.CENTER);
        LayoutParams params = new LayoutParams(TimeTableView.width/6 ,TimeTableView.width/6 );
        LayoutParams params1 = new LayoutParams(TimeTableView.width/6-2 ,TimeTableView.width/6-2);
        text=new AppCompatTextView(context);
        text.setLayoutParams(params1);
        text.setGravity(Gravity.CENTER);
        text.setTextSize(DimensionConvert.my_demins(3));
        setBackgroundColor(context.getApplicationContext().getResources().getColor(R.color.time_grey));
        addView(text);
        restored();
        setLayoutParams(params);
    }

    public void setText(String textString){
        text.setText(textString);
    }
    public TextView getTextView() {
        return text;
    }

    public void selected(){
        LayoutParams params1 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,ViewGroup.LayoutParams.MATCH_PARENT );
        text.setLayoutParams(params1);
        text.setTextColor(context.getApplicationContext().getResources().getColor(R.color.time_whilte));
        text.setBackgroundColor(context.getApplicationContext().getResources().getColor(R.color.time_item_bg_selected));
    }

    public  void restored(){
        LayoutParams params1 = new LayoutParams(TimeTableView.width/6-2 ,TimeTableView.width/6-2 );
        text.setLayoutParams(params1);
        text.setBackgroundColor(context.getApplicationContext().getResources().getColor(R.color.time_item_bg_unselected));
        text.setTextColor(context.getApplicationContext().getResources().getColor(R.color.time_item_text_unselected));

    }
}
