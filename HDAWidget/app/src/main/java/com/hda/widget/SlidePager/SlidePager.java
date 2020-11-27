package com.hda.widget.SlidePager;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.hda.widget.Pattern.Pattern;
import com.hda.widget.Pattern.PatternBean;
import com.hda.widget.SlidePoint.SlidePointWidget;
import java.util.ArrayList;
import java.util.List;
import static com.hda.widget.DimensionConvert.my_demins;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SlidePager extends LinearLayout {
    Context context;
    private ViewPager vp;
    List<View> views;
    private SlidePointWidget slide_point_widget;

    public SlidePager(Context context) {
        super(context);
        this.context=context;
        init();
    }
    public SlidePager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }
    private void init(){
        RelativeLayout.LayoutParams selected_params = new RelativeLayout.LayoutParams(my_demins(100), my_demins(100));
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        vp=new ViewPager(context);
        vp.setLayoutParams(selected_params);
        slide_point_widget=new SlidePointWidget(context);
        addView(vp);
        addView(slide_point_widget);
    }


    public void setStop(int index,boolean stop){
        LinearLayout p= (LinearLayout) views.get(index);
        Pattern pp= (Pattern) p.getChildAt(0);
        pp.setStop(stop);
    }

    public  void fillData(List<PatternBean> pattern_list){
        views=new ArrayList<>();
        for (int i=0;i<pattern_list.size();i++){
            LinearLayout layout=new LinearLayout(context);
            layout.setGravity(Gravity.CENTER);
            Pattern pattern=new Pattern(context);
            pattern.fill_data(pattern_list.get(i).pattern_type,pattern_list.get(i).status,pattern_list.get(i).pattern_remain_time,pattern_list.get(i).app_icon_info);
            layout.addView(pattern);
            views.add(layout);
        }

        slide_point_widget.fillSlidePoints(views.size());
        vp.setAdapter(new ViewPagerAdapter(views));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                slide_point_widget.setIndex(position);
                if(mOnSlidePager!=null)
                    mOnSlidePager.selected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    public void seekIndex(int index){
        vp.setCurrentItem(index);
    }

    private class ViewPagerAdapter extends PagerAdapter{

        private  List<View> views;
        public ViewPagerAdapter(List<View> views) {
            this.views=views;
        }

        @Override
        public int getCount() {
            return views==null?0:views.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView(views.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if(container.getChildAt(position)!=views.get(position)) {
                container.removeView(views.get(position));
                container.addView(views.get(position));
            }
            return views.get(position);
        }
    }


    private OnSlidePager mOnSlidePager;
    public void setOnSlidePager(OnSlidePager mOnSlidePager){
        this.mOnSlidePager=mOnSlidePager;
    }
    public interface  OnSlidePager{
        void selected(int index);
    }
}
