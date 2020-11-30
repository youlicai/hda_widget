package com.hda.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class DanceVIew extends View {
    private Path path;
    private Paint paint;//画笔

    int dx=0;
    int dx2=0;
    int dx3=0;
    int temp_dx=0;
    int temp_dx2=0;
    int temp_dx3=0;
    int step=10;
    int delay=200;
    boolean stop=false;

    public DanceVIew(Context context) {
        super(context);
        init();
    }
    public DanceVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.parseColor("#009FCC"));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        path = new Path();

    }

    public void start(){
        stop=false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag3=false;
                dx3=getRandomNumber2();
                while (true&&!stop) {
                    Log.e("++++'",stop+"");
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(!flag3){
                        temp_dx3+=step;
                        if(temp_dx3>dx3){
                            flag3=true;
                        }
                    }

                    if(flag3){
                        temp_dx3-=step;
                        if(temp_dx3<getHeight()/3){
                            flag3=false;
                            dx3=getRandomNumber2();
                        }
                    }
                    postInvalidate();
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag3=false;
                dx=getRandomNumber2();
                while (true&&!stop) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(!flag3){
                        temp_dx+=step;
                        if(temp_dx>dx){
                            flag3=true;
                        }
                    }

                    if(flag3){
                        temp_dx-=step;
                        if(temp_dx<getHeight()/3){
                            flag3=false;
                            dx=getRandomNumber2();
                        }
                    }
                    postInvalidate();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag3=false;
                dx2=getRandomNumber2();
                while (true&&!stop) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(!flag3){
                        temp_dx2+=step;
                        if(temp_dx2>dx2){
                            flag3=true;
                        }
                    }

                    if(flag3){
                        temp_dx2-=step;
                        if(temp_dx2<getHeight()/3){
                            flag3=false;
                            dx2=getRandomNumber2();
                        }
                    }
                    postInvalidate();
                }
            }
        }).start();
    }


    public void stop(){
        stop=true;
    }
    public Integer getRandomNumber2() {
        Integer min = 0;
        Integer max = getHeight();
        Random random = new  Random();
        int result = 0;
        try {
            result= random.nextInt(max) % (max-min+1) + min;
        }catch (Exception e){
            
        }
        return result;
    }
    int i;
    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        Log.e("===","onDraw"+temp_dx);
        step=getHeight()*1/8;
        RectF r1 = new RectF();
        r1.left = 0;
        r1.right = getWidth()/7;
        r1.top = getHeight()-temp_dx;
        r1.bottom = getHeight();
        canvas.drawRoundRect(r1, 0, 0, paint);
                RectF r2 = new RectF();
                r2.left = getWidth()/7*3;
                r2.right = getWidth()/7*4;
                r2.top = getHeight()-temp_dx2;
                r2.bottom = getHeight();
                canvas.drawRoundRect(r2, 0, 0, paint);
                RectF r3 = new RectF();
                r3.left = getWidth()/7*6;
                r3.right = getWidth()/7*7;
                r3.top = getHeight()-temp_dx3 ;
                r3.bottom = getHeight();
                canvas.drawRoundRect(r3, 0, 0, paint);
    }



}
