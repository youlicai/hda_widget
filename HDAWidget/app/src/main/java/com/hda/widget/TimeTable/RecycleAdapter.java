package com.hda.widget.TimeTable;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import java.util.HashMap;
import java.util.List;

public class RecycleAdapter extends Adapter<RecycleAdapter.MyHolder> {

    private List mList;//数据源
    private HashMap map;
    public RecycleAdapter(List list, HashMap map) {
        mList = list;
        this.map=map;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TableItemView view=new TableItemView(parent.getContext());
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    //通过方法提供的ViewHolder，将数据绑定到ViewHolder中
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        if(map!=null) {
            if (map.containsKey(position)) {
                holder.itemView1.selected();
            } else {
                holder.itemView1.restored();
            }
        }else{
            holder.itemView1.restored();
        }
        holder.textView.setText(String.format("%02d", mList.get(position)));
    }

    //获取数据源总的条数
    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 自定义的ViewHolder
     */
    class MyHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TableItemView itemView1;
        public MyHolder(View itemView) {
            super(itemView);
            itemView1= (TableItemView) itemView;
            textView = itemView1.getTextView();
        }
    }
}