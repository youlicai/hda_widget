package com.hda.widget.AppIcon;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import static com.hda.widget.DimensionConvert.my_demins;
import static com.hda.widget.DimensionConvert.my_demins2;

public class AppIcon extends RelativeLayout {
    private Context context;
    private ImageView app_image;
    private TextView app_title;
    private ImageView app_delete_image;

    String packet_id;
    int app_image_id = 1000;
    int app_title_id = 4004;
    int app_delete_image_id = 5005;

    private RelativeLayout  main;
    public AppIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public AppIcon(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public void setPadding1(int left, int top, int right, int bottom) {
        setPadding(left, top, right, bottom);

    }

    private void init() {
        main=new RelativeLayout(context);
        app_title = new TextView(context);
        app_delete_image = new ImageView(context);
        app_image = new ImageView(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            app_image.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 15);
                }
            });
            app_image.setClipToOutline(true);
        }

        LayoutParams main_params = new LayoutParams(my_demins(17), my_demins(18));
        main.setGravity(Gravity.CENTER);
        main.setLayoutParams(main_params);
        LayoutParams app_image_params = new LayoutParams(my_demins(12), my_demins(12));
        app_image_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        app_image.setId(app_image_id);
        app_image.setLayoutParams(app_image_params);
        LayoutParams app_title_params = new LayoutParams(my_demins(17), my_demins(4));
        app_title_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        app_title.setId(app_title_id);
        app_title_params.addRule(RelativeLayout.BELOW, app_image_id);
        app_title.setGravity(Gravity.CENTER);
        app_title_params.setMargins(0,my_demins(1),0,0);
        app_title.setLayoutParams(app_title_params);

        app_title.setTextSize(my_demins2(10));

        LayoutParams app_delete_params = new LayoutParams(my_demins(4), my_demins(4));
        app_delete_params.setMargins(my_demins(12),-my_demins(1),0,0);
        app_delete_image.setId(app_delete_image_id);

        app_delete_image.setLayoutParams(app_delete_params);
        addView(main);
        main.addView(app_image);
        main.addView(app_title);
        main.addView(app_delete_image);
        hidden_delete();
        shakeEnable(false);
    }

    public void fill_data(String packet_id, final String url, String title) {
        this.packet_id = packet_id;
        app_title.setText(title);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp = getURLimage(url);
                Message msg = new Message();
                msg.what = 0;
                msg.obj = bmp;
                handle.sendMessage(msg);
            }
        }).start();

        //图片显示




        //图片显示
    }

    public void shakeEnable(boolean enable){
        if(enable){
            setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnDeleteClickListener != null)
                        mOnDeleteClickListener.OnShake();
                    return true;
                }
            });
        }else{
            setOnLongClickListener(null);
        }
    }


    public void show_delete(int res_id,boolean show) {
        if(show) {
            app_delete_image.setImageResource(res_id);
            app_delete_image.setVisibility(VISIBLE);
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnDeleteClickListener != null)
                        mOnDeleteClickListener.OnDelete(packet_id);
                }
            });
        }else{
            app_delete_image.setVisibility(INVISIBLE);
            setOnClickListener(null);
        }

    }

    OnAppIconListener mOnDeleteClickListener;

    public void setOnDeleteClickListener(OnAppIconListener mOnDeleteClickListener) {
        this.mOnDeleteClickListener = mOnDeleteClickListener;
    }

    public void hidden_delete() {
        app_delete_image.setVisibility(INVISIBLE);
        setOnClickListener(null);
    }


    public interface OnAppIconListener {
        void OnDelete(String packet_id);
        void OnShake();
    }

    public Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    //在消息队列中实现对控件的更改
    private Handler handle = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Bitmap bmp = (Bitmap) msg.obj;
                    app_image.setImageBitmap(bmp);
                    break;
            }
        }
    };

    public  void shake(){
        startShakeByPropertyAnim(main,1f, 1f, 5f, 1000*2);
    }

    private void startShakeByPropertyAnim(View view, float scaleSmall, float scaleLarge, float shakeDegrees, long duration) {
        if (view == null) {
            return;
        }
        //先变小后变大
        PropertyValuesHolder scaleXValuesHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1.0f),
                Keyframe.ofFloat(0.25f, scaleSmall),
                Keyframe.ofFloat(0.5f, scaleLarge),
                Keyframe.ofFloat(0.75f, scaleLarge),
                Keyframe.ofFloat(1.0f, 1.0f)
        );
        PropertyValuesHolder scaleYValuesHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1.0f),
                Keyframe.ofFloat(0.25f, scaleSmall),
                Keyframe.ofFloat(0.5f, scaleLarge),
                Keyframe.ofFloat(0.75f, scaleLarge),
                Keyframe.ofFloat(1.0f, 1.0f)
        );

        //先往左再往右
        PropertyValuesHolder rotateValuesHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.1f, -shakeDegrees),
                Keyframe.ofFloat(0.2f, shakeDegrees),
                Keyframe.ofFloat(0.3f, -shakeDegrees),
                Keyframe.ofFloat(0.4f, shakeDegrees),
                Keyframe.ofFloat(0.5f, -shakeDegrees),
                Keyframe.ofFloat(0.6f, shakeDegrees),
                Keyframe.ofFloat(0.7f, -shakeDegrees),
                Keyframe.ofFloat(0.8f, shakeDegrees),
                Keyframe.ofFloat(0.9f, -shakeDegrees),
                Keyframe.ofFloat(1.0f, 0f)
        );

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, scaleXValuesHolder, scaleYValuesHolder, rotateValuesHolder);
        objectAnimator.setDuration(duration);

        objectAnimator.addListener(new Animator.AnimatorListener() {
                                       @Override
                                       public void onAnimationStart(Animator animation) {

                                       }

                                       @Override
                                       public void onAnimationEnd(Animator animation) {

                                       }

                                       @Override
                                       public void onAnimationCancel(Animator animation) {

                                       }

                                       @Override
                                       public void onAnimationRepeat(Animator animation) {

                                       }
                                   });
        objectAnimator.start();


    }
}
