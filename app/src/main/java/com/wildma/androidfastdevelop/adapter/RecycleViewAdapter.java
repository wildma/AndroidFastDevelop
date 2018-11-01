package com.wildma.androidfastdevelop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildma.androidfastdevelop.R;

import java.util.List;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/10/28
 * Desc	        ${RecycleView列表与网格 Adapter}
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    Context mContext;
    private List<String> mData;

    public RecycleViewAdapter(Context context) {
        this.mContext = context;
    }

    public void initData(List<String> data) {
        this.mData = data;
    }

    public void addDate(List<String> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void clearDate() {
        mData.clear();
        notifyDataSetChanged();
    }

    public List<String> getData() {
        return mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ((MyViewHolder) holder).setData(mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        } else {
            return mData.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        int      position;
        TextView mTvTime;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
            mTvTime = (TextView) view.findViewById(R.id.tv_time);
        }

        public void setData(String content, int position) {
            this.position = position;
            mTvTime.setText(content);
            /*针对纵向网格三列布局，实现左右两列贴边，中间居中*/
            /*if ((position + 1) % 3 == 0) { //第一列
                mTvTime.setGravity(Gravity.RIGHT);
            } else if ((position) % 3 == 0) { //最后一列
                mTvTime.setGravity(Gravity.LEFT);
            } else {
                mTvTime.setGravity(Gravity.CENTER_HORIZONTAL);
            }*/
        }
    }

    /*item点击接口回调*/
    public static OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
