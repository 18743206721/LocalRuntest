package com.xingguang.localrun.maincode.home.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseActivity;
import com.xingguang.localrun.base.BaseFragmentAdapter;
import com.xingguang.localrun.maincode.home.view.fragment.LookShopFragment;
import com.xingguang.localrun.utils.ToastUtils;

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

    //是否关注
    private boolean isshow;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_look_shop;
    }

    @Override
    protected void initView() {
        toolbarSubtitle.setText("商店");
        initViewPage();
        initListener();

    }


    private void initListener() {
    }

    /**
     * 关注
     */
    private void loadAttention() {
        isshow = !isshow;
        if (isshow) {
            toolbarSubimg.setImageResource(R.mipmap.attention_cancel);
            ToastUtils.showToast(LookShopActivity.this, "关注成功!");
        } else {
            toolbarSubimg.setImageResource(R.mipmap.attention_bg);
            ToastUtils.showToast(LookShopActivity.this, "已取消关注!");
        }
    }


    private void initViewPage() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            listFragment = LookShopFragment.newInstance(i + 1);
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
                loadAttention();
                break;
        }
    }
}
