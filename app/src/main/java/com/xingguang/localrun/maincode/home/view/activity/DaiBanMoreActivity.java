package com.xingguang.localrun.maincode.home.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.model.DaiBanMoreBean;
import com.xingguang.localrun.maincode.home.view.adapter.DaiBanMoreAdapter;
import com.xingguang.localrun.refresh.RefreshUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 创建日期：2018/5/25
 * 描述:代办列表页面
 * 作者:LiuYu
 */
public class DaiBanMoreActivity extends ToolBarActivity implements RefreshUtil.OnRefreshListener  {

    @BindView(R.id.rv_daiban)
    RecyclerView rvDaiban;
    @BindView(R.id.my_deco_fg_apply)
    RelativeLayout myDecoFgApply;
    @BindView(R.id.empty)
    ImageView empty;
    @BindView(R.id.tw_mylook)
    TwinklingRefreshLayout tw_mylook;

    private List<DaiBanMoreBean.DataBean> dataList = new ArrayList<>();
    private boolean isRefresh = false;
    int total = 0;
    DaiBanMoreAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dai_ban_more;
    }

    @Override
    protected void initView() {
        tw_mylook.setHeaderView(new SinaRefreshView(this));
        tw_mylook.setBottomView(new LoadingView(this));
        tw_mylook.setOnRefreshListener(new RefreshUtil(this).refreshListenerAdapter());
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setToolBarTitle("代办");

        initAdapter();
        load(0);
    }

    private void initAdapter() {

        adapter = new DaiBanMoreAdapter(DaiBanMoreActivity.this,dataList);
        LinearLayoutManager lmg = new LinearLayoutManager(DaiBanMoreActivity.this);
        rvDaiban.setLayoutManager(lmg);
        rvDaiban.setAdapter(adapter);

        adapter.setOnItemClickLitener(new DaiBanMoreAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(TextView view, int position) {
                startActivity(new Intent(DaiBanMoreActivity.this,DaiBanDetailsActivity.class)
                .putExtra("danbanid",dataList.get(position).getId())
                );
            }
        });

    }


    private void load(final int total) {
        OkGo.<String>post(HttpManager.Taskindex)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("page", total)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        DaiBanMoreBean daiBanMoreBean = gson.fromJson(response.body().toString(), DaiBanMoreBean.class);
//                        if (tjgoodsBean.getData().size() == 0){
//                            ToastUtils.showToast(DaiBanMoreActivity.this,"暂无更多商铺!");
//                        }else {
//                            adapter.setList(dataList);
//                        }

                        if (total == 0) {
                            dataList.clear();
                        }
                        dataList.addAll(daiBanMoreBean.getData());

                        if (dataList.size() == 0) {
                            myDecoFgApply.setVisibility(View.GONE);
                            empty.setVisibility(View.VISIBLE);
                        } else {
                            empty.setVisibility(View.GONE);
                            myDecoFgApply.setVisibility(View.VISIBLE);
                        }

                        if (isRefresh) {
                            tw_mylook.finishRefreshing();
                        } else {
                            tw_mylook.finishLoadmore();
                        }
                        adapter.setList(dataList);
                    }
                });
    }


    @Override
    public void onRefresh() {
        isRefresh = true;
        load(total);
    }

    @Override
    public void onLoad() {
        isRefresh = false;
        load(dataList.size());
    }


}
