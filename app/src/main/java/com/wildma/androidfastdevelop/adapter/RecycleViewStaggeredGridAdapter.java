package com.wildma.androidfastdevelop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wildma.androidfastdevelop.R;

import java.util.List;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/10/28
 * Desc	        ${RecycleView瀑布流 Adapter}
 */

public class RecycleViewStaggeredGridAdapter extends RecyclerView.Adapter<RecycleViewStaggeredGridAdapter.MyViewHolder> {

    Context mContext;
    private List<Integer> mData;

    public RecycleViewStaggeredGridAdapter(Context context) {
        this.mContext = context;
    }

    public void initData(List<Integer> data) {
        this.mData = data;
    }

    public void addDate(List<Integer> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void clearDate() {
        mData.clear();
        notifyDataSetChanged();
    }

    public List<Integer> getData() {
        return mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview_staggered_grid, parent, false);
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
        int position;
        private ImageView mIvImg;

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
            mIvImg = (ImageView) itemView.findViewById(R.id.iv_img);
        }

        public void setData(Integer content, int position) {
            this.position = position;
            mIvImg.setImageResource(content);

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
