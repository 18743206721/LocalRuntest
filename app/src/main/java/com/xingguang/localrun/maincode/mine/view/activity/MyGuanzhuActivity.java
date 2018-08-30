package com.xingguang.localrun.maincode.mine.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.http.CommonBean;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.view.activity.LookShopActivity;
import com.xingguang.localrun.maincode.mine.model.MineAttentionBean;
import com.xingguang.localrun.maincode.mine.view.adapter.MyAttentionAdapter;
import com.xingguang.localrun.popwindow.TextPopUpWindow;
import com.xingguang.localrun.refresh.RefreshUtil;
import com.xingguang.localrun.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 创建日期：2018/5/25
 * 描述:我关注的
 * 作者:LiuYu
 */
public class MyGuanzhuActivity extends ToolBarActivity implements RefreshUtil.OnRefreshListener {

    @BindView(R.id.rv_attention)
    RecyclerView rvAttention;
    @BindView(R.id.ll_myattention)
    RelativeLayout ll_myattention;
    @BindView(R.id.tw_refresh)
    TwinklingRefreshLayout tw_refresh;
    @BindView(R.id.rl_bg)
    RelativeLayout rl_bg;
    @BindView(R.id.empty)
    ImageView empty;

    MyAttentionAdapter attenAdapter;
    private int currentPositon = 0;
    private TextPopUpWindow popde;
    private View.OnClickListener node;
    private View.OnClickListener yesde;
    private boolean isRefresh = false;
    int total = 1;
    private List<MineAttentionBean.DataBean> mDatas = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_guanzhu;
    }

    @Override
    protected void initView() {
        tw_refresh.setHeaderView(new SinaRefreshView(this));
        tw_refresh.setBottomView(new LoadingView(this));
        tw_refresh.setOnRefreshListener(new RefreshUtil(this).refreshListenerAdapter());

        getToolbarTitle().setText("关注");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        attenAdapter = new MyAttentionAdapter(MyGuanzhuActivity.this, mDatas);
        LinearLayoutManager lmg = new LinearLayoutManager(MyGuanzhuActivity.this);
        rvAttention.setLayoutManager(lmg);
        rvAttention.setAdapter(attenAdapter);

        initListener();
        load(1);
    }

    private void initListener() {
        attenAdapter.setOnItemCancelClickLitener(new MyAttentionAdapter.OnItemCancelClickLitener() {
            @Override
            public void onItemCancelClick(TextView view, int position) {
                currentPositon = position;
                popde = new TextPopUpWindow(MyGuanzhuActivity.this, ll_myattention, "是否取消关注?", "取消", "确定", node, yesde);
            }
        });
        node = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popde.dismiss();
            }
        };
        yesde = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadcancel(currentPositon);
            }
        };


        attenAdapter.setOnItemClickLitener(new MyAttentionAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MyGuanzhuActivity.this, LookShopActivity.class);
                intent.putExtra("shopid", mDatas.get(position).getShop_id());
                startActivity(intent);
            }
        });


    }

    /**
     * 取消关注接口
     */
    private void loadcancel(final int currentPositon) {
        OkGo.<String>post(HttpManager.cancelCollectShop)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("collect_id", mDatas.get(currentPositon).getCollect_id())
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean commonBean = gson.fromJson(response.body().toString(), CommonBean.class);
                        popde.dismiss();
                        mDatas.remove(currentPositon);
                        attenAdapter.notifyDataSetChanged();
                        ToastUtils.showToast(MyGuanzhuActivity.this, commonBean.getMsg());
                    }
                });
    }


    private void load(final int total) {
        OkGo.<String>post(HttpManager.collectShop)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("page", total)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        MineAttentionBean attentionBean = gson.fromJson(response.body().toString(), MineAttentionBean.class);

                        if (total == 1) {
                            mDatas.clear();
                        } else {
                            if (attentionBean.getData().size() == 0) {
                                ToastUtils.showToast(MyGuanzhuActivity.this, "暂无更多!");
                            }
                        }
                        mDatas.addAll(attentionBean.getData());

                        if (mDatas.size() == 0) {
                            rl_bg.setVisibility(View.GONE);
                            empty.setVisibility(View.VISIBLE);
                        } else {
                            empty.setVisibility(View.GONE);
                            rl_bg.setVisibility(View.VISIBLE);
                        }

                        if (isRefresh) {
                            tw_refresh.finishRefreshing();
                        } else {
                            tw_refresh.finishLoadmore();
                        }
                        attenAdapter.setList(mDatas);
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
        total++;
        load(total);
    }
}
