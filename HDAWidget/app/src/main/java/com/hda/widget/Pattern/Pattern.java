package com.hda.widget.Pattern;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;

import com.hda.widget.AppIcon.AppIconBean;
import com.hda.widget.HRecyclerView;
import com.hda.widget.R;
import com.hda.widget.ScrollBottomScrollView;

import java.util.List;
import static com.hda.widget.DimensionConvert.my_demins;
import static com.hda.widget.DimensionConvert.my_demins2;

public class Pattern extends RelativeLayout {

    private Context context;
    private RelativeLayout title_area;
    private TextView title_tag;
    private TextView title_tips;
    private ImageView title_clock;
    private TextView title_remain_time;
    private TextView using;
    private ImageView image_patter;

    private ImageView stop;
    private TextView text_enable_apps;
    private HRecyclerView apps_list;
    private ImageView no_apps;

    private RelativeLayout main;
    private ScrollBottomScrollView scrollView;

    //title_area  id
    int title_area_id=1900;
    int title_tag_id=13413453;
    int title_tips_id=734634;
    int title_clock_id=73567537;
    int remain_time_id=75623524;

    int image_tips_id=4904;
    int text_enable_apps_id=5035;
    int apps_list_id=5225;

    private int status;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Pattern(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Pattern(Context context) {
        super(context);
        this.context=context;
        init();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init(){
        title_area=new RelativeLayout(context);
        title_tag=new TextView(context);
        title_tips=new TextView(context);
        title_remain_time=new TextView(context);
        title_clock=new ImageView(context);
        using=new TextView(context);

        title_area.setId(title_area_id);
        image_patter=new ImageView(context);
        stop=new ImageView(context);
        image_patter.setId(image_tips_id);
        text_enable_apps=new TextView(context);
        text_enable_apps.setId(text_enable_apps_id);
        apps_list=new HRecyclerView(context);
        apps_list.setId(apps_list_id);
        no_apps=new ImageView(context);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void layout(){


        //title_area
        RelativeLayout.LayoutParams title_area_params = new RelativeLayout.LayoutParams( my_demins(90), my_demins(15));
        title_area.setLayoutParams(title_area_params);
        title_area.setPadding(my_demins(4),my_demins(2),my_demins(1),0);


        RelativeLayout.LayoutParams title_tag_params = new RelativeLayout.LayoutParams( my_demins(25), my_demins(7));
        title_tag.setLayoutParams(title_tag_params);
        title_tag.setId(title_tag_id);
        title_tag.setTextSize(my_demins2(18));

        title_tag.setTextColor(Color.parseColor("#333333"));
        title_tag.setGravity(Gravity.CENTER_VERTICAL);

        RelativeLayout.LayoutParams title_tips_params = new RelativeLayout.LayoutParams( my_demins(55), my_demins(6));
        title_tips_params.addRule(RelativeLayout.BELOW,title_tag_id);
        title_tips.setId(title_tips_id);
        title_tips.setTextSize(my_demins2(12));
        title_tips.setLayoutParams(title_tips_params);
        title_tips.setGravity(Gravity.CENTER_VERTICAL);

        RelativeLayout.LayoutParams title_clock_params = new RelativeLayout.LayoutParams( my_demins2(40), my_demins2(40));
        title_clock_params.addRule(RelativeLayout.RIGHT_OF,title_tag_id);
        title_clock_params.setMargins(my_demins(30),my_demins2(15),0,0);
        title_clock.setId(title_clock_id);
        title_clock.setLayoutParams(title_clock_params);
        title_clock.setImageResource(R.mipmap.clock);

        RelativeLayout.LayoutParams title_remain_time_params = new RelativeLayout.LayoutParams( my_demins(25), my_demins(7));
        title_remain_time_params.addRule(RelativeLayout.RIGHT_OF,title_clock_id);
        title_remain_time_params.addRule(RelativeLayout.ALIGN_TOP,title_tag_id);
        title_remain_time_params.setMargins(my_demins(1),0,0,0);
        title_remain_time.setGravity(Gravity.CENTER_VERTICAL);
        title_remain_time.setTextSize(my_demins2(12));
        title_remain_time.setTextColor(Color.parseColor("#bbbbbb"));
        title_remain_time.setId(remain_time_id);
        title_remain_time.setLayoutParams(title_remain_time_params);

        RelativeLayout.LayoutParams using_params = new RelativeLayout.LayoutParams( my_demins(25), my_demins(7));
        using_params.addRule(RelativeLayout.RIGHT_OF,title_tag_id);
        using_params.addRule(RelativeLayout.ALIGN_TOP,title_tag_id);
        using_params.setMargins(my_demins(38),0,0,0);

        using.setGravity(Gravity.CENTER_VERTICAL);
        using.setTextSize(my_demins2(15));
        using.setTextColor(Color.parseColor("#00AB8D"));
        using.setText("使用中");
        using.setLayoutParams(using_params);


        title_area.addView(title_tag);
        title_area.addView(title_tips);
        title_area.addView(title_clock);
        title_area.addView(title_remain_time);
        title_area.addView(using);
        addView(title_area);

        //main
        main=new RelativeLayout(context);
        main.setBackgroundColor(Color.WHITE);
        RelativeLayout.LayoutParams image_tips_params = new RelativeLayout.LayoutParams(my_demins(90), my_demins(45));
        image_patter.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image_patter.setLayoutParams(image_tips_params);
        stop.setScaleType(ImageView.ScaleType.CENTER_CROP);
        stop.setLayoutParams(image_tips_params);


        RelativeLayout.LayoutParams text_enable_apps_params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , my_demins(6));
        text_enable_apps_params.addRule(RelativeLayout.BELOW,image_tips_id);
        text_enable_apps_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        text_enable_apps_params.setMargins(my_demins(5),my_demins(2),0,0);
        text_enable_apps.setTextSize(my_demins2(11));
        text_enable_apps.setTextColor(Color.parseColor("#bbbbbb"));
        text_enable_apps.setLayoutParams(text_enable_apps_params);

        RelativeLayout.LayoutParams apps_list_params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT);
        apps_list_params.addRule(RelativeLayout.BELOW,text_enable_apps_id);
        apps_list_params.setMargins(my_demins(5),0,my_demins(5),0);
        apps_list.setLayoutParams(apps_list_params);


        RelativeLayout.LayoutParams no_apps_params = new RelativeLayout.LayoutParams(my_demins(30) , my_demins(20));
        no_apps_params.setMargins(0,my_demins(5),0,0);
//        no_apps.setBackgroundColor(Color.GREEN);
        no_apps_params.addRule(RelativeLayout.BELOW,text_enable_apps_id);
        no_apps_params.addRule(RelativeLayout.CENTER_IN_PARENT);
        no_apps.setLayoutParams(no_apps_params);

        main.addView(image_patter);
        main.addView(stop);
        main.addView(text_enable_apps);
        main.addView(apps_list);
        main.addView(no_apps);
        int height=apps_list.getHHeight();
        if(apps_list.getHHeight()<10){
            height=my_demins(80);
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(my_demins(90), height+ my_demins(45)+my_demins(10));
        main.setLayoutParams(params);
        scrollView=new ScrollBottomScrollView(context);
        scrollView.setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        RelativeLayout.LayoutParams scrollView_params = new RelativeLayout.LayoutParams(my_demins(90), my_demins(100));
        scrollView_params.addRule(RelativeLayout.BELOW,title_area_id);
        scrollView.setLayoutParams(scrollView_params);

        scrollView.addView(main);
        addView(scrollView);
        setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 50);
            }
        });
        setClipToOutline(true);

        setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void fill_data(int pattern_type, int status, String remain_time, final List<AppIconBean> icon_list){
        List<AppIconBean> icon_list_temp=icon_list;
        if(pattern_type==0){
            title_tag.setText("锁屏模式");
            title_tips.setText("适用于上课、睡觉、休息时");
            title_area.setBackgroundColor(Color.parseColor("#E8F5E1"));
            image_patter.setImageResource(R.mipmap.study);//screen_off
        }else if(pattern_type==1){
            title_tag.setText("学习模式");
            title_tips.setText("适用于上课、写作业时");
            title_area.setBackgroundColor(Color.parseColor("#FFE8CC"));
            image_patter.setImageResource(R.mipmap.study);
        }else if(pattern_type==2){
            title_tag.setText("娱乐模式");
            title_tips.setText("适用于周末、节假日、寒暑假娱乐时");
            title_area.setBackgroundColor(Color.parseColor("#FFE8E8"));
            image_patter.setImageResource(R.mipmap.study);//entertainment
        }
        this.status=status;
        statusChange(status);

        title_remain_time.setText(remain_time);
        apps_list.setRecyclerViewWidth(my_demins(90));
        apps_list.set_span_count(4);


        if(icon_list!=null&&icon_list.size()>0) {
            text_enable_apps.setText("可以使用的APP");
            apps_list.fill_data(icon_list_temp.subList(0,10));
            no_apps.setVisibility(INVISIBLE);
            apps_list.setVisibility(VISIBLE);
        }else{
            text_enable_apps.setText("没有可以使用的APP哦");
            no_apps.setImageResource(R.mipmap.no_apps);
            no_apps.setVisibility(VISIBLE);
            apps_list.setVisibility(INVISIBLE);
        }
        layout();
        scrollView.onScrollViewScrollToBottom(new ScrollBottomScrollView.OnScrollBottomListener() {
            @Override
            public void scrollToBottom() {
                List<AppIconBean> icon_list_temp1=icon_list;
                scroll_index++;
                if(10*scroll_index>icon_list.size()){
                    return;
                }
                if(10*scroll_index+10<icon_list.size()) {
                    apps_list.addList(icon_list_temp1.subList(10 * scroll_index, 10 * scroll_index + 10));
                } else{
                    apps_list.addList(icon_list_temp1.subList(10*scroll_index,icon_list.size()));
                }

            }
        });
    }
    int scroll_index=0;

    public void setStop(boolean status1){

        if(status1){
            stop.setVisibility(VISIBLE);
            stop.setImageResource(R.mipmap.stop);
            title_clock.setVisibility(INVISIBLE);
            title_remain_time.setVisibility(INVISIBLE);
            using.setVisibility(INVISIBLE);
        }else{
            status=2;
            statusChange(status);
        }
    }


    private void statusChange(int status){
        if(status==0){//常规状态
            title_clock.setVisibility(VISIBLE);
            title_remain_time.setVisibility(VISIBLE);
            using.setVisibility(INVISIBLE);
        }else if(status==1){//使用中
            title_clock.setVisibility(INVISIBLE);
            title_remain_time.setVisibility(INVISIBLE);
            using.setVisibility(VISIBLE);
        }else if(status==2){//停用
            stop.setImageResource(R.mipmap.stop);
            stop.setVisibility(VISIBLE);
            title_clock.setVisibility(INVISIBLE);
            title_remain_time.setVisibility(INVISIBLE);
            using.setVisibility(INVISIBLE);
        }
    }



}
