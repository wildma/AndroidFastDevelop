package com.wildma.androidfastdevelop.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.adapter.BatchOperationAdapter;
import com.wildma.androidfastdevelop.bean.BatchOperationBean;
import com.wildma.androidfastdevelop.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * Author   wildma
 * Source   guohaosir
 * Date     2018/09/16
 * Desc     ${RecyclerView批量操作demo}
 */
public class BatchOperationActivity extends Activity implements View.OnClickListener, BatchOperationAdapter.OnItemClickListener {

    private TextView     mBtnEditor;
    private RecyclerView mRecyclerview;
    private LinearLayout mLlBottomLayout;
    private TextView     mTvSelectNum;
    private Button       mBtnDelete;
    private Button       mBtnAllSelect;

    private BatchOperationAdapter mBatchOperationAdapter = null;
    private LinearLayoutManager mLinearLayoutManager;
    private              List<BatchOperationBean> mList        = new ArrayList<>();
    private static final int                      MODE_CHECK   = 0;//查看模式
    private static final int                      MODE_EDIT    = 1;//编辑模式
    private              int                      mMode        = MODE_CHECK;//当前模式(查看或编辑)
    private              boolean                  isAllSelect  = false;//是否是全选
    private              boolean                  isEditStatus = false;//是否是编辑状态
    private              int                      mSelectNum   = 0;//选择的数量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_operation);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        mBtnEditor = (TextView) findViewById(R.id.tv_edit);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        mLlBottomLayout = (LinearLayout) findViewById(R.id.ll_bottom_layout);
        mTvSelectNum = (TextView) findViewById(R.id.tv_select_num);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnAllSelect = (Button) findViewById(R.id.btn_all_select);
    }

    private void initData() {
        mBatchOperationAdapter = new BatchOperationAdapter(this);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLinearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, true);
        dividerItemDecoration.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.shape_common_divider));
        mRecyclerview.addItemDecoration(dividerItemDecoration);
        mRecyclerview.setAdapter(mBatchOperationAdapter);
        for (int i = 0; i < 30; i++) {
            BatchOperationBean batchOperationBean = new BatchOperationBean();
            batchOperationBean.setTitle("这是第" + i + "个条目");
            batchOperationBean.setSource("来源" + i);
            mList.add(batchOperationBean);
            mBatchOperationAdapter.notifyAdapter(mList, false);
        }
    }

    private void initListener() {
        mBatchOperationAdapter.setOnItemClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnAllSelect.setOnClickListener(this);
        mBtnEditor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                delete();
                break;
            case R.id.btn_all_select:
                setAllSelectButtonStyle();
                break;
            case R.id.tv_edit:
                setEditOrCancelMode();
                break;
            default:
                break;
        }
    }

    /**
     * 设置删除按钮样式
     *
     * @param selectNum 选中的数量
     */
    private void setDeleteButtonStyle(int selectNum) {
        if (selectNum != 0) {
            mBtnDelete.setBackgroundResource(R.drawable.shape_bg_cbac73_corners_6dp);
            mBtnDelete.setEnabled(true);
            mBtnDelete.setTextColor(ContextCompat.getColor(this, R.color.white));
        } else {
            mBtnDelete.setBackgroundResource(R.drawable.shape_bg_8f7a55_corners_6dp);
            mBtnDelete.setEnabled(false);
            mBtnDelete.setTextColor(ContextCompat.getColor(this, R.color.color_b7b8bd));
        }
    }

    /**
     * 设置全选、反选按钮样式
     */
    private void setAllSelectButtonStyle() {
        if (mBatchOperationAdapter == null)
            return;
        if (!isAllSelect) {
            for (int i = 0; i < mBatchOperationAdapter.getBatchOperationBeanList().size(); i++) {
                mBatchOperationAdapter.getBatchOperationBeanList().get(i).setSelect(true);
            }
            mSelectNum = mBatchOperationAdapter.getBatchOperationBeanList().size();
            mBtnDelete.setEnabled(true);
            mBtnAllSelect.setText(getString(R.string.cancel_all_selection));
            isAllSelect = true;
        } else {
            for (int i = 0; i < mBatchOperationAdapter.getBatchOperationBeanList().size(); i++) {
                mBatchOperationAdapter.getBatchOperationBeanList().get(i).setSelect(false);
            }
            mSelectNum = 0;
            mBtnDelete.setEnabled(false);
            mBtnAllSelect.setText(getString(R.string.all_selection));
            isAllSelect = false;
        }
        mBatchOperationAdapter.notifyDataSetChanged();
        setDeleteButtonStyle(mSelectNum);
        mTvSelectNum.setText(String.valueOf(mSelectNum));
    }

    /**
     * 删除
     */
    private void delete() {
        if (mSelectNum == 0) {
            mBtnDelete.setEnabled(false);
            return;
        }
        final AlertDialog builder = new AlertDialog.Builder(this).create();
        builder.show();
        if (builder.getWindow() == null)
            return;
        builder.getWindow().setContentView(R.layout.dialog_delete_hint);
        TextView mTvMsg = (TextView) builder.findViewById(R.id.tv_msg);
        Button mBtnCancle = (Button) builder.findViewById(R.id.btn_cancle);
        Button mBtnConfirm = (Button) builder.findViewById(R.id.btn_confirm);
        if (mTvMsg == null || mBtnCancle == null || mBtnConfirm == null)
            return;

        if (mSelectNum == 1) {
            mTvMsg.setText(getString(R.string.batch_operation_delete_hint));
        } else {
            mTvMsg.setText(String.format(getString(R.string.batch_operation_delete_hint2), String.valueOf(mSelectNum)));
        }
        mBtnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = mBatchOperationAdapter.getBatchOperationBeanList().size(); i > 0; i--) {
                    BatchOperationBean batchOperationBean = mBatchOperationAdapter.getBatchOperationBeanList().get(i - 1);
                    if (batchOperationBean.isSelect()) {
                        mBatchOperationAdapter.getBatchOperationBeanList().remove(batchOperationBean);
                        mSelectNum--;
                    }
                }
                mSelectNum = 0;
                mTvSelectNum.setText(String.valueOf(0));
                setDeleteButtonStyle(mSelectNum);
                if (mBatchOperationAdapter.getBatchOperationBeanList().size() == 0) {
                    mLlBottomLayout.setVisibility(View.GONE);
                }
                mBatchOperationAdapter.notifyDataSetChanged();
                builder.dismiss();
            }
        });
    }

    /**
     * 设置编辑或者取消模式
     */
    private void setEditOrCancelMode() {
        mMode = mMode == MODE_CHECK ? MODE_EDIT : MODE_CHECK;
        if (mMode == MODE_EDIT) {
            mBtnEditor.setText(getString(R.string.cancel));
            mLlBottomLayout.setVisibility(View.VISIBLE);
            isEditStatus = true;
        } else {
            mBtnEditor.setText(getString(R.string.edit));
            mLlBottomLayout.setVisibility(View.GONE);
            isEditStatus = false;

            /*重置数据与UI*/
            isAllSelect = true;
            setAllSelectButtonStyle();
        }
        mBatchOperationAdapter.setMode(mMode);
    }

    @Override
    public void onItemClickListener(int pos, List<BatchOperationBean> batchOperationBeanList) {
        if (isEditStatus) {
            BatchOperationBean batchOperationBean = batchOperationBeanList.get(pos);
            boolean isSelect = batchOperationBean.isSelect();
            if (!isSelect) {
                mSelectNum++;
                batchOperationBean.setSelect(true);
                if (mSelectNum == batchOperationBeanList.size()) {
                    isAllSelect = true;
                    mBtnAllSelect.setText(getString(R.string.cancel_all_selection));
                }
            } else {
                mSelectNum--;
                batchOperationBean.setSelect(false);
                isAllSelect = false;
                mBtnAllSelect.setText(getString(R.string.all_selection));
            }
            setDeleteButtonStyle(mSelectNum);
            mTvSelectNum.setText(String.valueOf(mSelectNum));
            mBatchOperationAdapter.notifyDataSetChanged();
        }
    }
}
