package com.xingguang.localrun.login.view.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xingguang.core.utils.LogUtils;
import com.xingguang.core.utils.SharedPreferencesUtils;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.HttpToolBarActivity;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.login.model.LoginBean;
import com.xingguang.localrun.login.model.OtherLogin;
import com.xingguang.localrun.utils.AppUtil;
import com.xingguang.localrun.view.ClearEditText;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建日期：2018/5/19
 * 描述:登录界面
 * 作者:LiuYu
 */
public class LoginActivity extends HttpToolBarActivity {

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
    private UMShareAPI mShareAPI = null;
    // 三方类型 1:微信；2：QQ；3：微博
    private String type = "";
    private String openid;
    private String headerUrl;
    private String name;
    private String sex;

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
        tvRegister.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvRegister.getPaint().setAntiAlias(true);//抗锯齿

        mShareAPI = UMShareAPI.get(this);
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(this).setShareConfig(config);

    }

    @OnClick({R.id.tv_login, R.id.tv_register, R.id.forget_psw, R.id.qq_btn, R.id.wecha_btn, R.id.sinablog_btn})
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
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.forget_psw://忘记密码
                startActivity(new Intent(LoginActivity.this, ForgetPwdActivity.class));
                break;
            case R.id.qq_btn://QQ
                if (AppUtil.isFastDoubleClick(1000)) {
                    return;
                }
                resetLoginButton(true);
                type = "qq";
                mShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.wecha_btn://weixin
                if (AppUtil.isFastDoubleClick(1000)) {
                    return;
                }
                resetLoginButton(true);
                type = "weixin";
                mShareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            case R.id.sinablog_btn://新浪
                if (AppUtil.isFastDoubleClick(1000)) {
                    return;
                }
                resetLoginButton(true);
                type = "sina";
                mShareAPI.getPlatformInfo(this, SHARE_MEDIA.SINA, umAuthListener);
                break;
        }
    }

    //登录
    public void userLoginClient() {
        OkGo.<String>post(HttpManager.Login)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("mobile",loginPhone.getText().toString() )
                .params("password", loginPwd.getText().toString())
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("userLoginClient", "onSuccess: " + response.body().toString());
                        Gson gson = new Gson();
                        LoginBean loginBean = gson.fromJson(response.body().toString(), LoginBean.class);
                        if (loginBean.getData()!=null) {
                            SharedPreferencesUtils.put(LoginActivity.this, SharedPreferencesUtils.USERID, loginBean.getData().getToken());
                            SharedPreferencesUtils.put(LoginActivity.this, SharedPreferencesUtils.USERNAME, loginBean.getData().getNickname());
                            SharedPreferencesUtils.put(LoginActivity.this, SharedPreferencesUtils.USERIMAGE, HttpManager.INDEX + loginBean.getData().getAvatar());
                            SharedPreferencesUtils.put(LoginActivity.this, SharedPreferencesUtils.PHONE, loginBean.getData().getMobile());
                            ToastUtils.showToast(LoginActivity.this, "恭喜您,登录成功!");
                            finish();
                        }else {
                            ToastUtils.showToast(LoginActivity.this, loginBean.getMsg());
                        }
                    }
                });
    }


    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            //授权开始的回调
            LogUtils.e("test", "onStart" + share_media.toString());
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            LogUtils.e("test", "onComplete");
            if (platform == SHARE_MEDIA.SINA) {
                openid = data.get("id");
            } else if (platform == SHARE_MEDIA.WEIXIN) {
                openid = data.get("unionid");
            } else {
                openid = data.get("openid");
            }
            headerUrl = data.get("iconurl");
            name = data.get("name");
//            sex = "男".equals(data.get("gender")) ? "1" : "0";

            resetLoginButton(false);
            //第三方登陆 获取相关个人信息
            loadOtherLogin(openid, name, headerUrl,type);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            // 错误码：2002 错误信息：授权失败----errorcode:21338 message:sso package or sign error
            //包名和签名不一致
            LogUtils.e("test", "onError" + share_media + i + throwable.toString());
            resetLoginButton(false);
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            LogUtils.e("test", "onCancel" + share_media.toString() + i);
            resetLoginButton(false);
        }
    };

    /**
     * 三方登录
     */
    private void loadOtherLogin(final String openid, final String name, final String headerUrl, final String type) {
        OkGo.<String>post(HttpManager.Publicthird)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("third_type",type)
                .params("uniqid",openid)
                .params("nickname",name)
                .params("avatar",headerUrl)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        OtherLogin otherLogin = gson.fromJson(response.body().toString(), OtherLogin.class);
                            if (!otherLogin.getData().getToken().equals("")){
                                LoginActivity.this.finish();
                                ToastUtils.showToast(LoginActivity.this, "恭喜您,登录成功!");
                                SharedPreferencesUtils.put(LoginActivity.this, SharedPreferencesUtils.USERID, otherLogin.getData().getToken());
                                SharedPreferencesUtils.put(LoginActivity.this, SharedPreferencesUtils.USERNAME, otherLogin.getData().getNickname());
                                SharedPreferencesUtils.put(LoginActivity.this, SharedPreferencesUtils.USERIMAGE, HttpManager.INDEX + otherLogin.getData().getAvatar());
                                SharedPreferencesUtils.put(LoginActivity.this, SharedPreferencesUtils.PHONE, otherLogin.getData().getMobile());
                            }else {
                                Intent intent = new Intent(LoginActivity.this, OtherLoginActivity.class);
                                intent.putExtra("type",otherLogin.getData().getThird_type());
                                intent.putExtra("openid", otherLogin.getData().getUniqid());
                                intent.putExtra("user_id", otherLogin.getData().getUser_id());
                                intent.putExtra("name", name);
                                intent.putExtra("headerUrl", headerUrl);
                                startActivity(intent);
                            }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 设置登录按钮是否可点击
     *
     * @param flag true 不可点击 false 可点击
     */
    private void resetLoginButton(Boolean flag) {
        if (flag) {
//            pd = CustomProgressDialog.createDialog(this);
            wechaBtn.setClickable(false);
            qqBtn.setClickable(false);
            sinablogBtn.setClickable(false);
        } else {
//            if (pd != null)
//                pd.dismiss();
            sinablogBtn.setClickable(true);
            wechaBtn.setClickable(true);
            qqBtn.setClickable(true);
        }
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
        } else if (loginPwd.getText().toString().length() <= 5) {
            ToastUtils.showToast(this, "请填写六位以上密码");
            return false;
        } else {
            return true;
        }
    }


}
