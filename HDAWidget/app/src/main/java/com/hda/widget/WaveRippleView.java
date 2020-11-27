package com.hda.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import static android.media.MediaCodec.MetricsConstants.MODE;
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class WaveRippleView extends View {
    /**
     * 波纹的长度
     */
    private  static int WAVE_LENGTH = 1080;
    private final static int WAVE_LENGTH_1 = 1080;

    /**
     * 波纹的高度
     */
    private final static int WAVE_HEGHT = 100;

    /**
     *  起始点y坐标
     */
    private static int wavestartY = 100;


    int dx;
    private Path path;
    private Path path1;//画的路径
    private Paint paint;//画笔
    Context context;
    public WaveRippleView(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public WaveRippleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void init() {
        paint = new Paint();
        paint.setColor(Color.parseColor("#009FCC"));
        paint.setStyle(Paint.Style.FILL);
        path = new Path();
        path1 = new Path();


        startAnim();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.reset();
        path1.reset();
        path.moveTo(-WAVE_LENGTH+dx, wavestartY);
        for (int i = -WAVE_LENGTH; i < getWidth() + WAVE_LENGTH; i = i + WAVE_LENGTH) {
            path.rQuadTo(WAVE_LENGTH / 4, -WAVE_HEGHT, WAVE_LENGTH / 2, 0);
            path.rQuadTo(WAVE_LENGTH / 4, WAVE_HEGHT, WAVE_LENGTH / 2, 0);
        }
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0,getHeight());
        path.close();
        path1.addCircle(
                300,
                300,
                300,
                Path.Direction.CCW
        );
        path1.op(path, Path.Op.INTERSECT);
        canvas.drawPath(path1, paint);

        int verticalCenter    =  getHeight() / 2;
        int horizontalCenter  =  getWidth() / 2;
        int circleRadius      = 290;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawCircle( horizontalCenter, verticalCenter, circleRadius, paint);
    }
    /**
     * 水波纹属性动画
     */
    public void startAnim(){
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(0,WAVE_LENGTH);
        valueAnimator.setDuration(2500);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.e("++++",dx+"");
                dx = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }
}
