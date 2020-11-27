package com.hda.widget.SlidePoint;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;

public class SlidePointWidget extends LinearLayout {
    private Context context;
    private SlidePoint cur_slide_point;

    public SlidePointWidget(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public SlidePointWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    private void init(){
        this.setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
    }
    public void fillSlidePoints(int size){
        for (int i=0;i<size;i++){
            SlidePoint point=new SlidePoint(context);
            point.setId(i);
            addView(point);
            if(i==0){
                cur_slide_point=point;
                cur_slide_point.selected();
            }
        }
    }

    public void setIndex(int index){
        if(getChildCount()>index){
            cur_slide_point.unselected();
            cur_slide_point= (SlidePoint) getChildAt(index);
            cur_slide_point.selected();
        }else{
            Log.e("SlidepointWidget","index > SlidepointWidget.size()-1");
        }
    }
}
