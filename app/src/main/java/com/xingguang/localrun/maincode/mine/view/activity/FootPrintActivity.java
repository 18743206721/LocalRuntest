package com.xingguang.localrun.maincode.mine.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.maincode.mine.view.adapter.MyFootPrinAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 创建日期：2018/5/24
 * 描述:我的足迹
 * 作者:LiuYu
 */
public class FootPrintActivity extends ToolBarActivity {

    @BindView(R.id.rv_myfoot)
    RecyclerView rvMyfoot;
    @BindView(R.id.rlmy_footprint)
    RelativeLayout rlmyFootprint;
    @BindView(R.id.empty)
    ImageView empty;

    MyFootPrinAdapter footPrinAdapter;
    private List<String> mList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_foot_print;
    }

    @Override
    protected void initView() {
        getToolbarTitle().setText("足迹");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initAdapter();

        initListener();
        load();

    }

    private void initAdapter() {
        mList.add("a");
        mList.add("2");
        mList.add("3");
        mList.add("4");


        footPrinAdapter = new MyFootPrinAdapter(FootPrintActivity.this,mList);
        LinearLayoutManager lmg = new LinearLayoutManager(FootPrintActivity.this);
        rvMyfoot.setLayoutManager(lmg);
        rvMyfoot.setAdapter(footPrinAdapter);


    }

    private void initListener() {

    }

    private void load() {

    }



}
