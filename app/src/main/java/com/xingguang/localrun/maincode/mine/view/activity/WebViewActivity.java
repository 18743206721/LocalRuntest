package com.xingguang.localrun.maincode.mine.view.activity;

import android.view.View;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.xingguang.core.utils.ToastUtils;
import com.xingguang.localrun.R;
import com.xingguang.localrun.base.ToolBarActivity;
import com.xingguang.localrun.http.DialogCallback;
import com.xingguang.localrun.http.HttpManager;
import com.xingguang.localrun.maincode.mine.model.AboutBean;

import butterknife.BindView;

/**
 * 网页
 */
public class WebViewActivity extends ToolBarActivity {

    @BindView(R.id.webView1)
    WebView webView1;
    String type = "";

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
        type = getIntent().getStringExtra("type");

        if ("3".equals(type)) { //注册协议
            loadxieyi(3);
        } else if ("2".equals(type)) { //服务协议
            loadxieyi(2);
        }

    }


    //协议
    private void loadxieyi(int type) {
        OkGo.<String>post(HttpManager.Indexarticle)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("id", type)
                .execute(new DialogCallback<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        AboutBean aboutBean = gson.fromJson(response.body().toString(), AboutBean.class);
                        if (aboutBean.getData() != null) {
                            String html = aboutBean.getData().getContent();
                            String data = html.replace("%@", html);
                            webView1.loadData(data, "text/html; charset=UTF-8", null);
                        } else {
                            ToastUtils.showToast(WebViewActivity.this, aboutBean.getMsg());
                        }
                    }
                });

    }


}
