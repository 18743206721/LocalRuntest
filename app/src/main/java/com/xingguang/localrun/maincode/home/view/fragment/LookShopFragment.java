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

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseFragment;
import com.xingguang.localrun.maincode.home.view.activity.ProductdetailsActivity;
import com.xingguang.localrun.maincode.home.view.adapter.LookShopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建日期：2018/5/18
 * 描述:商店列表
 * 作者:LiuYu
 */
@SuppressLint("ValidFragment")
public class LookShopFragment extends BaseFragment {

    int type;
    @BindView(R.id.rv_looksp)
    RecyclerView rvLooksp;
    @BindView(R.id.my_deco_fg_apply)
    RelativeLayout myDecoFgApply;
    @BindView(R.id.empty)
    ImageView empty;
    private LookShopAdapter adapter;
    private List<String> looklist = new ArrayList<>();

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
        Bundle arguments = getArguments();
        if (arguments != null) {
            type = arguments.getInt("type");
        }

        initAdapter();
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


}
