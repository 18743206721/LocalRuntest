package com.xingguang.localrun.login.view.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.xingguang.localrun.view.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 三方登录
 */
public class OtherLoginActivity extends ToolBarActivity {

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

    private static final String mobile = "^1\\d{10}$";
    String type;
    String user_id;
    String openid;
    String name;
    String headerUrl;

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

        type = getIntent().getStringExtra("type");
        openid = getIntent().getStringExtra("openid");
        user_id = getIntent().getStringExtra("user_id");
        name = getIntent().getStringExtra("name");
        headerUrl = getIntent().getStringExtra("headerUrl");

    }

    @OnClick(R.id.tv_commit)
    public void onViewClicked() {
        if (validate()) {
            loadcommit();
        }

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
        } else {
            return true;
        }
    }


}
