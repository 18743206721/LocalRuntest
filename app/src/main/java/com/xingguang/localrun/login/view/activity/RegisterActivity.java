package com.xingguang.localrun.login.view.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
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
public class RegisterActivity extends ToolBarActivity implements CountDownTimerUtil.CountDownTimerListener{

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


    }

    @OnClick({R.id.rl_get_messs, R.id.register_user_xieyi, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_get_messs: //获取验证码

                break;
            case R.id.register_user_xieyi://注册协议

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
        ToastUtils.showToast(RegisterActivity.this,"恭喜您,注册成功!");
        finish();
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
        } else if (registerTwopwd.getText().toString().length() == 0) {
            ToastUtils.showToast(RegisterActivity.this, "请再次输入密码");
            return false;
        } else if (!(registerPwd.getText().toString().equals(registerTwopwd.getText().toString()))) {
            ToastUtils.showToast(RegisterActivity.this, "两次输入的密码不一致,请重新输入");
            return false;
        } else if (registerMss.getText().toString().length() == 0) {
            ToastUtils.showToast(RegisterActivity.this, "请输入验证码");
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
