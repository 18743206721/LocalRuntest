package com.xingguang.localrun.maincode.mine.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.SharedPreferencesUtils;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.http.CommonBean;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.login.view.activity.LoginActivity;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.CountDownTimerUtil;
import com.xingguang.localrun.view.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 创建日期：2018/5/21
 * 描述:修改设置界面的名称
 * 作者:LiuYu
 */
public class ModificationActivity extends ToolBarActivity implements CountDownTimerUtil.CountDownTimerListener {

    @BindView(R.id.et_modif_bianliang)
    ClearEditText etModifBianliang;
    @BindView(R.id.tv_zhuxiao)
    TextView tvZhuxiao;
    @BindView(R.id.register_mss)
    ClearEditText registerMss;
    @BindView(R.id.tv_regis_getmss)
    TextView tvRegisGetmss;
    @BindView(R.id.rl_get_messs)
    RelativeLayout rlGetMesss;
    @BindView(R.id.ll_sms)
    LinearLayout llSms;

    String type = "";
    private static final String mobile = "^1\\d{10}$";
    private CountDownTimerUtil util;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    util.restart();
                    break;
            }
        }
    };
    private Intent mIntent;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_modification;
    }

    @Override
    protected void initView() {

        type = getIntent().getStringExtra("type");
        if (type.equals("1")) { //修改会员名字
            getToolbarTitle().setText(getIntent().getStringExtra("huiyuan"));
            etModifBianliang.setText(AppUtil.getUserName(this));
            llSms.setVisibility(View.GONE);
        } else { //修改电话号
            getToolbarTitle().setText(getIntent().getStringExtra("phone"));
            etModifBianliang.setText(AppUtil.getUserPhone(this));
            llSms.setVisibility(View.VISIBLE);
        }
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        util = new CountDownTimerUtil(this, this);

    }

    @OnClick({R.id.tv_zhuxiao, R.id.rl_get_messs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_zhuxiao:
                if (type.equals("1")) {
                    loadname();
                } else {
                    loadphone();
                }
                break;
            case R.id.rl_get_messs:
                if (AppUtil.isFastDoubleClick(1000)) {
                    return;
                }
                if (validates()) {
                    sendSMSClient();
                }
                break;

        }
    }

    /**
     * 发送验证码
     */
    private void sendSMSClient() {
        OkGo.<String>post(HttpManager.sendSms)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("mobile", etModifBianliang.getText().toString())
                .params("type", "2")
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        tvRegisGetmss.setTextColor(Color.rgb(81, 87, 104));
                        rlGetMesss.setBackgroundResource(R.drawable.corners5_solidblack);
                        Message msgs = mHandler.obtainMessage();
                        msgs.what = 1;
                        msgs.sendToTarget();
                        tvRegisGetmss.setEnabled(false);
                        rlGetMesss.setEnabled(false);
                    }
                });
    }

    //提交电话号
    private void loadphone() {
        OkGo.<String>post(HttpManager.update_mobile)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("mobile", etModifBianliang.getText().toString())
                .params("code", registerMss.getText().toString())
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean commonBean = gson.fromJson(response.body().toString(), CommonBean.class);
//                        mIntent = new Intent();
//                        Bundle b = new Bundle();
//                        b.putSerializable("content", etModifBianliang.getText().toString());
//                        mIntent.putExtras(b);
                        // 设置结果，并进行传送
//                        SharedPreferencesUtils.remove(ModificationActivity.this,SharedPreferencesUtils.PHONE);
//                        SharedPreferencesUtils.put(ModificationActivity.this, SharedPreferencesUtils.PHONE, etModifBianliang.getText().toString());
//                        setResult(201, mIntent);
                        SharedPreferencesUtils.clear(ModificationActivity.this);
                        ToastUtils.showToast(ModificationActivity.this, commonBean.getMsg());
                        finish();
                        MineSettingActivity.instance.finish();
                        startActivity(new Intent(ModificationActivity.this, LoginActivity.class));


                    }
                });
    }

    //提交名字
    private void loadname() {
        OkGo.<String>post(HttpManager.update_nickname)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("token", AppUtil.getUserId(this))
                .params("nickname", etModifBianliang.getText().toString())
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CommonBean commonBean = gson.fromJson(response.body().toString(), CommonBean.class);
                        mIntent = new Intent();
                        Bundle b = new Bundle();
                        b.putSerializable("content", etModifBianliang.getText().toString());
                        mIntent.putExtras(b);
                        // 设置结果，并进行传送
                        SharedPreferencesUtils.put(ModificationActivity.this, SharedPreferencesUtils.USERNAME, etModifBianliang.getText().toString());
                        setResult(200, mIntent);
                        ToastUtils.showToast(ModificationActivity.this, commonBean.getMsg());
                        finish();
                    }
                });
    }

    /**
     * 获取验证码时校验手机
     */
    private boolean validates() {
        if (etModifBianliang.getText().toString().length() == 0) {
            Toast.makeText(this, "请填写手机号!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etModifBianliang.getText().toString().length() != 11) {
            Toast.makeText(this, "请填写11位手机号!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!etModifBianliang.getText().toString().matches(mobile)) {
            Toast.makeText(this, "电话格式不对！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void countDownTimerListener(String time) {
        tvRegisGetmss.setText(time);
    }

    @Override
    public void countDownTimerFinish() {
        rlGetMesss.setEnabled(true);
        tvRegisGetmss.setEnabled(true);
        tvRegisGetmss.setTextColor(Color.parseColor("#2dbe50"));
        rlGetMesss.setBackgroundResource(R.drawable.btn_register_bg);
    }


}
