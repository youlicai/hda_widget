package com.hda.widget.TimeTable;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.hda.widget.R;

public class WeekView extends LinearLayout {
    Context context;
    String[] week_name=new String[]{"周一","周二","周三","周四","周五","周六","周日"};
    public WeekView(Context context) {
        super(context);
        this.context=context;
    }

    private WeekItemView pre_WeekItem;
    public void initWeek(int days){
        clear();
        setBackgroundColor(context.getApplicationContext().getResources().getColor(R.color.header_bg));

        LayoutParams params1 = new LayoutParams(TimeTableView.width ,(int)(TimeTableView.width/8.3));
        setLayoutParams(params1);
        for (int day=1;day<=days;day++){
            final WeekItemView item=new WeekItemView(context);
            item.setText(week_name[day-1]);
            addView(item);
            final int finalDay = day;
            item.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(pre_WeekItem!=null&&pre_WeekItem==item)
                        return;
                    if(pre_WeekItem!=null&&pre_WeekItem!=item)
                        pre_WeekItem.restored();

                    if(mOnWeeklistener!=null){
                        mOnWeeklistener.OnWeekClick(finalDay);
                        item.selected();
                        pre_WeekItem=item;
                    }
                }
            });
        }
    }

    public void showcur_week(int cur_week){
        if(cur_week<7) {
            WeekItemView item= (WeekItemView) getChildAt(cur_week - 1);
            pre_WeekItem=item;
            item.selected();
        }
    }

    private void clear(){
        for (int i=0;i<getChildCount();i++){
            WeekItemView item= (WeekItemView) getChildAt(i);
            item.restored();
        }
    }
    OnWeeklistener mOnWeeklistener;
    public void setOnWeeklistener(OnWeeklistener mOnWeeklistener){
        this.mOnWeeklistener=mOnWeeklistener;
    }
    interface OnWeeklistener{
        void OnWeekClick(int index);
    }
}
