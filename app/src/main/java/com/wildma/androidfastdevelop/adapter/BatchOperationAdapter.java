package com.wildma.androidfastdevelop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.bean.BatchOperationBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Author   wildma
 * Source   guohaosir
 * Date     2018/09/16
 * Desc     ${RecyclerView批量操作adapter}
 */
public class BatchOperationAdapter extends RecyclerView.Adapter<BatchOperationAdapter.ViewHolder> {

    private static final int MODE_CHECK = 0;//查看模式
    int mMode = MODE_CHECK;//当前模式(查看或编辑)

    private Context                  mContext;
    private List<BatchOperationBean> mBatchOperationBeanList;
    private OnItemClickListener      mOnItemClickListener;

    public BatchOperationAdapter(Context context) {
        this.mContext = context;
    }

    public void notifyAdapter(List<BatchOperationBean> batchOperationBeanList, boolean isAdd) {
        if (!isAdd) {
            this.mBatchOperationBeanList = batchOperationBeanList;
        } else {
            this.mBatchOperationBeanList.addAll(batchOperationBeanList);
        }
        notifyDataSetChanged();
    }

    public List<BatchOperationBean> getBatchOperationBeanList() {
        if (mBatchOperationBeanList == null) {
            mBatchOperationBeanList = new ArrayList<>();
        }
        return mBatchOperationBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_batch_operation, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mBatchOperationBeanList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BatchOperationBean batchOperationBean = mBatchOperationBeanList.get(holder.getAdapterPosition());
        holder.mTvTitle.setText(batchOperationBean.getTitle());
        holder.mTvSource.setText(batchOperationBean.getSource());
        if (mMode == MODE_CHECK) {
            holder.mCheckBox.setVisibility(View.GONE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
            if (batchOperationBean.isSelect()) {
                holder.mCheckBox.setImageResource(R.drawable.ic_checked);
            } else {
                holder.mCheckBox.setImageResource(R.drawable.ic_uncheck);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClickListener(holder.getAdapterPosition(), mBatchOperationBeanList);
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mCheckBox;
        private TextView  mTvTitle;
        private TextView  mTvSource;

        public ViewHolder(View itemView) {
            super(itemView);
            mCheckBox = (ImageView) itemView.findViewById(R.id.iv_check);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTvSource = (TextView) itemView.findViewById(R.id.tv_source);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int pos, List<BatchOperationBean> batchOperationBean);
    }

    /**
     * 设置当前模式
     *
     * @param mode
     */
    public void setMode(int mode) {
        mMode = mode;
        notifyDataSetChanged();
    }
}
