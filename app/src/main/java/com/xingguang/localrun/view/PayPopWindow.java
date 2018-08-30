package com.xingguang.localrun.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xingguang.localrun.R;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.home.view.activity.BuyActivity;
import com.xingguang.localrun.maincode.mine.view.activity.MyOrderAllActivity;
import com.xingguang.localrun.maincode.shop.model.PayBean;
import com.xingguang.localrun.maincode.shop.model.WeixinBean;
import com.xingguang.localrun.utils.AppUtil;

import java.util.List;

/**
 * 创建日期：2018/6/30
 * 描述:
 * 作者:LiuYu
 */
public class PayPopWindow extends PopupWindow implements View.OnClickListener {

    private final int type;
    //支付宝，微信
    ImageView alipay, wxpay;
    //总价，提交
    TextView tvTotal, commit;
    RelativeLayout alipayLv, wxpayLv;

    /**
     * 支付支付渠道
     */
    private static final String CHANNEL_ALIPAY = "alipay";
    /**
     * 微信支付渠道
     */
    private static final String CHANNEL_WECHAT = "weixin";

    private String channel = CHANNEL_ALIPAY;

    private Context context;
    double totalprice; //总价格
    private String adsId;//地址id
    private String content = "";//用户留言
    private String ordersn;

    //微信支付
    private IWXAPI iwapi;
    PayReq request = new PayReq();

    public PayPopWindow(Context context, View parent, double totalprice, String adsId, String content,String ordersn,int type) {
        this.context = context;
        this.totalprice = totalprice;
        this.adsId = adsId;
        this.content = content;
        this.ordersn = ordersn;
        this.type = type;

        View view = View.inflate(context, R.layout.pay_popwindow, null);
        view.startAnimation(AnimationUtils.loadAnimation(context,
                R.anim.pop_enter_anim));
        tvTotal = view.findViewById(R.id.tv_total);
        alipay = view.findViewById(R.id.alipay_click);
        wxpay = view.findViewById(R.id.wxpay_click);
        commit = view.findViewById(R.id.commit);
        //支付宝
        alipayLv = view.findViewById(R.id.alipay_lv);
        alipayLv.setOnClickListener(this);
        //微信
        wxpayLv = view.findViewById(R.id.wxpay_lv);
        wxpayLv.setOnClickListener(this);

        //确认购买
        commit.setOnClickListener(this);

        iwapi = WXAPIFactory.createWXAPI(context, null);
        iwapi.registerApp("xxx");

        tvTotal.setText("¥" + totalprice);

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        // setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.CENTER, 0, 0);
        update();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit: //确认付款

//                    Message msg1 = activity.handler.obtainMessage();
//                    msg1.what = 1;
//                    msg1.obj = nums + " " + itemid + " ";
//                BuyActivity.instance.handler.sendMessage(msg1);
                if (type == 1) {
                    BuyActivity activity = (BuyActivity) context;
                    if (channel.equals(CHANNEL_WECHAT)) {
                        wxPay(activity);
                    } else {
                        zhifubao(activity);
                    }
                } else if (type == 2){
                    MyOrderAllActivity orderAllActivity = (MyOrderAllActivity) context;
                    if (channel.equals(CHANNEL_WECHAT)) {
                        wxPayorder(orderAllActivity);
                    } else {
                        zhifubaoorder(orderAllActivity);
                    }

                }

                break;
            case R.id.wxpay_lv: //微信
                channel = CHANNEL_WECHAT;
                setClick(2);
                break;
            case R.id.alipay_lv: //支付宝
                channel = CHANNEL_ALIPAY;
                setClick(1);
                break;
        }

    }

    private void zhifubaoorder(final MyOrderAllActivity orderAllActivity) {
        if (hasApplication()) {
            OkGo.<String>post(HttpManager.topay)
                    .tag(this)
                    .cacheKey("cachePostKey")
                    .cacheMode(CacheMode.DEFAULT)
                    .params("token", AppUtil.getUserId(context))
                    .params("order_sn", ordersn)
                    .params("pay_code", channel)
                    .execute(new DialogCallback<String>(orderAllActivity) {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Gson gson = new Gson();
                            final PayBean bean = gson.fromJson(response.body().toString(), PayBean.class);
                            dismiss();

                            Runnable payRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    //调用支付宝
                                    PayTask payTask = new PayTask(orderAllActivity);
                                    String result = payTask.pay(bean.getData().getParam(), true);
                                    Message msg = orderAllActivity.handler.obtainMessage();
                                    msg.what = 1;
                                    msg.obj = result + " ";
                                    orderAllActivity.handler.sendMessage(msg);
                                }
                            };
                            // 必须异步调用
                            Thread payThread = new Thread(payRunnable);
                            payThread.start();

                        }
                    });

        } else {
            // 处理没有安装支付宝的情况
            new AlertDialog.Builder(orderAllActivity)
                    .setMessage("是否下载并安装支付宝完成认证?")
                    .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent action = new Intent(Intent.ACTION_VIEW);
                            action.setData(Uri.parse("https://m.alipay.com"));
                            orderAllActivity.startActivity(action);
                        }
                    }).setNegativeButton("算了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    }

    private void wxPayorder(MyOrderAllActivity orderAllActivity) {
        final IWXAPI mWxApi = WXAPIFactory.createWXAPI(context, HttpManager.WX_APP_ID, true);
        // 将该app注册到微信
        mWxApi.registerApp(HttpManager.WX_APP_ID);
        // 判断是否安装客户端
//        if (!mWxApi.isWXAppInstalled() && !mWxApi.isWXAppSupportAPI()) {
//            ToastUtils.showToast(context, "请您先安装微信客户端！");
//            return;
//        } else {

            OkGo.<String>post(HttpManager.topay)
                    .tag(this)
                    .cacheKey("cachePostKey")
                    .cacheMode(CacheMode.DEFAULT)
                    .params("token", AppUtil.getUserId(context))
                    .params("order_sn", ordersn)
                    .params("pay_code", channel)
                    .execute(new DialogCallback<String>(orderAllActivity) {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Gson gson = new Gson();
                            WeixinBean bean = gson.fromJson(response.body().toString(), WeixinBean.class);
                            dismiss();
                            if (mWxApi != null) {
                                PayReq req = new PayReq();
                                req.appId = HttpManager.WX_APP_ID;// 微信开放平台审核通过的应用APPID
                                req.partnerId = bean.getData().getPartnerid();// partnerid微信支付分配的商户号
                                req.prepayId = bean.getData().getPrepayid();//  prepayid预支付订单号，app服务器调用“统一下单”接口获取
                                req.nonceStr = bean.getData().getNoncestr();// noncestr随机字符串，不长于32位
                                req.timeStamp = bean.getData().getTimestamp()+"";// timestamp时间戳
                                req.packageValue = bean.getData().getPackageX();// package固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
                                req.sign = bean.getData().getSign();// sign签名，
                                // 调用微信SDK，发起支付，回调WxPayEntryActivity
                                mWxApi.sendReq(req);
                            }

                        }
                    });
//        }

    }


    private void zhifubao(final BuyActivity activity) {

            if (hasApplication()) {
                OkGo.<String>post(HttpManager.cart4)
                        .tag(this)
                        .cacheKey("cachePostKey")
                        .cacheMode(CacheMode.DEFAULT)
                        .params("token", AppUtil.getUserId(context))
                        .params("address_id", adsId)
                        .params("user_note", content)
                        .params("pay_code", channel)
                        .execute(new DialogCallback<String>(activity) {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Gson gson = new Gson();
                                final PayBean bean = gson.fromJson(response.body().toString(), PayBean.class);
                                dismiss();

                                Runnable payRunnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        //调用支付宝
                                        PayTask payTask = new PayTask(activity);
                                        String result = payTask.pay(bean.getData().getParam(), true);
                                        Message msg = activity.handler.obtainMessage();
                                        msg.what = 1;
                                        msg.obj = result + " ";
                                        activity.handler.sendMessage(msg);
                                    }
                                };
                                // 必须异步调用
                                Thread payThread = new Thread(payRunnable);
                                payThread.start();

                            }
                        });

            } else {
                // 处理没有安装支付宝的情况
                new AlertDialog.Builder(activity)
                        .setMessage("是否下载并安装支付宝完成认证?")
                        .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent action = new Intent(Intent.ACTION_VIEW);
                                action.setData(Uri.parse("https://m.alipay.com"));
                                activity.startActivity(action);
                            }
                        }).setNegativeButton("算了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
    }


    private void setClick(int count) {
        alipay.setImageResource(R.drawable.nochoice_btn_gray);
        wxpay.setImageResource(R.drawable.nochoice_btn_gray);
        if (count == 1) {
            alipay.setImageResource(R.drawable.choice_btn_red);
        } else {
            wxpay.setImageResource(R.drawable.choice_btn_red);
        }
    }


    // 微信支付点击调用此方法，同支付宝支付，和后台协商好传递的参数，这一步就是请求后台的接口，拼接订单信息，生成一个加密的预支付订单，主要工作都在后台
    private void wxPay(BuyActivity activity) {
        final IWXAPI mWxApi = WXAPIFactory.createWXAPI(context, HttpManager.WX_APP_ID, true);
        // 将该app注册到微信
        mWxApi.registerApp(HttpManager.WX_APP_ID);
        // 判断是否安装客户端

            OkGo.<String>post(HttpManager.cart4)
                    .tag(this)
                    .cacheKey("cachePostKey")
                    .cacheMode(CacheMode.DEFAULT)
                    .params("token", AppUtil.getUserId(context))
                    .params("address_id", adsId)
                    .params("user_note", content)
                    .params("pay_code", channel)
                    .execute(new DialogCallback<String>(activity) {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Gson gson = new Gson();
                            WeixinBean bean = gson.fromJson(response.body().toString(), WeixinBean.class);
                            dismiss();

                            if (mWxApi != null) {
                                PayReq req = new PayReq();
                                req.appId = HttpManager.WX_APP_ID;// 微信开放平台审核通过的应用APPID
                                    req.partnerId = bean.getData().getPartnerid();// partnerid微信支付分配的商户号
                                    req.prepayId = bean.getData().getPrepayid();//  prepayid预支付订单号，app服务器调用“统一下单”接口获取
                                    req.nonceStr = bean.getData().getNoncestr();// noncestr随机字符串，不长于32位
                                    req.timeStamp = bean.getData().getTimestamp()+"";// timestamp时间戳
                                    req.packageValue = bean.getData().getPackageX();// package固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
                                    req.sign = bean.getData().getSign();// sign签名，
                                // 调用微信SDK，发起支付，回调WxPayEntryActivity
                                mWxApi.sendReq(req);
                            }

                        }
                    });
    }

    /**
     * 判断是否安装了支付宝
     *
     * @return true 为已经安装
     */
    private boolean hasApplication() {
        PackageManager manager = context.getPackageManager();
        Intent action = new Intent(Intent.ACTION_VIEW);
        action.setData(Uri.parse("alipays://"));
        List list = manager.queryIntentActivities(action, PackageManager.GET_RESOLVED_FILTER);
        return list != null && list.size() > 0;
    }


}
