package com.xingguang.localrun.maincode.home.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.base.BaseFragment;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.model.ShopAllGoodBean;
import com.xingguang.localrun.maincode.home.model.ShopBannerBean;
import com.xingguang.localrun.maincode.home.model.ShopIndex;
import com.xingguang.localrun.maincode.home.model.ShopJianjieBean;
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
    private LookShopAdapter adapter;
    private List<ShopBannerBean.DataBean> bannerList = new ArrayList<>(); //banner数据集合
    private List<ShopIndex.DataBean> indexlist = new ArrayList<>();//推荐新品集合
    private ShopJianjieBean.DataBean jianjie = new ShopJianjieBean.DataBean();//店铺首页简介
    private List<ShopAllGoodBean.DataBean> allgoodlist = new ArrayList<>();//店铺全部宝贝集合
    int type;
    String shopid;
    int total = 0;
    int count;
    private int page = 1;//页数
    ViewPager viewPager;

    public LookShopFragment(int type, String shopid, ViewPager mPagerShop) {
        this.type = type;
        this.shopid = shopid;
        this.viewPager = mPagerShop;
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

        initAdapter();
        if (type == 1) {
            loadbanner(0);
            loadIndex(0);
            loadjianjie();
        } else if (type == 2) {
            loadall(1);
        } else {
            loadnews(1);
        }
        initListener();
    }

    private void initAdapter() {
        adapter = new LookShopAdapter(getActivity(), bannerList, indexlist, allgoodlist, jianjie, type);
        if (type == 1) {
            LinearLayoutManager mgr = new LinearLayoutManager(getActivity());
            rvLooksp.setLayoutManager(mgr);
            rvLooksp.setAdapter(adapter);
        } else {
            GridLayoutManager mgr = new GridLayoutManager(getActivity(), 2);
            rvLooksp.setLayoutManager(mgr);
            rvLooksp.setAdapter(adapter);
        }
    }

    /**
     * 轮播图
     */
    private void loadbanner(final int total) {
        OkGo.<String>post(HttpManager.Shopbanner)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id", shopid)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ShopBannerBean shopBannerBean = gson.fromJson(response.body().toString(), ShopBannerBean.class);
                        if (shopBannerBean.getData() != null) {
                            if (total == 0) {
                                bannerList.clear();
                            }
                            bannerList.addAll(shopBannerBean.getData());
                            adapter.setList(bannerList);
                        } else {
                            ToastUtils.showToast(getActivity(), shopBannerBean.getMsg());
                        }

                        if (isRefresh) {
                            tw_mylook.finishRefreshing();
                        } else {
                            tw_mylook.finishLoadmore();
                        }

                    }
                });
    }

    /**
     * 推荐新品
     */
    private void loadIndex(final int total) {
        OkGo.<String>post(HttpManager.Shopindex)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id", shopid)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ShopIndex index = gson.fromJson(response.body().toString(), ShopIndex.class);
                        if (index.getData() != null) {

                            if (total == 0) {
                                indexlist.clear();
                            }
                            indexlist.addAll(index.getData());
                            adapter.setListIndex(indexlist);
                        } else {
                            ToastUtils.showToast(getActivity(), index.getMsg());
                        }
                        if (isRefresh) {
                            tw_mylook.finishRefreshing();
                        } else {
                            tw_mylook.finishLoadmore();
                        }
                    }
                });
    }

    /**
     * 店铺简介
     */
    private void loadjianjie() {
        OkGo.<String>post(HttpManager.Shopjianjie)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id", shopid)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ShopJianjieBean jianjieBean = gson.fromJson(response.body().toString(), ShopJianjieBean.class);
                        if (jianjieBean.getData() != null) {
                            adapter.setjianjie(jianjieBean.getData());
                        } else {
                            ToastUtils.showToast(getActivity(), jianjieBean.getMsg());
                        }
                        if (isRefresh) {
                            tw_mylook.finishRefreshing();
                        } else {
                            tw_mylook.finishLoadmore();
                        }
                    }
                });
    }

    /**
     * 全部宝贝
     */
    private void loadall(final int page) {
        OkGo.<String>post(HttpManager.Shopgoods)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id", shopid)
                .params("page", page)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ShopAllGoodBean allGoodBean = gson.fromJson(response.body().toString(), ShopAllGoodBean.class);
                        if (allGoodBean.getData() != null) {

                            if (allGoodBean.getData().size() == 0 && page != 1) {
                                Toast.makeText(getActivity(),
                                        "只有这么多了~",
                                        Toast.LENGTH_SHORT).show();
                            }

                            if (page == 1) {
                                allgoodlist.clear();
                            }
                            allgoodlist.addAll(allGoodBean.getData());

                            if (isRefresh) {
                                tw_mylook.finishRefreshing();
                            } else {
                                tw_mylook.finishLoadmore();
                            }

                            adapter.setgoodsList(allgoodlist);

                        } else {
                            ToastUtils.showToast(getActivity(), allGoodBean.getMsg());
                        }

                    }
                });
    }

    /**
     * 新品
     */
    private void loadnews(final int page) {
        OkGo.<String>post(HttpManager.shopnews)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id", shopid)
                .params("page", page)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ShopAllGoodBean allGoodBean = gson.fromJson(response.body().toString(), ShopAllGoodBean.class);
                        if (allGoodBean.getData() != null) {
                            if (allGoodBean.getData().size() == 0 && page != 1) {
                                Toast.makeText(getActivity(),
                                        "只有这么多了~",
                                        Toast.LENGTH_SHORT).show();
                            }

                            if (page == 1) {
                                allgoodlist.clear();
                            }
                            allgoodlist.addAll(allGoodBean.getData());
                            if (isRefresh) {
                                tw_mylook.finishRefreshing();
                            } else {
                                tw_mylook.finishLoadmore();
                            }
                            adapter.setnewsgoodsList(allgoodlist);
                        } else {
                            ToastUtils.showToast(getActivity(), allGoodBean.getMsg());
                        }

                    }
                });
    }

    private void initListener() {
        //更多分类
        adapter.setmOnItemmoreClickListener(new LookShopAdapter.OnItemmoreClickListener() {
            @Override
            public void onItemmoreClick(View view, int position) {
                viewPager.setCurrentItem(1);
            }
        });

        adapter.setOnItemClickListener(new LookShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ProductdetailsActivity.class);
                intent.putExtra("goods_id", allgoodlist.get(position).getGoods_id());
                intent.putExtra("lookshop", 1);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        if (type == 1) {
            loadbanner(0);
            loadIndex(0);
        } else if (type == 2) {
            loadall(page);
        } else {
            loadnews(page++);
        }
    }

    @Override
    public void onLoad() {
        isRefresh = false;
        if (type == 1) {
            loadbanner(0);
            total += count;
            loadIndex(0);
        } else if (type == 2) {
            loadall(page++);
        } else {
            loadnews(page++);
        }
    }

}
