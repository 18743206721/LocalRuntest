package com.xingguang.localrun.login.view.activity;

import android.graphics.Color;
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
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.login.model.OtherLogin;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.utils.CountDownTimerUtil;
import com.xingguang.localrun.view.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 三方登录
 */
public class OtherLoginActivity extends ToolBarActivity implements CountDownTimerUtil.CountDownTimerListener{

    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.et_pwd)
    ClearEditText etPwd;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.ll_layout_denglu)
    LinearLayout llLayoutDenglu;
    @BindView(R.id.center)
    LinearLayout center;
    @BindView(R.id.register_mss)
    ClearEditText registerMss;
    @BindView(R.id.tv_regis_getmss)
    TextView tvRegisGetmss;
    @BindView(R.id.rl_get_messs)
    RelativeLayout rlGetMesss;

    private static final String mobile = "^1\\d{10}$";
    String type;
    String user_id;
    String openid;
    String name;
    String headerUrl;
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
        return R.layout.activity_other_login;
    }

    @Override
    protected void initView() {
        setToolBarTitle("登录");
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        util = new CountDownTimerUtil(this, this);

        type = getIntent().getStringExtra("type");
        openid = getIntent().getStringExtra("openid");
        user_id = getIntent().getStringExtra("user_id");
        name = getIntent().getStringExtra("name");
        headerUrl = getIntent().getStringExtra("headerUrl");

    }

    @OnClick({R.id.tv_commit,R.id.rl_get_messs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_get_messs: //获取验证码
                if (AppUtil.isFastDoubleClick(1000)) {
                    return;
                }
                if (etPhone.getText().toString().length() == 0) {
                    Toast.makeText(this, "请填写手机号!", Toast.LENGTH_SHORT).show();
                }else {
                    sendSMSClient();
                }
                break;
            case R.id.tv_commit:
                if (validate()) {
                    loadcommit();
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
                .params("mobile", etPhone.getText().toString())
                .params("type", "4")
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
     * 提交
     */
    private void loadcommit() {
        OkGo.<String>post(HttpManager.bindMobile)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("third_type", type)
                .params("uniqid", openid)
                .params("user_id", user_id)
                .params("mobile", etPhone.getText().toString())
                .params("password", etPwd.getText().toString())
                .params("code",registerMss.getText().toString())
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        OtherLogin otherLogin = gson.fromJson(response.body().toString(), OtherLogin.class);
                        if (otherLogin.getStatus() == 1) {
                            SharedPreferencesUtils.put(OtherLoginActivity.this, SharedPreferencesUtils.USERID, otherLogin.getData().getToken());
                            SharedPreferencesUtils.put(OtherLoginActivity.this, SharedPreferencesUtils.USERNAME, otherLogin.getData().getNickname());
                            SharedPreferencesUtils.put(OtherLoginActivity.this, SharedPreferencesUtils.USERIMAGE, HttpManager.INDEX + otherLogin.getData().getAvatar());
                            SharedPreferencesUtils.put(OtherLoginActivity.this, SharedPreferencesUtils.PHONE, otherLogin.getData().getMobile());
                            ToastUtils.showToast(OtherLoginActivity.this, otherLogin.getMsg());
                            finish();
                            LoginActivity.instance.finish();
                        }else{
                            ToastUtils.showToast(OtherLoginActivity.this, otherLogin.getMsg());
                        }
                    }
                });
    }

    public Boolean validate() {
        if (etPhone.getText().length() == 0) {
            ToastUtils.showToast(this, "请填写电话");
            return false;
        } else if (etPhone.getText().length() != 11) {
            ToastUtils.showToast(this, "请填写11位手机号");
            return false;
        } else if (etPwd.getText().toString().length() == 0) {
            ToastUtils.showToast(this, "请填写密码");
            return false;
        } else if (etPwd.getText().toString().length() <= 5) {
            ToastUtils.showToast(this, "请填写六位以上密码");
            return false;
        } else if (registerMss.getText().toString().length() == 0){
            ToastUtils.showToast(this, "请输入验证码!");
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
        rlGetMesss.setEnabled(true);
        tvRegisGetmss.setEnabled(true);
        tvRegisGetmss.setTextColor(Color.parseColor("#2dbe50"));
        rlGetMesss.setBackgroundResource(R.drawable.btn_register_bg);
    }


}
