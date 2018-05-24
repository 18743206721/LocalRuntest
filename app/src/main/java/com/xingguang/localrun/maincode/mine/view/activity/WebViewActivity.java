package com.xingguang.localrun.maincode.mine.view.activity;

import android.view.View;
import android.webkit.WebView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;

import butterknife.BindView;

/**
 * 网页
 */
public class WebViewActivity extends ToolBarActivity {

    @BindView(R.id.webView1)
    WebView webView1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setToolBarTitle("服务协议");
        loadxieyi();

    }


    //协议
    private void loadxieyi() {
//        String html = state.getUserAgreement();
//        String data = html.replace("%@", html);
//        webView1.loadData(data, "text/html; charset=UTF-8", null);
    }


}
