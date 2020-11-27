package com.hda.widget.TimeTable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hda.widget.DimensionConvert;
import com.hda.widget.R;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class TimeTableView extends LinearLayout {

    Context context;
    RecyclerView mRecyclerView;
    public static int width;
    private HashMap<Integer,HashMap<Integer,Integer>> table_datas;
    private int cur_index=-1;
    private RecycleAdapter mAdapter;//适配器

    WeekView week;
    public TimeTableView(Context context) {
        super(context);
        this.context=context;
    }
    public TimeTableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public void init(int width){
        this.width=width;
        DimensionConvert.setwidth(width);
        LayoutParams params = new LayoutParams(width ,WRAP_CONTENT);
        setLayoutParams(params);
        setOrientation(VERTICAL);
        addheader();
        addtips();
        addtable();
        setBackgroundColor(context.getApplicationContext().getResources().getColor(R.color.time_whilte));
    }
    private void addheader(){
        week=new WeekView(context);
        week.initWeek(7);
        addView(week);
        week.setOnWeeklistener(new WeekView.OnWeeklistener() {
            @Override
            public void OnWeekClick(int index) {
                cur_index=index;
                showcur(cur_index);
            }
        });
    }
    private void addtable(){
        mRecyclerView=new RecyclerView(context);
        LayoutParams params = new LayoutParams(width -DimensionConvert.my_demins(10), ViewGroup.LayoutParams.WRAP_CONTENT);
        this.width=width -DimensionConvert.my_demins(10);
        params.setMargins(DimensionConvert.my_demins(5),0,DimensionConvert.my_demins(5),0);
        mRecyclerView.setLayoutParams(params);
        addView(mRecyclerView);
    }

    private void addtips(){
        LayoutParams params = new LayoutParams(width ,ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout tips_area=new LinearLayout(context);
        tips_area.setLayoutParams(params);
        tips_area.setOrientation(HORIZONTAL);
        TextView tips=new TextView(context);
        tips.setText("可用时间段         ");
        tips.setTextSize(DimensionConvert.my_demins(2));
        tips.getPaint().setFakeBoldText(true);

        tips.setTextColor(context.getApplicationContext().getResources().getColor(R.color.time_item_bg_selected));
        tips_area.addView(tips);
        TextView tips2=new TextView(context);
        tips2.setText("禁用时间段");
        tips2.getPaint().setFakeBoldText(true);
        tips2.setTextColor(context.getApplicationContext().getResources().getColor(R.color.time_item_text_unselected));
        tips2.setTextSize(DimensionConvert.my_demins(2));
        tips_area.addView(tips2);
        addView(tips_area);
        params.setMargins(DimensionConvert.my_demins(5),DimensionConvert.my_demins(3),DimensionConvert.my_demins(2),DimensionConvert.my_demins(3));
    }


    int time=0;
    int pre_time=0;
    private List mList;
    private TableItemView tableItemView;
    private void setdata(final HashMap<Integer,Integer> time_map){
        mList=new ArrayList<Integer>();
        for (int i=0;i<24;i++){
            mList.add(i);
        }
        mAdapter = new RecycleAdapter(mList,time_map);
        mRecyclerView.setAdapter(mAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 6);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    time=(int) (event.getX() / 180) + (int) (event.getY() / 180) * 6;
                    if(time>24||time<0){
                        return true;
                    }
                    if(event.getAction()!=MotionEvent.ACTION_DOWN){
                        if(pre_time==time){
                            return true;
                        }
                    }
                    tableItemView= (TableItemView) mRecyclerView.getChildAt(time);
                    pre_time=time;
                    if(event.getAction()==MotionEvent.ACTION_MOVE||event.getAction()==MotionEvent.ACTION_DOWN) {
                        if (time_map.containsKey(time)) {
                            time_map.remove(time);
                            tableItemView.restored();
                        } else {
                            time_map.put(time,time);
                            tableItemView.selected();
                        }
                    }
                    if(mTimeTableViewlistener!=null){
                        mTimeTableViewlistener.TimeTableDataCallBack(cur_index,time_map);
                    }
                }catch (Exception e){
                    return true;
                }finally {
                    return true;
                }
            }
        });
    }

    public void insertData(int cur_day,HashMap<Integer,HashMap<Integer,Integer>> selected_times){
        if( this.cur_index==-1) {
            table_datas = selected_times;
            this.cur_index = cur_day;
            week.showcur_week(cur_day);
            setdata(selected_times.get(cur_day));
        }
    }

    private void showcur(int cur_day){
        if(!table_datas.containsKey(cur_day)) {
            table_datas.put(cur_day,new HashMap<Integer,Integer>());
        }
        setdata(table_datas.get(cur_day));
    }

    public void setOnTimeTableViewlistener(TimeTableViewlistener mTimeTableViewlistener){
        this.mTimeTableViewlistener=mTimeTableViewlistener;
    }

    public TimeTableViewlistener mTimeTableViewlistener;
    public interface TimeTableViewlistener{
        void TimeTableDataCallBack(int cur_day, HashMap<Integer, Integer> maps);
    }


}
