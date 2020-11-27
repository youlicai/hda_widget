package com.hda.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hda.widget.AppIcon.AppIcon;
import com.hda.widget.AppIcon.AppIconBean;

import java.util.ArrayList;
import java.util.List;
import static com.hda.widget.DimensionConvert.my_demins;

public class HRecyclerView extends LinearLayout {

    public String Tag="";
    private Context context;
    private RecyclerView mRecyclerView;
    private HRecycleAdapter mRecycleAdapter;
    private int padding_left,padding_top,padding_right,padding_button;
    private int spanCount,sum_count;
    private int width,height;
    private boolean show_delete=false;
    private boolean shake_enable=false;
    private int res_id;

    private List<AppIcon> list=new ArrayList<>();
    public HRecyclerView(Context context) {
        super(context);
        this.context=context;
        init();
    }
    public HRecyclerView(Context context,AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }
    private void init(){
        mRecyclerView=new RecyclerView(context);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mRecyclerView.setScrollBarSize(1000);
//        mRecyclerView.setScroll
//        mRecyclerView.getLayoutManager().setAutoMeasureEnabled(false);
        mRecycleAdapter = new HRecycleAdapter();
        mRecyclerView.setAdapter(mRecycleAdapter);
//        mRecyclerView.setItemViewCacheSize(500);
        addView(mRecyclerView);
    }
    List<AppIconBean> mList=new ArrayList<AppIconBean>();
    public class HRecycleAdapter extends RecyclerView.Adapter<HRecycleAdapter.MyHolder> {


        HRecycleAdapter() { }
        public void fill_data(List list){
            if(list!=null) {
                mList.clear();
                mList.addAll(list);
                notifyDataSetChanged();
            }
        }
        public void addData(AppIconBean bean){
            if(bean!=null) {
                mList.add(bean);
                notifyDataSetChanged();
            }
        }

        public void addList(List<AppIconBean> list){
            if(list!=null) {
                mList.addAll(list);
                notifyDataSetChanged();
            }
        }
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            AppIcon view=new AppIcon(parent.getContext());
            view.shakeEnable(shake_enable);
            view.setPadding1(padding_left,padding_top/2,padding_right,0);
            MyHolder holder = new MyHolder(view);
            list.add(holder.appIcon);
            return holder;
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(final MyHolder holder, final int position) {
            Log.e("onBindViewHolder",position+"");
            holder.appIcon.fill_data(mList.get(position).packet_id,mList.get(position).res_id,mList.get(position).title);
            holder.appIcon.setOnDeleteClickListener(new AppIcon.OnAppIconListener() {
                @Override
                public void OnDelete(String packet_id) {
                    if(position>=mList.size()){
                        return;
                    }
                    if(mOnHRecyclerViewListener!=null)
                        mOnHRecyclerViewListener.OnDeleteOneItem(mList.get(position));
                    mList.remove(mList.get(position));

                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,mList.size()-position);
                }
                @Override
                public void OnShake() {
                    shake_shake();
                    if (mOnHRecyclerViewListener != null)
                        mOnHRecyclerViewListener.OnLongClickShake();
                }
            });
            if(show_delete) {
                holder.appIcon.show_delete(res_id,true);
            }
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
        class MyHolder extends RecyclerView.ViewHolder {
            AppIcon appIcon;
            public MyHolder(View itemView) {
                super(itemView);
                appIcon= (AppIcon) itemView;
            }
        }
    }

    private OnHRecyclerViewListener mOnHRecyclerViewListener;
    public void setOnHRecyclerViewListener(OnHRecyclerViewListener mOnHRecyclerViewListener){
        this.mOnHRecyclerViewListener=mOnHRecyclerViewListener;
    }

    public interface OnHRecyclerViewListener{
        void OnDeleteOneItem(AppIconBean bean);
        void OnLongClickShake();
    }

    public void setRecyclerViewWidth(int width){
        this.width=width;
    }
    public void setRecyclerViewSize(int width,int height){
        this.width=width;
        this.height=height;
        LinearLayout.LayoutParams main_params = new LinearLayout.LayoutParams(width,height);
        mRecyclerView.setLayoutParams(main_params);
    }
    public void set_span_count(int spanCount){
        if(spanCount>5){
            Log.e("HRecyclerView ERROR","2< spanCount <6");
            return;
        }
        this.spanCount=spanCount;
        GridLayoutManager layoutManager = new GridLayoutManager(context, spanCount);
        mRecyclerView.setLayoutManager(layoutManager);
        this.padding_left=this.padding_top=this.padding_right=this.padding_button=(width-(my_demins(17)*spanCount))/(spanCount*2);
    }
    public void fill_data(List iconList){
        if(iconList!=null&&iconList.size()>0) {
            mRecycleAdapter.fill_data(iconList);
            sum_count = iconList.size();
            height = (sum_count / spanCount) * (my_demins(17) + padding_left);
        }
    }
    public void addData(AppIconBean bean){
        int pos=0;
        pos=mList.size();
        mList.add(pos,bean);
        mRecycleAdapter.notifyItemInserted(pos);
        mRecycleAdapter.notifyItemRangeChanged(pos,1);
    }

    public  void addList(List<AppIconBean> iconList){
        //需要优化
        int pos=0,count;
        pos=mList.size();
        mList.addAll(iconList);
        count=mList.size();
        mRecycleAdapter.notifyItemInserted(pos);
        mRecycleAdapter.notifyItemRangeChanged(0,iconList.size());
    }
    public void setDeleteImage(int res_id){
        if(res_id!=0) {
            this.res_id = res_id;
        }
    }
    public void shakeLongClickEnable(boolean flag){
        shake_enable=flag;
    }

    public void shake_shake(){
        show_delete = true;
        for (int i=0;i<list.size();i++){
            list.get(i).shake();
            list.get(i).show_delete(res_id,true);
        }
    }

    public void setOver(){
        show_delete=false;
        for (int i=0;i<list.size();i++){
            list.get(i).show_delete(res_id,false);
        }
    }
    public int getHHeight(){
        return height;
    }
}
