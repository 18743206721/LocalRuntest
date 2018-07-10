package com.xingguang.localrun.maincode.home.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.model.TaskdetailBean;
import com.xingguang.localrun.utils.ImageLoader;

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
    String id;
    private String phone;

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
        id = getIntent().getStringExtra("danbanid");

        load();
    }


    private void load() {
        OkGo.<String>post(HttpManager.Taskdetail)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id", id)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        TaskdetailBean taskdetailBean = gson.fromJson(response.body().toString(), TaskdetailBean.class);
                        itemTvAds.setText(taskdetailBean.getData().getDescribe());
                        ImageLoader.getInstance().initGlide(DaiBanDetailsActivity.this).loadImage(
                                HttpManager.INDEX+taskdetailBean.getData().getCover(), ivDaibande);
                        phone = taskdetailBean.getData().getPhone();
                    }
                });
    }

    @OnClick(R.id.tv_lianxi)
    public void onViewClicked() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



}
