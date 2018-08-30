package com.xingguang.localrun.maincode.mine.view.activity;

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
import com.xingguang.localrun.maincode.mine.model.ZujiBean;
import com.xingguang.localrun.maincode.mine.view.adapter.MyFootPrinAdapter;
import com.xingguang.localrun.popwindow.TextPopUpWindow;
import com.xingguang.localrun.refresh.RefreshUtil;
import com.xingguang.localrun.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 创建日期：2018/5/24
 * 描述:我的足迹
 * 作者:LiuYu
 */
public class FootPrintActivity extends ToolBarActivity implements RefreshUtil.OnRefreshListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tw_refresh)
    TwinklingRefreshLayout twRefresh;
    @BindView(R.id.rl_bg)
    RelativeLayout rlBg;
    @BindView(R.id.empty)
    ImageView empty;
    @BindView(R.id.rl_parent)
    RelativeLayout rlParent;

    MyFootPrinAdapter footPrinAdapter;
    private int currentPositon = 0;
    private TextPopUpWindow popde;
    private View.OnClickListener node;
    private View.OnClickListener yesde;
    private boolean isRefresh = false;
    int total = 0;
    private List<ZujiBean.DataBean> mDatas = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_foot_print;
    }

    @Override
    protected void initView() {
        twRefresh.setHeaderView(new SinaRefreshView(this));
        twRefresh.setBottomView(new LoadingView(this));
        twRefresh.setOnRefreshListener(new RefreshUtil(this).refreshListenerAdapter());
        getToolbarTitle().setText("足迹");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initAdapter();

        initListener();
        load(0);

    }

    private void initAdapter() {
        footPrinAdapter = new MyFootPrinAdapter(FootPrintActivity.this, mDatas);
        LinearLayoutManager lmg = new LinearLayoutManager(FootPrintActivity.this);
        recyclerview.setLayoutManager(lmg);
        recyclerview.setAdapter(footPrinAdapter);

    }

    private void initListener() {
        footPrinAdapter.setOnItemCancelClickLitener(new MyFootPrinAdapter.OnItemCancelClickLitener() {
            @Override
            public void onItemCancelClick(TextView view, int position) {

                currentPositon = position;
                popde = new TextPopUpWindow(FootPrintActivity.this, rlParent, "是否删除足迹?", "取消", "确定", node, yesde);
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

    }

    /**
     * 删除接口
     * */
    private void loadcancel(final int currentPositon) {
        OkGo.<String>post(HttpManager.delVisit)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("date", mDatas.get(currentPositon).getDate())
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean commonBean = gson.fromJson(response.body().toString(), CommonBean.class);
                        popde.dismiss();
                        mDatas.remove(currentPositon);
                        footPrinAdapter.notifyDataSetChanged();
                        ToastUtils.showToast(FootPrintActivity.this,commonBean.getMsg());

                        if (mDatas.size() == 0) {
                            rlBg.setVisibility(View.GONE);
                            empty.setVisibility(View.VISIBLE);
                        } else {
                            empty.setVisibility(View.GONE);
                            rlBg.setVisibility(View.VISIBLE);
                        }

                    }
                });
    }

    private void load(final int total) {
        OkGo.<String>post(HttpManager.visit)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ZujiBean zujiBean = gson.fromJson(response.body().toString(), ZujiBean.class);

                        if (total == 0) {
                            mDatas.clear();
                        } else {
                            if (zujiBean.getData().size() == 0) {
                                ToastUtils.showToast(FootPrintActivity.this, "暂无更多!");
                            }
                        }
                        mDatas.addAll(zujiBean.getData());

                        if (mDatas.size() == 0) {
                            rlBg.setVisibility(View.GONE);
                            empty.setVisibility(View.VISIBLE);
                        } else {
                            empty.setVisibility(View.GONE);
                            rlBg.setVisibility(View.VISIBLE);
                        }

                        if (isRefresh) {
                            twRefresh.finishRefreshing();
                        } else {
                            twRefresh.finishLoadmore();
                        }
                        footPrinAdapter.setList(mDatas);
                    }
                });
    }


    @Override
    public void onRefresh() {
        isRefresh = true;
        load(0);
    }

    @Override
    public void onLoad() {
        isRefresh = false;
        load(0);
    }



}
