package com.xingguang.localrun.maincode.home.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseActivity;
import com.xingguang.localrun.base.BaseFragmentAdapter;
import com.xingguang.localrun.maincode.home.view.fragment.LookShopFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
    String[] mTitles = new String[]{"首页","全部宝贝","新品"};
    LookShopFragment listFragment;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_look_shop;
    }

    @Override
    protected void initView() {
        initViewPage();
    }


    private void initViewPage() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            listFragment = new LookShopFragment(i+1+"");
            mFragments.add(listFragment);
        }
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mPagerShop.setAdapter(adapter);
        xtabShop.setupWithViewPager(mPagerShop);
        mPagerShop.setOffscreenPageLimit(0);
    }



}
