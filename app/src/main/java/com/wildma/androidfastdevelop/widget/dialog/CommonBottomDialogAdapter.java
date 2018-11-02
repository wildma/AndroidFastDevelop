package com.wildma.androidfastdevelop.widget.dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wildma.androidfastdevelop.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/10/28
 * Desc	        ${通用底部弹出dialog的Adapter}
 */
public class CommonBottomDialogAdapter extends RecyclerView.Adapter {
    public Context mContext;
    public List<String> mList = new ArrayList<>();

    public CommonBottomDialogAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_common_bottom_dialog, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    public void setDate(ArrayList<String> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).setData(mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Button mBtnItem;

        int position;

        ViewHolder(View view) {
            super(view);
            mBtnItem = view.findViewById(R.id.btn_item);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onItemClick(position);
                    }
                }
            });
        }

        public void setData(final String text, int position) {
            this.position = position;
            mBtnItem.setText(text);
        }
    }

    /*条目点击接口*/
    public interface OnItemListener {
        void onItemClick(int position);
    }

    public static OnItemListener mListener;

    public void setOnItemListener(OnItemListener listener) {
        this.mListener = listener;
    }
}