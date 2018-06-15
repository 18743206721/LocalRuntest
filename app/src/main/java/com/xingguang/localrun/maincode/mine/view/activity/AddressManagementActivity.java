package com.xingguang.localrun.maincode.mine.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.http.CommonBean;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.mine.model.AddressModel;
import com.xingguang.localrun.maincode.mine.view.adapter.AddressManagementAdapter;
import com.xingguang.localrun.refresh.RefreshUtil;
import com.xingguang.localrun.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 创建日期：2018/5/24
 * 描述:收货地址管理类
 * 作者:LiuYu
 */
public class AddressManagementActivity extends ToolBarActivity implements RefreshUtil.OnRefreshListener {

    @BindView(R.id.address_management_recycle)
    RecyclerView addressManagementRecycle;
    @BindView(R.id.bottom)
    TextView bottom;
    @BindView(R.id.main_fl)
    LinearLayout mainFl;
    @BindView(R.id.empty)
    ImageView empty;
    @BindView(R.id.tw_Refresh)
    TwinklingRefreshLayout tw_refresh;

    private List<AddressModel.DataBean> myContractList = new ArrayList<>();
    private AddressManagementAdapter mAdapter;
    int total = 0;
    private boolean isRefresh = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_management;
    }

    @Override
    protected void initView() {
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setToolBarTitle("收货地址");
        tw_refresh.setHeaderView(new SinaRefreshView(this));
        tw_refresh.setBottomView(new LoadingView(this));
        tw_refresh.setOnRefreshListener(new RefreshUtil(this).refreshListenerAdapter());

        initList();
        load(0);
    }

    @OnClick(R.id.bottom)
    public void onViewClicked() { //添加新地址
        Intent intent = new Intent(AddressManagementActivity.this, AddressDetailActivity.class);
                intent.putExtra("id", "-1");
        startActivityForResult(intent, 1);
    }

    /**
     * 初始化
     */
    public void initList() {
        mAdapter = new AddressManagementAdapter(myContractList, this);
        addressManagementRecycle.setLayoutManager(new LinearLayoutManager(this));
        addressManagementRecycle.setAdapter(mAdapter);
        addressManagementRecycle.setNestedScrollingEnabled(false);
        //设置默认
        mAdapter.setDefaultClick(new AddressManagementAdapter.DefaultListener() {
            @Override
            public void SubClick(ImageView imageView, String isdefult, int position) {
                SetDefAddress(imageView,myContractList.get(position).getAddress_id(),isdefult, position);
            }
        });
        //编辑
        mAdapter.setUpdateClick(new AddressManagementAdapter.UpdateListener() {
            @Override
            public void UpdateClick(int position) {
                Intent intent = new Intent(AddressManagementActivity.this, AddressDetailActivity.class);
                intent.putExtra("id", myContractList.get(position).getAddress_id());
                startActivityForResult(intent, 1);
            }
        });
        //删除
        mAdapter.setDeteleClick(new AddressManagementAdapter.DeteleListener() {
            @Override
            public void DeteleClick(int position) {
                DeteleAddress(myContractList.get(position).getAddress_id(), position);
            }
        });
    }

    /**
     * 设置默认地址*/
    private void SetDefAddress(final ImageView imageView, String address_id, String defult, final int position) {
        OkGo.<String>post(HttpManager.usersetAds)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("address_id",address_id)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean madbean = gson.fromJson(response.body().toString(), CommonBean.class);
                        for (int i = 0, j = myContractList.size(); i < j; i++) {
                            myContractList.get(i).setIs_default("0");
                        }
                        myContractList.get(position).setIs_default("1");
                        mAdapter.notifyDataSetChanged();
                        ToastUtils.showToast(AddressManagementActivity.this,madbean.getMsg());
                    }
                });
    }

    /**
     * 删除收货地址
     * */
    private void DeteleAddress(String address_id, final int position) {
        OkGo.<String>post(HttpManager.deletedads)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("address_id",address_id)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean madbean = gson.fromJson(response.body().toString(), CommonBean.class);
                        mAdapter.setRemove(myContractList,position);
                        ToastUtils.showToast(AddressManagementActivity.this,madbean.getMsg());
                    }
                });
    }

    /**
     *地址列表数据
     *
     * @param total*/
    private void load(final int total) {
        OkGo.<String>post(HttpManager.ListAddress)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        AddressModel madbean = gson.fromJson(response.body().toString(), AddressModel.class);
                        if (total == 0) {
                            myContractList.clear();
                        }
                        myContractList.addAll(madbean.getData());
                        if (isRefresh) {
                            tw_refresh.finishRefreshing();
                        } else {
                            tw_refresh.finishLoadmore();
                        }
                        mAdapter.setList(madbean.getData());
                    }
                });
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        load(total);
    }
    @Override
    public void onLoad() {
        isRefresh = false;
        load(myContractList.size());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        total = 0;
        isRefresh = true;
        load(0);
    }


}
