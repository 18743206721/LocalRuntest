package com.xingguang.localrun.login.view.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.login.model.RegisterBean;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.CountDownTimerUtil;
import com.xingguang.localrun.view.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 创建日期：2018/5/19
 * 描述:忘记密码
 * 作者:LiuYu
 */
public class ForgetPwdActivity extends ToolBarActivity implements CountDownTimerUtil.CountDownTimerListener{

    @BindView(R.id.forget_phone)
    ClearEditText forgetPhone;
    @BindView(R.id.forget_pwd)
    ClearEditText forgetPwd;
    @BindView(R.id.forget_twopwd)
    ClearEditText forgetTwopwd;
    @BindView(R.id.forget_mss)
    ClearEditText forgetMss;
    @BindView(R.id.tv_forget_getmss)
    TextView tvForgetGetmss;
    @BindView(R.id.rl_get_messs)
    RelativeLayout rlGetMesss;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

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


    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void initView() {
        setToolBarTitle("忘记密码");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        util = new CountDownTimerUtil(this, this);

    }

    @OnClick({R.id.rl_get_messs, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_get_messs://验证码
                if (AppUtil.isFastDoubleClick(1000)) {
                    return;
                }
                if (validates()) {
                    sendSMSClient();
                }
                break;
            case R.id.tv_commit://提交
                if (AppUtil.isFastDoubleClick(1000)) {
                    return;
                }
                if (validate()) {
                    userforgetClient();
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
                .params("mobile", forgetPhone.getText().toString())
                .params("type", "3")
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        tvForgetGetmss.setTextColor(Color.rgb(81, 87, 104));
                        rlGetMesss.setBackgroundResource(R.drawable.corners5_solidblack);
                        Message msgs = mHandler.obtainMessage();
                        msgs.what = 1;
                        msgs.sendToTarget();
                        tvForgetGetmss.setEnabled(false);
                        rlGetMesss.setEnabled(false);
                    }
                });
    }


    /**
     * 获取验证码时校验手机
     */
    private boolean validates() {
        if (forgetPhone.getText().toString().length() == 0) {
            Toast.makeText(this, "请填写手机号!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (forgetPhone.getText().toString().length() != 11) {
            Toast.makeText(this, "请填写11位手机号!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!forgetPhone.getText().toString().matches(mobile)) {
            Toast.makeText(this, "电话格式不对！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public Boolean validate() {
        if (forgetPhone.getText().length() == 0) {
            ToastUtils.showToast(this, "请填写电话");
            return false;
        } else if (forgetPhone.getText().length() != 11) {
            ToastUtils.showToast(this, "请填写11位手机号");
            return false;
        } else if (forgetPwd.getText().toString().length() == 0) {
            ToastUtils.showToast(this, "请填写新密码");
            return false;
        } else if (forgetTwopwd.getText().toString().length() == 0) {
            ToastUtils.showToast(ForgetPwdActivity.this, "请再次输入新密码");
            return false;
        } else if (forgetPwd.getText().toString().length() <= 5) {
            ToastUtils.showToast(this, "请填写六位以上密码");
            return false;
        } else if (!(forgetPwd.getText().toString().equals(forgetTwopwd.getText().toString()))) {
            ToastUtils.showToast(ForgetPwdActivity.this, "两次输入的密码不一致,请重新输入");
            return false;
        } else if (forgetMss.getText().toString().length() == 0) {
            ToastUtils.showToast(ForgetPwdActivity.this, "请输入验证码");
            return false;
        } else {
            return true;
        }
    }

    private void userforgetClient() {
        OkGo.<String>post(HttpManager.forgotPassword)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("mobile", forgetPhone.getText().toString())
                .params("password", forgetPwd.getText().toString())
                .params("confirm_pwd", forgetTwopwd.getText().toString())
                .params("code", forgetMss.getText().toString())
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        RegisterBean registerBean = gson.fromJson(response.body().toString(), RegisterBean.class);
                        if (registerBean.getStatus() != 0) {
                            ToastUtils.showToast(ForgetPwdActivity.this,"密码修改成功!");
                            finish();
                        }else {
                            ToastUtils.showToast(ForgetPwdActivity.this, registerBean.getMsg());
                        }
                    }
                });
    }


    @Override
    public void countDownTimerListener(String time) {
        tvForgetGetmss.setText(time);
    }

    @Override
    public void countDownTimerFinish() {
        rlGetMesss.setEnabled(true);
        tvForgetGetmss.setEnabled(true);
        tvForgetGetmss.setTextColor(Color.parseColor("#2dbe50"));
        rlGetMesss.setBackgroundResource(R.drawable.btn_register_bg);
    }



}
