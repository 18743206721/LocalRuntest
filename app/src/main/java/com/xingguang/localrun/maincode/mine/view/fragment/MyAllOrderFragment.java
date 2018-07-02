package com.xingguang.localrun.maincode.mine.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.base.BaseFragment;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.http.CommonBean;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.mine.model.OrderBean;
import com.xingguang.localrun.maincode.mine.view.adapter.MyAllOrderAdapter;
import com.xingguang.localrun.popwindow.TextPopUpWindow;
import com.xingguang.localrun.refresh.RefreshUtil;
import com.xingguang.localrun.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建日期：2018/5/24
 * 描述:
 * 作者:LiuYu
 */
@SuppressLint("ValidFragment")
public class MyAllOrderFragment extends BaseFragment implements RefreshUtil.OnRefreshListener {

    @BindView(R.id.rv_myplay_apply)
    RecyclerView rvMyplayApply;
    @BindView(R.id.my_deco_fg_apply)
    RelativeLayout myDecoFgApply;
    @BindView(R.id.empty)
    ImageView empty;
    @BindView(R.id.ll_myplay_frg)
    RelativeLayout llMyplayFrg;
    @BindView(R.id.tw_refresh)
    TwinklingRefreshLayout tw_refresh;

    String type;
    MyAllOrderAdapter adapter;
    private List<OrderBean.DataBean.ListBean> mDatas = new ArrayList<>();
    private boolean isRefresh = false;

    private TextPopUpWindow popcancel;
    private TextPopUpWindow popdelted;
    private View.OnClickListener nocancel;
    private View.OnClickListener yescancel;
    private View.OnClickListener nodeleted;
    private View.OnClickListener yesdeleted;
    int currentPositon = 0; //当前的position
    private Intent intent;
    private int page = 1;

    public MyAllOrderFragment(String type) {
        this.type = type;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_allorder;
    }

    @Override
    protected void initView() {
        tw_refresh.setHeaderView(new SinaRefreshView(getActivity()));
        tw_refresh.setBottomView(new LoadingView(getActivity()));
        tw_refresh.setOnRefreshListener(new RefreshUtil(this).refreshListenerAdapter());

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
                popcancel = new TextPopUpWindow(getActivity(), llMyplayFrg, "确定取消订单?", "取消", "确定", nocancel, yescancel);
            }
        });
        nocancel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popcancel.dismiss();
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
                popdelted = new TextPopUpWindow(getActivity(), llMyplayFrg, "确定删除订单?", "取消", "确定", nodeleted, yesdeleted);
            }
        });
        nodeleted = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popdelted.dismiss();
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
            public void OnOrderpay(TextView item_orderpay, int position) {
                ToastUtils.showToast(getActivity(), "支付");
            }
        });

        //确认收货
        adapter.setmOnOrdertrue(new MyAllOrderAdapter.OnOrderTrue() {
            @Override
            public void OnOrdertrue(TextView item_ordertrue, int position) {
                loadtrue(position);
            }
        });
    }

    /**
     * 确认收货
     */
    private void loadtrue(final int position) {
        OkGo.<String>post(HttpManager.trueOrder)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(getActivity()))
                .params("order_id", mDatas.get(position).getOrder_id())
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                        mDatas.remove(position);
                        adapter.notifyDataSetChanged();
                        ToastUtils.showToast(getActivity(), bean.getMsg());
                        if (mDatas.size() == 0) {
                            myDecoFgApply.setVisibility(View.GONE);
                            empty.setVisibility(View.VISIBLE);
                        } else {
                            empty.setVisibility(View.GONE);
                            myDecoFgApply.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    //删除订单的接口
    private void loadDeleted(final int currentPositon) {
        OkGo.<String>post(HttpManager.delOrder)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(getActivity()))
                .params("order_id", mDatas.get(currentPositon).getOrder_id())
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                        popdelted.dismiss();
//                            adapter.setList(mDatas);
                        mDatas.remove(currentPositon);
                        adapter.notifyDataSetChanged();
                        ToastUtils.showToast(getActivity(), bean.getMsg());
                        if (mDatas.size() == 0) {
                            myDecoFgApply.setVisibility(View.GONE);
                            empty.setVisibility(View.VISIBLE);
                        } else {
                            empty.setVisibility(View.GONE);
                            myDecoFgApply.setVisibility(View.VISIBLE);
                        }

                    }
                });
    }

    //取消订单接口
    private void loadCancel(final int currentPositon) {
        OkGo.<String>post(HttpManager.cancelOrder)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(getActivity()))
                .params("order_id", mDatas.get(currentPositon).getOrder_id())
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean bean = gson.fromJson(response.body().toString(), CommonBean.class);
                        popcancel.dismiss();
//                            adapter.setList(mDatas);
                        mDatas.remove(currentPositon);
                        adapter.notifyDataSetChanged();
                        ToastUtils.showToast(getActivity(), bean.getMsg());
                        if (mDatas.size() == 0) {
                            myDecoFgApply.setVisibility(View.GONE);
                            empty.setVisibility(View.VISIBLE);
                        } else {
                            empty.setVisibility(View.GONE);
                            myDecoFgApply.setVisibility(View.VISIBLE);
                        }

                    }
                });
    }

    @Override
    protected void lazyLoad() {
        load(1);
    }

    private void load(final int page) {
        OkGo.<String>post(HttpManager.Userorder)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(getActivity()))
                .params("type", type)
                .params("page", page)
                .execute(new DialogCallback<String>(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        OrderBean bean = gson.fromJson(response.body().toString(), OrderBean.class);
                        if (bean.getData() != null) {
                            if (bean.getData().getList().size() == 0 && page != 1) {
                                Toast.makeText(getActivity(),
                                        "只有这么多了~",
                                        Toast.LENGTH_SHORT).show();
                            }
                            if (page == 1) {
                                mDatas.clear();
                            }
                            mDatas.addAll(bean.getData().getList());
                            adapter.setList(mDatas);
                        } else {
                            ToastUtils.showToast(getActivity(), bean.getMsg());
                        }

                        if (mDatas.size() == 0) {
                            myDecoFgApply.setVisibility(View.GONE);
                            empty.setVisibility(View.VISIBLE);
                        } else {
                            empty.setVisibility(View.GONE);
                            myDecoFgApply.setVisibility(View.VISIBLE);
                        }

                        if (isRefresh) {
                            tw_refresh.finishRefreshing();
                        } else {
                            tw_refresh.finishLoadmore();
                        }

                    }
                });
    }


    @Override
    public void onRefresh() {
        isRefresh = true;
        load(page);
    }

    @Override
    public void onLoad() {
        isRefresh = false;
        load(page++);
    }


}
