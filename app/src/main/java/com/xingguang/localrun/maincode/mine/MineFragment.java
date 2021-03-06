package com.xingguang.localrun.maincode.mine;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.base.BaseFragment;
import com.xingguang.core.utils.SharedPreferencesUtils;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.login.view.activity.LoginActivity;
import com.xingguang.localrun.maincode.mine.model.ProfileBean;
import com.xingguang.localrun.maincode.mine.view.activity.AboutActivity;
import com.xingguang.localrun.maincode.mine.view.activity.AddressManagementActivity;
import com.xingguang.localrun.maincode.mine.view.activity.FootPrintActivity;
import com.xingguang.localrun.maincode.mine.view.activity.MineApplyEnterActivity;
import com.xingguang.localrun.maincode.mine.view.activity.MineSettingActivity;
import com.xingguang.localrun.maincode.mine.view.activity.MyCollectionActivity;
import com.xingguang.localrun.maincode.mine.view.activity.MyGuanzhuActivity;
import com.xingguang.localrun.maincode.mine.view.activity.MyOrderAllActivity;
import com.xingguang.localrun.popwindow.TextPopUpWindow;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.RoundImageView;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment {

    @BindView(R.id.my_user_img)
    RoundImageView myUserImg;
    @BindView(R.id.my_user_name)
    TextView myUserName;
    @BindView(R.id.rl_my_header)
    RelativeLayout rlMyHeader;
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
    @BindView(R.id.AppFragment_Toolbar)
    Toolbar mToolbar;
    @BindView(R.id.AppFragment_CollapsingToolbarLayout)
    CollapsingToolbarLayout AppFragmentCollapsingToolbarLayout;
    @BindView(R.id.AppFragment_AppBarLayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.my_tv_setting)
    TextView my_tv_setting;
    private TextPopUpWindow pop;
    private View.OnClickListener no;
    private View.OnClickListener yes;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

        //設置appbar的滑動颜色渐变效果
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                mToolbar.setBackgroundColor(AppUtil.changeAlpha(getResources().getColor(R.color.home_read), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));
            }
        });

//        EventBus.getDefault().register(this);
        initListener();

    }

    private void initListener() {
        no = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        };
        yes = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadbacklogin();
            }
        };
    }

    /**
     * 退出登录
     */
    private void loadbacklogin() {
        OkGo.<String>post(HttpManager.logout)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(getActivity()))
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        SharedPreferencesUtils.clear(getActivity());
                        pop.dismiss();
                        ToastUtils.showToast(getActivity(), "已退出登录");
                        myUserName.setText("未登录");
                        ImageLoader.loadCircleImage(getActivity(), R.mipmap.defaultavatar123, myUserImg);
                        btBack.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    protected void lazyLoad() {}


    @Override
    public void onResume() {
        super.onResume();
        if (!AppUtil.getUserId(getActivity()).equals("")) {
            btBack.setVisibility(View.VISIBLE);
            AppUtil.getUserImage(getActivity());
            myUserName.setText(AppUtil.getUserName(getActivity()));
            ImageLoader.loadCircleImage(getActivity(), AppUtil.getUserImage(getActivity()), myUserImg);

            loadcount();

        } else {
            myUserName.setText("未登录");
            ImageLoader.loadCircleImage(getActivity(), R.mipmap.defaultavatar123, myUserImg);
            btBack.setVisibility(View.GONE);
            tvMyColcount.setText("0");
            tvMyColshopcount.setText("0");
            tvZujicount.setText("0");
        }


    }

    private void loadcount() {
        OkGo.<String>post(HttpManager.profile)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(getActivity()))
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //设置关注数，收藏数，足迹数
                        Gson gson = new Gson();
                        ProfileBean bean = gson.fromJson(response.body().toString(), ProfileBean.class);
                        if (bean.getData()!=null){
                            tvMyColcount.setText(bean.getData().getGoods_collect());
                            tvMyColshopcount.setText(bean.getData().getShop_collect());
                            tvZujicount.setText(bean.getData().getGoods_visit());
                        }

                    }
                });
    }

    @OnClick({R.id.rl_my_header, R.id.ll_my_collection, R.id.ll_my_attention, R.id.ll_my_zuji, R.id.ll_my_allorder, R.id.ll_daipay, R.id.ll_mydai_fahuo,
            R.id.ll_complete, R.id.ll_getads, R.id.ll_about,
            R.id.btn_shenqing, R.id.bt_back, R.id.my_tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_my_header://登录头布局
                if (AppUtil.getUserId(getActivity()).equals("")) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_my_collection://我的收藏
                if (AppUtil.isExamined(getActivity())) {
                    startActivity(new Intent(getActivity(), MyCollectionActivity.class));
                }
                break;
            case R.id.ll_my_attention://我的关注
                if (AppUtil.isExamined(getActivity())) {
                    startActivity(new Intent(getActivity(), MyGuanzhuActivity.class));
                }
                break;
            case R.id.ll_my_zuji://我的足迹
                if (AppUtil.isExamined(getActivity())) {
                    startActivity(new Intent(getActivity(), FootPrintActivity.class));
                }
                break;
            case R.id.ll_my_allorder://我的全部订单
                if (AppUtil.isExamined(getActivity())) {
                    startActivity(new Intent(getActivity(), MyOrderAllActivity.class)
                            .putExtra("state", "0"));
                }
                break;
            case R.id.ll_daipay://待支付
                if (AppUtil.isExamined(getActivity())) {
                    startActivity(new Intent(getActivity(), MyOrderAllActivity.class)
                            .putExtra("state", "1"));
                }
                break;
            case R.id.ll_mydai_fahuo://待付款
                if (AppUtil.isExamined(getActivity())) {
                    startActivity(new Intent(getActivity(), MyOrderAllActivity.class)
                            .putExtra("state", "2"));
                }
                break;
            case R.id.ll_complete://已完成
                if (AppUtil.isExamined(getActivity())) {
                    startActivity(new Intent(getActivity(), MyOrderAllActivity.class)
                            .putExtra("state", "3"));
                }
                break;
            case R.id.ll_getads://收货地址
                if (AppUtil.isExamined(getActivity())) {
                    startActivity(new Intent(getActivity(), AddressManagementActivity.class));
                }
                break;
            case R.id.ll_about://关于
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.btn_shenqing://申请入驻
                if (AppUtil.isExamined(getActivity())) {
                    startActivity(new Intent(getActivity(), MineApplyEnterActivity.class));
                }
                break;
            case R.id.bt_back://返回
                if (!AppUtil.getUserId(getActivity()).equals("")) {
                    pop = new TextPopUpWindow(getActivity(), llReadilyParent, "确定退出登录?", "取消", "确定", no, yes);
                }
                break;
            case R.id.my_tv_setting://设置
                if (AppUtil.isExamined(getActivity())) {
                    startActivity(new Intent(getActivity(), MineSettingActivity.class));
                }
                break;
        }
    }



}
