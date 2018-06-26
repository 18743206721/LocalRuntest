package com.xingguang.localrun.maincode.home.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseFragmentAdapter;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.maincode.home.view.fragment.LookClassifShopFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/**
 * 首页按钮分类
 * */
public class ClassifShopActivity extends ToolBarActivity {

    @BindView(R.id.xtab_classifshop)
    XTabLayout xtabClassifshop;
    @BindView(R.id.mPager_classifshop)
    ViewPager mPagerClassifshop;

    List<Fragment> mFragments;
    String[] mTitles = new String[]{"综和","销量","热度"};
    LookClassifShopFragment listFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_classif_shop;
    }

    @Override
    protected void initView() {

        getToolbarTitle().setText(getIntent().getStringExtra("name"));
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
            listFragment = LookClassifShopFragment.newInstance(i+1);
            mFragments.add(listFragment);
        }
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mPagerClassifshop.setAdapter(adapter);
        xtabClassifshop.setupWithViewPager(mPagerClassifshop);
        mPagerClassifshop.setOffscreenPageLimit(0);

    }



}
