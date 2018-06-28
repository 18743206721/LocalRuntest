package com.xingguang.localrun.maincode.home.view.activity;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.model.CartBean;
import com.xingguang.localrun.maincode.home.view.adapter.BuyCartAdapter;
import com.xingguang.localrun.maincode.mine.view.activity.AddressManagementActivity;
import com.xingguang.localrun.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 立即购买页面
 */
public class BuyActivity extends ToolBarActivity {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.ll_ads)
    LinearLayout llAds;
    @BindView(R.id.main_home_lv)
    RecyclerView recyc;
    @BindView(R.id.tv_peisong)
    TextView tvPeisong;
    @BindView(R.id.tv_online)
    TextView tvOnline;
    @BindView(R.id.ed_content)
    EditText edContent;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv_allprice)
    TextView tvAllprice;
    @BindView(R.id.tv_hejiprice)
    TextView tvHejiprice;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    BuyCartAdapter adapter;
    List<CartBean.DataBean.CartlistBean> mDatas = new ArrayList<>();
    public static BuyActivity instance;
    //id,姓名，电话，详细地址
    private String addressId,nameStr,phoneStr,xiangxiads;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy;
    }

    @Override
    protected void initView() {
        instance = this;
        setToolBarTitle("确认订单");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapter = new BuyCartAdapter(BuyActivity.this, mDatas);
        LinearLayoutManager manager = new LinearLayoutManager(BuyActivity.this);
        recyc.setLayoutManager(manager);
        recyc.setAdapter(adapter);
        recyc.setNestedScrollingEnabled(false);
        recyc.setFocusable(false);

        load();
        initListener();

    }

    private void load() {
        OkGo.<String>post(HttpManager.Cart)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(BuyActivity.this))
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CartBean bean = gson.fromJson(response.body().toString(), CartBean.class);
                        ToastUtils.showToast(BuyActivity.this, bean.getMsg());
                        mDatas.addAll(bean.getData().getCartlist());
                        adapter.setList(mDatas);
                        tvPeisong.setText("¥"+bean.getData().getShipping_price());//配送金额
                        tvAllprice.setText("¥"+bean.getData().getTotal_amount());//总金额
                    }
                });

    }

    private void initListener() {
//        Intent intent = new Intent(BuyActivity.this,ProductdetailsActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        startActivity(intent);
    }

    @OnClick({R.id.ll_ads, R.id.tv_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_ads: //选择地址
                Intent intent = new Intent(BuyActivity.this, AddressManagementActivity.class);
                intent.putExtra("buytype",1);
                startActivityForResult(intent, 50);
                break;
            case R.id.tv_order://立即购买

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
//        if (requestCode == REQUEST_CODE_PAYMENT) {
//            if (resultCode == NowPayActivity.RESULT_OK) {
//                String result = data.getExtras().getString("pay_result");
//                if (result.equals("success")) {
//                    Toast.makeText(PurchaseDetailPayActivity.this, "交易成功",
//                            Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(PurchaseDetailPayActivity.this, PurchasePaySuccessActivity.class);
//                    intent.putExtra("jifenNum", jifenNum);
//                    startActivity(intent);
//                    finish();
//                } else if (result.equals("fail")) {
//                    Toast.makeText(PurchaseDetailPayActivity.this, "交易失败", Toast.LENGTH_SHORT)
//                            .show();
//                } else if (result.equals("cancel")) {
//                    Toast.makeText(PurchaseDetailPayActivity.this, "用户取消交易",
//                            Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(PurchaseDetailPayActivity.this, "支付插件没有安装",
//                            Toast.LENGTH_SHORT).show();
//                }
//                // 处理返回值
//                // "success" - 支付成功
//                // "fail"    - 支付失败
//                // "cancel"  - 取消支付
//                // "invalid" - 支付插件未安装（一般是微信客户端未安装的情况）
//                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
//                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
////                showMsg(result, errorMsg, extraMsg);
//            }
//        }
        if (requestCode == 50) {
            if (resultCode == 50) {
                addressId = data.getStringExtra("id");
//                address.setText("收货地址：" + data.getStringExtra("address"));
                nameStr = data.getStringExtra("name");
                phoneStr = data.getStringExtra("phone");
                xiangxiads = data.getStringExtra("xiangxiads");

                name.setText(nameStr);
                phone.setText(phoneStr);
                address.setText(""+xiangxiads);

//                namePhone.setText("收货人：" + nameStr + "    " + phoneStr);
            }
        }
    }


}
