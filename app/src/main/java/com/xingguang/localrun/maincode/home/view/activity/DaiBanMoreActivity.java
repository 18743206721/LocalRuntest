package com.xingguang.localrun.maincode.home.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.maincode.home.view.adapter.DaiBanMoreAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 创建日期：2018/5/25
 * 描述:代办列表页面
 * 作者:LiuYu
 */
public class DaiBanMoreActivity extends ToolBarActivity {

    @BindView(R.id.rv_daiban)
    RecyclerView rvDaiban;
    private List<String> attenList = new ArrayList<>();

    DaiBanMoreAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dai_ban_more;
    }

    @Override
    protected void initView() {
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setToolBarTitle("代办");

        initAdapter();
        load();
    }

    private void initAdapter() {

        adapter = new DaiBanMoreAdapter(DaiBanMoreActivity.this,attenList);
        LinearLayoutManager lmg = new LinearLayoutManager(DaiBanMoreActivity.this);
        rvDaiban.setLayoutManager(lmg);
        rvDaiban.setAdapter(adapter);

        adapter.setOnItemClickLitener(new DaiBanMoreAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(TextView view, int position) {
                startActivity(new Intent(DaiBanMoreActivity.this,DaiBanDetailsActivity.class));
            }
        });

    }


    private void load() {

    }



}
