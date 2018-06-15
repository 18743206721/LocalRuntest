package com.xingguang.localrun.login.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.HttpToolBarActivity;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.login.model.RegisterBean;
import com.xingguang.localrun.maincode.mine.view.activity.WebViewActivity;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.CountDownTimerUtil;
import com.xingguang.localrun.view.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建日期：2018/5/19
 * 描述:注册页面
 * 作者:LiuYu
 */
public class RegisterActivity extends HttpToolBarActivity implements CountDownTimerUtil.CountDownTimerListener{

    @BindView(R.id.register_phone)
    ClearEditText registerPhone;
    @BindView(R.id.register_pwd)
    ClearEditText registerPwd;
    @BindView(R.id.register_twopwd)
    ClearEditText registerTwopwd;
    @BindView(R.id.register_mss)
    ClearEditText registerMss;
    @BindView(R.id.tv_regis_getmss)
    TextView tvRegisGetmss;
    @BindView(R.id.rl_get_messs)
    RelativeLayout rlGetMesss;
    @BindView(R.id.register_user_xieyi)
    TextView registerUserXieyi;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.check_box)
    CheckBox check_box;

    private static final String mobile = "^1\\d{10}$";
    private CountDownTimerUtil util;
    public static RegisterActivity instance;

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

    private int shushi = 0;
    private boolean isdianji = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        setToolBarTitle("注册");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        instance = this;
        util = new CountDownTimerUtil(this, this);

        check_box.setChecked(true);
        shushi = 1;

    }

    @OnClick({R.id.rl_get_messs, R.id.register_user_xieyi, R.id.tv_register,R.id.check_box})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_get_messs: //获取验证码
                if (AppUtil.isFastDoubleClick(1000)) {
                    return;
                }
                if (validates()) {
                    sendSMSClient();
                }
                break;
            case R.id.check_box:
                isdianji = !isdianji;
                if (isdianji) {
                    check_box.setChecked(true);
                    shushi = 1;
                } else {
                    check_box.setChecked(false);
                    shushi = 0;
                }
                break;
            case R.id.register_user_xieyi://注册协议
                Intent intent = new Intent(RegisterActivity.this, WebViewActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
                break;
            case R.id.tv_register: //注册
                if (AppUtil.isFastDoubleClick(1000)) {
                    return;
                }
                if (validate()) {
                    userRegistClient();
                }
                break;
        }


    }

    //注册
    private void userRegistClient() {
        OkGo.<String>post(HttpManager.register)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("mobile", registerPhone.getText().toString())
                .params("password", registerPwd.getText().toString())
                .params("confirm_pwd", registerTwopwd.getText().toString())
                .params("code", registerMss.getText().toString())
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        RegisterBean registerBean = gson.fromJson(response.body().toString(), RegisterBean.class);
                        if (registerBean.getStatus() != 0) {
                            ToastUtils.showToast(RegisterActivity.this, "恭喜您,注册成功!");
                            finish();
                        }else {
                            ToastUtils.showToast(RegisterActivity.this, registerBean.getMsg());
                        }
                    }
                });

    }

    /**
     * 发送验证码
     */
    private void sendSMSClient() {
        OkGo.<String>post(HttpManager.sendSms)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("mobile", registerPhone.getText().toString())
                .params("type", "1")
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

    /**
     * 获取验证码时校验手机
     */
    private boolean validates() {
        if (registerPhone.getText().toString().length() == 0) {
            Toast.makeText(this, "请填写手机号!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (registerPhone.getText().toString().length() != 11) {
            Toast.makeText(this, "请填写11位手机号!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!registerPhone.getText().toString().matches(mobile)) {
            Toast.makeText(this, "电话格式不对！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public Boolean validate() {
        if (registerPhone.getText().length() == 0) {
            ToastUtils.showToast(this, "请填写电话");
            return false;
        } else if (registerPhone.getText().length() != 11) {
            ToastUtils.showToast(this, "请填写11位手机号");
            return false;
        } else if (registerPwd.getText().toString().length() == 0) {
            ToastUtils.showToast(this, "请填写密码");
            return false;
        } else if (registerPwd.getText().toString().length() <= 5) {
            ToastUtils.showToast(this, "请填写六位以上密码");
            return false;
        } else if (registerTwopwd.getText().toString().length() == 0) {
            ToastUtils.showToast(RegisterActivity.this, "请再次输入密码");
            return false;
        } else if (!(registerPwd.getText().toString().equals(registerTwopwd.getText().toString()))) {
            ToastUtils.showToast(RegisterActivity.this, "两次输入的密码不一致,请重新输入");
            return false;
        } else if (registerMss.getText().toString().length() == 0) {
            ToastUtils.showToast(RegisterActivity.this, "请输入验证码");
            return false;
        } else if (shushi == 0) {
            ToastUtils.showToast(RegisterActivity.this, "请您确认勾选注册协议");
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void countDownTimerListener(String time) {
        tvRegisGetmss.setText(time);
    }

    @Override
    public void countDownTimerFinish() {
        tvRegisGetmss.setEnabled(true);
        tvRegisGetmss.setTextColor(Color.parseColor("#2dbe50"));
        rlGetMesss.setBackgroundResource(R.drawable.btn_register_bg);
    }




}
