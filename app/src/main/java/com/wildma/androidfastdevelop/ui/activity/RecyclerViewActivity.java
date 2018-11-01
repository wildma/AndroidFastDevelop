package com.wildma.androidfastdevelop.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wildma.androidfastdevelop.R;
import com.wildma.androidfastdevelop.adapter.RecycleViewAdapter;
import com.wildma.androidfastdevelop.adapter.RecycleViewStaggeredGridAdapter;
import com.wildma.androidfastdevelop.widget.DividerGridItemDecoration;
import com.wildma.androidfastdevelop.widget.DividerLinearItemDecoration;

import java.util.ArrayList;
import java.util.List;

import me.ele.uetool.UETool;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/10/28
 * Desc	        ${RecyclerView demo}
 */

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView                    mRecyclerView;
    private TextView                        mTv_reference;
    private RecycleViewAdapter              mAdapter;
    private RecycleViewStaggeredGridAdapter mStaggerdGridDemoAdapter;
    private List<String>                    mTimeList;
    private List<Integer>                   mImgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        UETool.showUETMenu();//打开UI调试工具悬浮窗

        mTv_reference = (TextView) findViewById(R.id.tv_reference);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initData();
        setLinearLayoutManagerVertical();
        mAdapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RecyclerViewActivity.this, mTimeList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mTimeList = new ArrayList<String>();
        for (int i = 10; i < 60; i++) {
            mTimeList.add("10:00-11:" + i);
        }

        int[] mIcons = new int[]{R.drawable.stragger_grid1, R.drawable.stragger_grid2, R.drawable.stragger_grid3};
        mImgList = new ArrayList<Integer>();
        for (int i = 0; i < 30; i++) {
            int position = (int) (Math.random() * 3);
            mImgList.add(mIcons[position]);
        }
    }

    /**
     * 纵向列表
     */
    private void setLinearLayoutManagerVertical() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DividerLinearItemDecoration itemDecoration = new DividerLinearItemDecoration(this, DividerLinearItemDecoration.VERTICAL_LIST, true);
        itemDecoration.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.shape_special_linear_divider));
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = new RecycleViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.initData(mTimeList);
    }

    /**
     * 横向列表
     */
    private void setLinearLayoutManagerHorizontal() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        DividerLinearItemDecoration itemDecoration = new DividerLinearItemDecoration(this, DividerLinearItemDecoration.HORIZONTAL_LIST, true);
        itemDecoration.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.shape_special_linear_divider));
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = new RecycleViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.initData(mTimeList);
    }

    /**
     * 纵向网格
     */
    private void setGridLayoutManagerVertical() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        DividerGridItemDecoration itemDecoration = new DividerGridItemDecoration(30, ContextCompat.getColor(this, R.color.colorPrimary));
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = new RecycleViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.initData(mTimeList);
    }

    /**
     * 横向网格
     */
    private void setGridLayoutManagerHorizontal() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.HORIZONTAL, false));
        //横向的GridLayoutManager的分割线在item_recycleview_demo中设置更佳
        /*DividerGridSpecialItemDecoration itemDecoration = new DividerGridSpecialItemDecoration(this);
        itemDecoration.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.shape_special_linear_divider));
        mRecyclerView.addItemDecoration(itemDecoration);*/
        mAdapter = new RecycleViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.initData(mTimeList);
    }

    /**
     * 纵向瀑布流
     */
    private void setStaggeredGridLayoutManager() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mStaggerdGridDemoAdapter = new RecycleViewStaggeredGridAdapter(this);
        mRecyclerView.setAdapter(mStaggerdGridDemoAdapter);
        mStaggerdGridDemoAdapter.initData(mImgList);
    }

    /**
     * 横向向瀑布流
     */
    private void setStaggeredGridLayoutManagerH() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        mStaggerdGridDemoAdapter = new RecycleViewStaggeredGridAdapter(this);
        mRecyclerView.setAdapter(mStaggerdGridDemoAdapter);
        mStaggerdGridDemoAdapter.initData(mImgList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recycleview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mRecyclerView.getItemDecorationCount() > 0) {
            mRecyclerView.removeItemDecorationAt(0); //移除ItemDecoration
        }
        mTv_reference.setVisibility(View.GONE);
        switch (item.getItemId()) {
            case R.id.action_list_vertical:
                setLinearLayoutManagerVertical();
                break;
            case R.id.action_list_horizontal:
                setLinearLayoutManagerHorizontal();
                break;
            case R.id.action_grid_vertical:
                mTv_reference.setVisibility(View.VISIBLE);
                setGridLayoutManagerVertical();
                break;
            case R.id.action_grid_horizontal:
                setGridLayoutManagerHorizontal();
                break;
            case R.id.action_stragger_grid_vertical:
                setStaggeredGridLayoutManager();
                break;
            case R.id.action_stragger_grid_horizontal:
                setStaggeredGridLayoutManagerH();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
