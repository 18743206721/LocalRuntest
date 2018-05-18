package com.xingguang.localrun.maincode.home.view.fragment;

import android.annotation.SuppressLint;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseFragment;

/**
 * 创建日期：2018/5/18
 * 描述:查看商铺
 * 作者:LiuYu
 */
@SuppressLint("ValidFragment")
public class LookShopFragment extends BaseFragment{

    String type;


    public LookShopFragment(String type) {
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
