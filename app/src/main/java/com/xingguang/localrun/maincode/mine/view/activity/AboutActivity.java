package com.xingguang.localrun.maincode.mine.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 关于同城快跑
 */
public class AboutActivity extends ToolBarActivity {

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


    }

    @OnClick({R.id.ll_xieyi, R.id.ll_qrcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_xieyi://同城快跑服务协议
                startActivity(new Intent(AboutActivity.this,WebViewActivity.class));
                break;
            case R.id.ll_qrcode://分享二维码

                break;
        }
    }


}
