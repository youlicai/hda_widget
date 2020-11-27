package com.hda.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Test extends View {
    public Test(Context context) {
        super(context);
    }

    public Test(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int verticalCenter    =  getHeight() / 2;

        int horizontalCenter  =  getWidth() / 2;

        int circleRadius      = 280;

        Paint paint = new Paint();

        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.STROKE);

        paint.setStrokeWidth(20);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawCircle( horizontalCenter, verticalCenter, circleRadius, paint);
    }
}
