package com.xingguang.localrun.maincode.mine.view.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.http.MyShare;
import com.xingguang.localrun.maincode.mine.model.AboutBean;
import com.xingguang.localrun.popwindow.SharePopUpWindow;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 关于同城快跑
 */
public class AboutActivity extends ToolBarActivity implements SharePopUpWindow.OnShareListener {

    @BindView(R.id.tv_introduction)
    TextView tvIntroduction;
    @BindView(R.id.ll_xieyi)
    LinearLayout llXieyi;
    @BindView(R.id.iv_QRcode)
    ImageView ivQRcode;
    @BindView(R.id.tv_qr)
    TextView tvQr;
    @BindView(R.id.ll_qrcode)
    LinearLayout llQrcode;
    @BindView(R.id.ll_parent)
    LinearLayout ll_parent;
    private UMImage image;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setToolBarTitle("关于同城快跑");
        load();
    }


    private void load() {
        OkGo.<String>post(HttpManager.Indexarticle)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id", 1)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        AboutBean aboutBean = gson.fromJson(response.body().toString(), AboutBean.class);
                        if (aboutBean.getData() != null) {
                            tvIntroduction.setText(aboutBean.getData().getDescribe());
                        } else {
                            ToastUtils.showToast(AboutActivity.this, aboutBean.getMsg());
                        }
                    }
                });
    }

    @OnClick({R.id.ll_xieyi, R.id.ll_qrcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_xieyi://同城快跑服务协议
                startActivity(new Intent(AboutActivity.this, WebViewActivity.class)
                        .putExtra("type", "2")
                );
                break;
            case R.id.ll_qrcode://分享二维码
                new SharePopUpWindow(AboutActivity.this, ll_parent, AboutActivity.this);
                break;
        }
    }


    @Override
    public void onShareListener(SHARE_MEDIA share_media) {
        //设置分享图片
        image = new UMImage(this, MyShare.getQr_code());//网络图片
        new ShareAction(AboutActivity.this)
                .setPlatform(share_media)
                .withMedia(image)
                .setCallback(umShareListener)
                .share();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            Toast.makeText(AboutActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(AboutActivity.this, "分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
        }
    };


}
