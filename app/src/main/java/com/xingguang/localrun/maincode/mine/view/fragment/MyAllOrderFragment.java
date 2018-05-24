package com.xingguang.localrun.maincode.mine.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.BaseFragment;
import com.xingguang.localrun.maincode.mine.view.activity.PingLunActivity;
import com.xingguang.localrun.maincode.mine.view.adapter.MyAllOrderAdapter;
import com.xingguang.localrun.popwindow.TextPopUpWindow;
import com.xingguang.localrun.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建日期：2018/5/24
 * 描述:
 * 作者:LiuYu
 */
@SuppressLint("ValidFragment")
public class MyAllOrderFragment extends BaseFragment {

    @BindView(R.id.rv_myplay_apply)
    RecyclerView rvMyplayApply;
    @BindView(R.id.my_deco_fg_apply)
    RelativeLayout myDecoFgApply;
    @BindView(R.id.empty)
    ImageView empty;
    @BindView(R.id.ll_myplay_frg)
    RelativeLayout llMyplayFrg;
    String type;

    MyAllOrderAdapter adapter;
    private List<String> mDatas = new ArrayList<>();

    private TextPopUpWindow pop;
    private View.OnClickListener nocancel;
    private View.OnClickListener yescancel;
    private View.OnClickListener nodeleted;
    private View.OnClickListener yesdeleted;
    int currentPositon = 0; //当前的position
    private Intent intent;

    public MyAllOrderFragment(String type) {
        this.type = type;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_allorder;
    }

    @Override
    protected void initView() {

        adapter = new MyAllOrderAdapter(getActivity(), mDatas, type);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvMyplayApply.setLayoutManager(layoutManager);
        rvMyplayApply.setAdapter(adapter);
        initListener();

    }

    private void initListener() {
        //取消订单
        adapter.setmOnOrdercancel(new MyAllOrderAdapter.OnOrderCancel() {
            @Override
            public void OnOrdercancel(TextView item_ordercancel, int position) {
                currentPositon = position;
                pop = new TextPopUpWindow(getActivity(), llMyplayFrg, "确定取消订单?", "取消", "确定", nocancel, yescancel);
            }
        });
        nocancel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        };
        yescancel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCancel(currentPositon);
            }
        };
        //删除
        adapter.setmOnOrderdeleted(new MyAllOrderAdapter.OnOrderDeleted() {
            @Override
            public void OnOrderdeleted(TextView item_orderdeleted, int position) {
                currentPositon = position;
                pop = new TextPopUpWindow(getActivity(), llMyplayFrg, "确定删除订单?", "取消", "确定", nodeleted, yesdeleted);
            }
        });
        nodeleted = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        };
        yesdeleted = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDeleted(currentPositon);
            }
        };

        //支付
        adapter.setmOnOrderpay(new MyAllOrderAdapter.OnOrderPay() {
            @Override
            public void OnOrderPay(TextView item_orderpay, int position) {
                ToastUtils.showToast(getActivity(),"支付");
            }
        });
        //二次支付
        adapter.setmOnOrdertwopay(new MyAllOrderAdapter.OnOrderTwoPay() {
            @Override
            public void OnOrderTwoPay(TextView item_ordertwopay, int position) {
                ToastUtils.showToast(getActivity(),"二次支付");
            }
        });
        //评论
        adapter.setmOnOrdercomment(new MyAllOrderAdapter.OnOrderComment() {
            @Override
            public void OnOrderComment(TextView item_comment, int position) {
               startActivity(new Intent(getActivity(),PingLunActivity.class));
            }
        });




    }

    //删除订单的接口
    private void loadDeleted(int currentPositon) {
        ToastUtils.showToast(getActivity(),"删除");
        pop.dismiss();
    }

    //取消订单接口
    private void loadCancel(int currentPositon) {
        ToastUtils.showToast(getActivity(),"取消订单");
        pop.dismiss();
    }

    @Override
    protected void lazyLoad() {

    }




}
