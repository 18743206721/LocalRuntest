package com.xingguang.localrun.maincode.home.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xingguang.core.base.BaseFragment;
import com.xingguang.localrun.R;
import com.xingguang.localrun.maincode.home.view.activity.LookShopActivity;
import com.xingguang.localrun.maincode.home.view.adapter.ShopDianAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * 创建日期：2018/5/18
 * 描述:分类商店列表
 * 作者:LiuYu
 */
@SuppressLint("ValidFragment")
public class LookClassifShopFragment extends BaseFragment {

    @BindView(R.id.rv_looksp)
    RecyclerView rvLooksp;
    @BindView(R.id.my_deco_fg_apply)
    RelativeLayout myDecoFgApply;
    @BindView(R.id.empty)
    ImageView empty;
    int type;
    ShopDianAdapter adapter;
    private List<String> looklist = new ArrayList<>();

    public static LookClassifShopFragment newInstance(int type) {
        LookClassifShopFragment fragment = new LookClassifShopFragment();
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

    @Override
    protected void lazyLoad() {

    }

    private void initAdapter() {
        adapter = new ShopDianAdapter(getActivity(), looklist, type);
        LinearLayoutManager mgr = new LinearLayoutManager(getActivity());
        rvLooksp.setLayoutManager(mgr);
        rvLooksp.setAdapter(adapter);
    }

    private void initListener() {
        adapter.setmOnItemClickListener(new ShopDianAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(TextView view, int position) {
                Intent intent = new Intent(getActivity(), LookShopActivity.class);
//                intent.putExtra("id", listDatas.get(position).getCommodityId());
                startActivity(intent);
            }
        });

    }



}
