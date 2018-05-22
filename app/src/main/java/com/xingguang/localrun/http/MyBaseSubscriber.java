package com.xingguang.localrun.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.util.NetworkUtil;

/**
 * 创建日期：2018/5/22
 * 描述: 自定义的MyBaseSubscriber 里面，定义了一个进度条，这样在请求数据的时候，会显示正在加载中
 * 作者:LiuYu
 */
public abstract class MyBaseSubscriber<T>  extends BaseSubscriber<T> {

    private ProgressDialog progress;

    private Context context;

    public MyBaseSubscriber(Context context) {
        super(context);
        this.context = context;
        progress = new ProgressDialog(context);
        progress.setMessage("novate拼命加载中....");

    }


    @Override
    public void onStart() {
        super.onStart();

        if (!NetworkUtil.isNetworkAvailable(context)) {
            Toast.makeText( context, "似乎没网O",Toast.LENGTH_SHORT).show();
            onCompleted();
            return;
        }
        if (progress != null){
            if (progress.isShowing()) {
                progress.dismiss();
            }
            progress.show();
        }


    }

    @Override
    public void onCompleted() {
        super.onCompleted();

        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }
}
