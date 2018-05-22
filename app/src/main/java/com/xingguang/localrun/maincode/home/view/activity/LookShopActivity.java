package com.xingguang.localrun.maincode.home.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseFragmentAdapter;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.maincode.home.view.fragment.LookShopFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建日期：2018/5/18
 * 描述:店铺页面
 * 作者:LiuYu
 */
public class LookShopActivity extends ToolBarActivity {

    @BindView(R.id.xtab_shop)
    XTabLayout xtabShop;
    @BindView(R.id.mPager_shop)
    ViewPager mPagerShop;

    List<Fragment> mFragments;
    String[] mTitles = new String[]{"首页","全部宝贝","新品"};
    LookShopFragment listFragment;




    @Override
    protected int getLayoutId() {
        return R.layout.fragment_look_clshop;
    }

    @Override
    protected void initView() {
        getToolbarTitle().setText("商店");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
