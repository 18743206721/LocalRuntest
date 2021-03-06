package com.xingguang.localrun.maincode.home.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.base.BaseFragment;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.classify.model.ClassifBean;
import com.xingguang.localrun.maincode.home.view.activity.LookShopActivity;
import com.xingguang.localrun.maincode.home.view.adapter.ShopDianAdapter;
import com.xingguang.localrun.refresh.RefreshUtil;
import com.xingguang.localrun.refresh.SinaRefreshHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建日期：2018/5/18
 * 描述:分类商店列表
 * 作者:LiuYu
 */
@SuppressLint("ValidFragment")
public class LookClassifShopFragment extends BaseFragment implements RefreshUtil.OnRefreshListener  {

    @BindView(R.id.rv_looksp)
    RecyclerView rvLooksp;
    @BindView(R.id.my_deco_fg_apply)
    RelativeLayout myDecoFgApply;
    @BindView(R.id.empty)
    ImageView empty;
    @BindView(R.id.tw_mylook)
    TwinklingRefreshLayout tw_mylook;

    private boolean isRefresh = false;
    int type;
    ShopDianAdapter adapter;
    private List<ClassifBean.DataBean> looklist = new ArrayList<>();
    int page = 1;
    private int id = 0; //分类id


    public static LookClassifShopFragment newInstance(int type, int id) {
        LookClassifShopFragment fragment = new LookClassifShopFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putInt("id",id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lookshop;
    }

    @Override
    protected void initView() {
        tw_mylook.setHeaderView(new SinaRefreshHeader(getActivity()));
        tw_mylook.setBottomView(new LoadingView(getActivity()));
        tw_mylook.setOnRefreshListener(new RefreshUtil(this).refreshListenerAdapter());
        Bundle arguments = getArguments();
        if (arguments != null) {
            type = arguments.getInt("type");
            id = arguments.getInt("id");
        }
        initAdapter();
        initListener();
    }

    @Override
    protected void lazyLoad() {
        load(1);
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
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), LookShopActivity.class);
                intent.putExtra("shopid",looklist.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void load(final int page) {
        OkGo.<String>post(HttpManager.classifshop)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id",id)
                .params("sort",type)
                .params("page",page)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ClassifBean bean  = gson.fromJson(response.body().toString(), ClassifBean.class);
                        if (bean.getData()!=null) {
                            if (bean.getData().size() == 0 && page != 1) {
                                Toast.makeText(getActivity(),
                                        "只有这么多了~",
                                        Toast.LENGTH_SHORT).show();
                            }

                            if (page == 1 ){
                                looklist.clear();
                            }

                            looklist.addAll(bean.getData());

                            if (looklist.size() == 0) {
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

                            adapter.setList(looklist);

                        }else{
                            ToastUtils.showToast(getActivity(),bean.getMsg());
                        }
                    }
                });
    }


    @Override
    public void onRefresh() {
        isRefresh = true;
        load(page);
    }

    @Override
    public void onLoad() {
        isRefresh = false;
        load(page++);
    }



}
