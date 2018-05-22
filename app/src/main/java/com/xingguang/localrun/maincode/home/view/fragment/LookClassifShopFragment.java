package com.xingguang.localrun.maincode.home.view.fragment;

import android.annotation.SuppressLint;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseFragment;

/**
 * 创建日期：2018/5/18
 * 描述:分类商店列表
 * 作者:LiuYu
 */
@SuppressLint("ValidFragment")
public class LookClassifShopFragment extends BaseFragment{

    String type;


    public LookClassifShopFragment(String type) {
        this.type = type;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lookshop;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void lazyLoad() {

    }


}
