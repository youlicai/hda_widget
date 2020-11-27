package com.hda.widget;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hda.widget.AppIcon.AppIconBean;
import com.hda.widget.Pattern.PatternBean;
import com.hda.widget.SlidePager.SlidePager;

import java.util.ArrayList;
import java.util.List;

import static com.hda.widget.DimensionConvert.my_demins;


public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"ResourceAsColor", "WrongConstant"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DimensionConvert.setwidth(720);
        setContentView(R.layout.activity_main);


        //1.
        SlidePager slide_pager=findViewById(R.id.pager);
        List<PatternBean> patternList=new ArrayList<PatternBean>();
        String title="网易";
        for (int i=0;i<3;i++){
            PatternBean pattern=new PatternBean();
            pattern.pattern_type=i;
            pattern.status=i;
            pattern.pattern_remain_time="5小时后切换";
            pattern.pattern_image="";
            if(i!=1) {
                for (int y = 0; y < 102; y++) {
                    if (y == 1) {
                        title = "支付宝";
                    }
                    if (y == 2) {
                        title = "微信";
                    }
                    if (y == 3) {
                        title = "王者荣耀耀耀";
                    }
                    if (y == 4) {
                        title = "关爱孩子APP";
                    }
                    if (y == 6) {
                        title = "查快递";
                    }
                    AppIconBean app_icon_bean = new AppIconBean(y + 1 + "", "https://avatar.csdnimg.cn/F/B/3/3_a2241076850.jpg", title+y);
                    pattern.app_icon_info.add(app_icon_bean);
                }
            }
            patternList.add(pattern);
        }
        slide_pager.fillData(patternList);
        slide_pager.setOnSlidePager(new SlidePager.OnSlidePager() {
            @Override
            public void selected(int index) {
                Log.e("index",index+"");
            }
        });
//        slide_pager.seekIndex(0);//跳转哪一页
//        slide_pager.setStop(1,false);//停用哪个模式
//        slide_pager.fillData(patternList);


        //====================================

        //2.
        List<AppIconBean> brean1=new ArrayList<AppIconBean>();
        for(int y=0;y<10;y++){
            AppIconBean app_icon_bean=new AppIconBean(y+1+"","https://avatar.csdnimg.cn/F/B/3/3_a2241076850.jpg","中国工商银行");
            brean1.add(app_icon_bean);
        }

        List<AppIconBean> brean2=new ArrayList<AppIconBean>();
        for(int y=0;y<7;y++){
            AppIconBean app_icon_bean=new AppIconBean(y+1+"","https://avatar.csdnimg.cn/F/B/3/3_a2241076850.jpg","微信");
            brean2.add(app_icon_bean);
        }

        final HRecyclerView un_enable=findViewById(R.id.un_enable);
        final HRecyclerView enable=findViewById(R.id.enable);
        enable.setOnHRecyclerViewListener(new HRecyclerView.OnHRecyclerViewListener() {
            @Override
            public void OnDeleteOneItem(AppIconBean bean) {
                un_enable.addData(bean);
                Log.e("+++enable++",bean.title);
                //调用api接口
            }
            @Override
            public void OnLongClickShake() {
                un_enable.shake_shake();
            }
        });
        un_enable.setOnHRecyclerViewListener(new HRecyclerView.OnHRecyclerViewListener() {
            @Override
            public void OnDeleteOneItem(AppIconBean bean) {
                enable.addData(bean);
                Log.e("++un_enable+++",bean.title);
                //调用api接口
            }

            @Override
            public void OnLongClickShake() {
                enable.shake_shake();
            }
        });
        enable.Tag="enable";
        enable.setRecyclerViewSize(my_demins(100),my_demins(70));
        enable.set_span_count(5);
        enable.setDeleteImage(R.mipmap.x);
        enable.fill_data(brean1);
        enable.shakeLongClickEnable(true);

        un_enable.setRecyclerViewSize(my_demins(90),my_demins(70));
        un_enable.Tag="un_enable";
        un_enable.set_span_count(5);
        un_enable.setDeleteImage(R.mipmap.x);
        un_enable.fill_data(brean2);
        un_enable.shakeLongClickEnable(true);

        TextView click=findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enable.shake_shake();
                un_enable.shake_shake();//
            }
        });

        TextView click2=findViewById(R.id.click2);
        click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enable.setOver();
                un_enable.setOver();//
            }
        });
    }
}