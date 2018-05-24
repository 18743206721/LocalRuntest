package com.xingguang.localrun.maincode.mine.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseFragmentAdapter;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.maincode.mine.view.fragment.MyAllOrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 创建日期：2018/5/24
 * 描述:我的订单页
 * 作者:LiuYu
 */
public class MyOrderAllActivity extends ToolBarActivity {

    @BindView(R.id.tab_order)
    XTabLayout tabOrder;
    @BindView(R.id.vp_order)
    ViewPager vpOrder;
    @BindView(R.id.ll_parent)
    LinearLayout llParent;

    List<Fragment> mFragments;
    String[] mTitles = new String[]{"全部","待付款","待发货","已完成"};
    MyAllOrderFragment listFragment;
    String state = ""; //控制viewpager的状态

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order_all;
    }

    @Override
    protected void initView() {
        state = getIntent().getStringExtra("state");
        getToolbarTitle().setText("我的订单");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initViewPage();
        initListener();
    }

    private void initViewPage() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            listFragment = new MyAllOrderFragment(i + "");
            mFragments.add(listFragment);
        }
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        vpOrder.setAdapter(adapter);
        tabOrder.setupWithViewPager(vpOrder);
        vpOrder.setOffscreenPageLimit(0);
    }

    public void initListener(){
        if (state.equals("1")){
            vpOrder.setCurrentItem(1);
        }else if (state.equals("2")){
            vpOrder.setCurrentItem(2);
        }else if (state.equals("3")){
            vpOrder.setCurrentItem(3);
        }else {
            vpOrder.setCurrentItem(0);
        }
    }



}
