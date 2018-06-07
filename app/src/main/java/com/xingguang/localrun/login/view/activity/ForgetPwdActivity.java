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

    }

    @OnClick({R.id.rl_get_messs, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_get_messs://验证码

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
        ToastUtils.showToast(ForgetPwdActivity.this,"修改成功!");
        finish();
    }


    @Override
    public void countDownTimerListener(String time) {
        tvForgetGetmss.setText(time);
    }

    @Override
    public void countDownTimerFinish() {
        tvForgetGetmss.setEnabled(true);
        tvForgetGetmss.setTextColor(Color.parseColor("#2dbe50"));
        rlGetMesss.setBackgroundResource(R.drawable.btn_register_bg);
    }



}
