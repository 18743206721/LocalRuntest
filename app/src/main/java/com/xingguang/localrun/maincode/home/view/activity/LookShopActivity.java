package com.xingguang.localrun.maincode.home.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.base.BaseActivity;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseFragmentAdapter;
import com.xingguang.localrun.http.CommonBean;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.model.ShopJianjieBean;
import com.xingguang.localrun.maincode.home.view.fragment.LookShopFragment;
import com.xingguang.localrun.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建日期：2018/5/18
 * 描述:店铺页面
 * 作者:LiuYu
 */
public class LookShopActivity extends BaseActivity {

    @BindView(R.id.xtab_shop)
    XTabLayout xtabShop;
    @BindView(R.id.mPager_shop)
    ViewPager mPagerShop;

    List<Fragment> mFragments;
    String[] mTitles = new String[]{"首页", "全部宝贝", "新品"};
    LookShopFragment listFragment;
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_subtitle)
    TextView toolbarSubtitle;
    @BindView(R.id.toolbar_subimg)
    ImageView toolbarSubimg;

    public static LookShopActivity instance;
    //是否关注
    private boolean isshow = false;
    private String shopid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_look_shop;
    }

    @Override
    protected void initView() {
        instance = this;
        toolbarSubtitle.setText("商店");
        shopid = getIntent().getStringExtra("shopid");
        if (shopid!=null) {
            loadjianjie();
        }
        initViewPage();
    }

    /**
     * 店铺简介
     * */
    private void loadjianjie() {
        OkGo.<String>post(HttpManager.Shopjianjie)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id", shopid)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        ShopJianjieBean jianjieBean = gson.fromJson(response.body().toString(), ShopJianjieBean.class);
                        if (jianjieBean.getData()!=null) {
                            if (jianjieBean.getData().getIs_collected() == 0) { //未关注
                                toolbarSubimg.setImageResource(R.mipmap.attention_bg);
                            } else { //已关注
                                toolbarSubimg.setImageResource(R.mipmap.attention_cancel);
                            }
                        }
                    }
                });
    }

    /**
     * 关注
     */
    private void loadAttention() {
        isshow = !isshow;
        if (isshow) {
            OkGo.<String>post(HttpManager.Shopcollect)
                    .tag(this)
                    .cacheKey("cachePostKey")
                    .cacheMode(CacheMode.DEFAULT)
                    .params("token", AppUtil.getUserId(this))
                    .params("shop_id", shopid)
                    .execute(new DialogCallback<String>(this) {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Gson gson = new Gson();
                            CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                            toolbarSubimg.setImageResource(R.mipmap.attention_cancel);
                            ToastUtils.showToast(LookShopActivity.this,"关注成功!");
                        }
                    });
        } else {
            OkGo.<String>post(HttpManager.cancelCollectShop)
                    .tag(this)
                    .cacheKey("cachePostKey")
                    .cacheMode(CacheMode.DEFAULT)
                    .params("token", AppUtil.getUserId(this))
                    .params("collect_id", shopid)
                    .execute(new DialogCallback<String>(this) {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Gson gson = new Gson();
                            CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                            toolbarSubimg.setImageResource(R.mipmap.attention_bg);
                            ToastUtils.showToast(LookShopActivity.this,"取消关注!");
                        }
                    });
        }
    }


    private void initViewPage() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            listFragment = new LookShopFragment(i + 1, shopid,mPagerShop);
            mFragments.add(listFragment);
        }
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mPagerShop.setAdapter(adapter);
        xtabShop.setupWithViewPager(mPagerShop);
        mPagerShop.setOffscreenPageLimit(0);
    }

    @OnClick({R.id.toolbar_back, R.id.toolbar_subimg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.toolbar_subimg:
                if (AppUtil.isExamined(LookShopActivity.this)) {
                    loadAttention();
                }
                break;
        }
    }
}
