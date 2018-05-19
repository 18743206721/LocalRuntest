package com.xingguang.localrun.maincode.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseFragment;
import com.xingguang.localrun.login.view.activity.LoginActivity;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.view.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MineFragment extends BaseFragment {


    @BindView(R.id.AppFragment_Toolbar)
    Toolbar mToolbar;
    @BindView(R.id.AppFragment_AppBarLayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.my_user_img)
    RoundImageView myUserImg;
    @BindView(R.id.my_user_name)
    TextView myUserName;
    @BindView(R.id.rl_my_header)
    RelativeLayout rlMyHeader;
    @BindView(R.id.AppFragment_CollapsingToolbarLayout)
    CollapsingToolbarLayout AppFragmentCollapsingToolbarLayout;
    @BindView(R.id.tv_my_colcount)
    TextView tvMyColcount;
    @BindView(R.id.ll_my_collection)
    LinearLayout llMyCollection;
    @BindView(R.id.tv_my_colshopcount)
    TextView tvMyColshopcount;
    @BindView(R.id.ll_my_attention)
    LinearLayout llMyAttention;
    @BindView(R.id.tv_zujicount)
    TextView tvZujicount;
    @BindView(R.id.ll_my_zuji)
    LinearLayout llMyZuji;
    @BindView(R.id.linearlayout2)
    LinearLayout linearlayout2;
    @BindView(R.id.ll_my_allorder)
    LinearLayout llMyAllorder;
    @BindView(R.id.ll_daipay)
    LinearLayout llDaipay;
    @BindView(R.id.ll_mydai_fahuo)
    LinearLayout llMydaiFahuo;
    @BindView(R.id.ll_complete)
    LinearLayout llComplete;
    @BindView(R.id.ll_getads)
    LinearLayout llGetads;
    @BindView(R.id.ll_about)
    LinearLayout llAbout;
    @BindView(R.id.btn_shenqing)
    Button btnShenqing;
    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.ll_readily_parent)
    LinearLayout llReadilyParent;
    Unbinder unbinder;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

        //設置Toolbar透明
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //設置appbar的滑動颜色渐变效果
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                mToolbar.setBackgroundColor(AppUtil.changeAlpha(getResources().getColor(R.color.colorPrimary), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));
            }
        });

    }

    @Override
    protected void lazyLoad() {
        //写网络请求

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_my_header, R.id.ll_my_collection, R.id.ll_my_attention, R.id.ll_my_zuji, R.id.ll_my_allorder, R.id.ll_daipay, R.id.ll_mydai_fahuo, R.id.ll_complete, R.id.ll_getads, R.id.ll_about, R.id.btn_shenqing, R.id.bt_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_my_header://登录头布局
                startActivity(new Intent(getActivity(),LoginActivity.class));
                break;
            case R.id.ll_my_collection://我的收藏
                break;
            case R.id.ll_my_attention://我的关注
                break;
            case R.id.ll_my_zuji://我的足迹
                break;
            case R.id.ll_my_allorder://我的全部订单
                break;
            case R.id.ll_daipay://待支付
                break;
            case R.id.ll_mydai_fahuo://待付款
                break;
            case R.id.ll_complete://已完成
                break;
            case R.id.ll_getads://收货地址
                break;
            case R.id.ll_about://关于
                break;
            case R.id.btn_shenqing://申请入驻
                break;
            case R.id.bt_back://返回
                break;
        }
    }
}
