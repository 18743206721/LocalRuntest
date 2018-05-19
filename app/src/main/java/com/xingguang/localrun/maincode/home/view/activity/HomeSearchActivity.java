package com.xingguang.localrun.maincode.home.view.activity;

import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseActivity;
import com.xingguang.localrun.view.ClearEditText;

import butterknife.BindView;


/**
 * 创建日期：2018/5/19
 * 描述: 首页搜索页面
 * 作者:LiuYu
 */
public class HomeSearchActivity extends BaseActivity {

    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tab_all)
    XTabLayout tabAll;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_search;
    }

    @Override
    protected void initView() {

        initTab();

    }

    private void initTab() {
        tabAll.addTab(tabAll.newTab().setText("商品"));
        tabAll.addTab(tabAll.newTab().setText("店铺"));
        tabAll.addTab(tabAll.newTab().setText("代办"));

        tabAll.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                String text = (String) tab.getText();
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });


    }


}
