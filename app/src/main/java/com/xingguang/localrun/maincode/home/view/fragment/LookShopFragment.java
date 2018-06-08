package com.xingguang.localrun.maincode.home.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.xingguang.core.base.BaseFragment;
import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.home.view.activity.ProductdetailsActivity;
import com.xingguang.localrun.maincode.home.view.adapter.LookShopAdapter;
import com.xingguang.localrun.refresh.RefreshUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建日期：2018/5/18
 * 描述:商店列表
 * 作者:LiuYu
 */
@SuppressLint("ValidFragment")
public class LookShopFragment extends BaseFragment implements RefreshUtil.OnRefreshListener {

    @BindView(R.id.rv_looksp)
    RecyclerView rvLooksp;
    @BindView(R.id.my_deco_fg_apply)
    RelativeLayout myDecoFgApply;
    @BindView(R.id.empty)
    ImageView empty;
    @BindView(R.id.tw_mylook)
    TwinklingRefreshLayout tw_mylook;

    private boolean isRefresh = false;
    int total = 0;
    private LookShopAdapter adapter;
    private List<String> looklist = new ArrayList<>();
    int type;

    public static LookShopFragment newInstance(int type) {
        LookShopFragment fragment = new LookShopFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lookshop;
    }

    @Override
    protected void initView() {
        tw_mylook.setHeaderView(new SinaRefreshView(getActivity()));
        tw_mylook.setBottomView(new LoadingView(getActivity()));
        tw_mylook.setOnRefreshListener(new RefreshUtil(this).refreshListenerAdapter());

        Bundle arguments = getArguments();
        if (arguments != null) {
            type = arguments.getInt("type");
        }

        initAdapter();
        load(total);
        initListener();
    }

    private void initAdapter() {
        adapter = new LookShopAdapter(getActivity(),looklist,type);
        if (type == 1){
            LinearLayoutManager mgr = new LinearLayoutManager(getActivity());
            rvLooksp.setLayoutManager(mgr);
            rvLooksp.setAdapter(adapter);
        }else {
            GridLayoutManager mgr = new GridLayoutManager(getActivity(), 2);
            rvLooksp.setLayoutManager(mgr);
            rvLooksp.setAdapter(adapter);
        }
    }

    private void load(int count) {

        if (isRefresh) {
            tw_mylook.finishRefreshing();
        } else {
            tw_mylook.finishLoadmore();
        }

    }

    private void initListener() {
        adapter.setOnItemClickListener(new LookShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ProductdetailsActivity.class);
                intent.putExtra("proid", "2");
                startActivity(intent);
                getActivity().finish();
            }
        });

    }


    @Override
    protected void lazyLoad() {

    }


    @Override
    public void onRefresh() {
        isRefresh = true;
        load(total);
    }

    @Override
    public void onLoad() {
        isRefresh = false;
        load(looklist.size());
    }
}
