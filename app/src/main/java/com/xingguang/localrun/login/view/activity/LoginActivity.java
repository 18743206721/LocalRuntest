package com.xingguang.localrun.login.view.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.view.ClearEditText;
import butterknife.BindView;
import butterknife.OnClick;
/**
 * 创建日期：2018/5/19
 * 描述:登录界面
 * 作者:LiuYu
 */
public class LoginActivity extends ToolBarActivity {

    @BindView(R.id.et_forget_phone)
    ClearEditText loginPhone;
    @BindView(R.id.login_pwd)
    ClearEditText loginPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.forget_psw)
    TextView forgetPsw;
    @BindView(R.id.qq_btn)
    LinearLayout qqBtn;
    @BindView(R.id.wecha_btn)
    LinearLayout wechaBtn;
    @BindView(R.id.sinablog_btn)
    LinearLayout sinablogBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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
        //设置下划线
        tvRegister.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        tvRegister.getPaint().setAntiAlias(true);//抗锯齿

    }

    @OnClick({R.id.tv_login, R.id.tv_register, R.id.forget_psw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login://登录
                if (AppUtil.isFastDoubleClick(1000)) {
                    return;
                }
                if (validate()) {
                    userLoginClient();
                }
                break;
            case R.id.tv_register://注册
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.forget_psw://忘记密码
                startActivity(new Intent(LoginActivity.this,ForgetPwdActivity.class));
                break;
        }
    }

    //登录
    private void userLoginClient() {
        ToastUtils.showToast(LoginActivity.this,"登录成功!");

    }


    public Boolean validate() {
        if (loginPhone.getText().length() == 0) {
            ToastUtils.showToast(this, "请填写电话");
            return false;
        } else if (loginPhone.getText().length() != 11) {
            ToastUtils.showToast(this, "请填写11位手机号");
            return false;
        } else if (loginPwd.getText().toString().length() == 0) {
            ToastUtils.showToast(this, "请填写密码");
            return false;
        } else {
            return true;
        }
    }



}
