package com.xingguang.localrun.maincode.home;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseFragment;
import com.xingguang.localrun.maincode.home.model.GlideImageLoader;
import com.xingguang.localrun.maincode.home.view.activity.LookShopActivity;
import com.xingguang.localrun.maincode.home.view.adapter.HomeDaiAdapter;
import com.xingguang.localrun.maincode.home.view.adapter.HomeDaiBanAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.ll_main_fenlei)
    LinearLayout llMainFenlei;
    @BindView(R.id.ll_main_selecte)
    LinearLayout llMainSelecte;
    @BindView(R.id.ll_home_dijia)
    LinearLayout llHomeDijia;
    @BindView(R.id.ll_home_food)
    LinearLayout llHomeFood;
    @BindView(R.id.ll_outfit)
    LinearLayout llOutfit;
    @BindView(R.id.ll_supermarket)
    LinearLayout llSupermarket;
    @BindView(R.id.ll_home_fruit)
    LinearLayout llHomeFruit;
    @BindView(R.id.ll_home_hardware)
    LinearLayout llHomeHardware;
    @BindView(R.id.main_home_lv)
    RecyclerView mainHomeLv;
    @BindView(R.id.main_homelv_daiban)
    RecyclerView mainHomelvDaiban;

    private HomeDaiAdapter daigouadapter;
    private HomeDaiBanAdapter daibanadapter;


    private List<String> networkImages;
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg"};
    private Intent intent;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        initpage();

        initAdapter();

        initListener();

    }


    private void initListener() {

        daigouadapter.setmOnItemLookshopListener(new HomeDaiAdapter.OnItemLookshopListener() {
            @Override
            public void OnItemLookshop(View view, int position) {
                //跳转到店铺页面
                intent = new Intent(getActivity(),LookShopActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        daigouadapter = new HomeDaiAdapter(getActivity());
        GridLayoutManager mgr = new GridLayoutManager(getActivity(), 2);
        mainHomeLv.setLayoutManager(mgr);
        mainHomeLv.setAdapter(daigouadapter);
        mainHomeLv.setNestedScrollingEnabled(false);

        daibanadapter = new HomeDaiBanAdapter(getActivity());
        LinearLayoutManager lmg = new LinearLayoutManager(getActivity());
        mainHomelvDaiban.setLayoutManager(lmg);
        mainHomelvDaiban.setAdapter(daibanadapter);
        mainHomelvDaiban.setNestedScrollingEnabled(false);

    }

    private void initpage() {
        networkImages = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            networkImages.add(images[i]);
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(networkImages);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    @Override
    protected void lazyLoad() {
        //写网络请求

    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @OnClick({R.id.ll_main_fenlei, R.id.ll_main_selecte, R.id.ll_home_dijia, R.id.ll_home_food, R.id.ll_outfit, R.id.ll_supermarket, R.id.ll_home_fruit, R.id.ll_home_hardware})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main_fenlei://分类
                break;
            case R.id.ll_main_selecte://精选
                break;
            case R.id.ll_home_dijia://低价
                break;
            case R.id.ll_home_food://美食
                break;
            case R.id.ll_outfit://美装
                break;
            case R.id.ll_supermarket://超市
                break;
            case R.id.ll_home_fruit://果蔬
                break;
            case R.id.ll_home_hardware://五金
                break;
        }
    }
}
