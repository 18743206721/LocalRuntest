package com.xingguang.localrun.maincode.home.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 创建日期：2018/5/25
 * 描述:代办详情
 * 作者:LiuYu
 */
public class DaiBanDetailsActivity extends ToolBarActivity {

    @BindView(R.id.iv_daibande)
    ImageView ivDaibande;
    @BindView(R.id.item_tv_ads)
    TextView itemTvAds;
    @BindView(R.id.tv_lianxi)
    TextView tvLianxi;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dai_ban_details;
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

    }

    @OnClick(R.id.tv_lianxi)
    public void onViewClicked() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 1008655));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



}
