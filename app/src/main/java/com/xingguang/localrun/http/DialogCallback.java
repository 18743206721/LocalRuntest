/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xingguang.localrun.http;

import android.app.Activity;

import com.lzy.okgo.request.base.Request;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：对于网络请求是否需要弹出进度对话框
 * 修订历史：
 * ================================================
 */
public abstract class DialogCallback<T> extends JsonCallBack<T> {

    CustomProgressDialog pd = null;
    Activity activity;
    protected int postCount = 0;
    protected int finishCount = 0;

//    private void initDialog(Activity activity) {
//        dialog = new ProgressDialog(activity);
//        View view = LayoutInflater.from(activity).inflate(R.layout.loading_dialog,null);
//         iva = view.findViewById(R.id.loading);
//
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setProgressStyle(R.style.CustomProgressDialog);
//        dialog.setMessage("正在加载中...");
//
//    }

    public DialogCallback(Activity activity) {
        super();
//        initDialog(activity);
        this.activity = activity;

    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
//        if (dialog != null && !dialog.isShowing()) {
//            dialog.show();
//            iva.setVisibility(View.VISIBLE);
//        }

        postCount++;
        if (pd == null)
            pd = CustomProgressDialog.createDialog(activity);

    }

    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
//        if (dialog != null && dialog.isShowing()) {
//            dialog.dismiss();
//            iva.setVisibility(View.GONE);
//        }

        finishCount++;
        if (finishCount == postCount) {
            if (pd != null)
                pd.dismiss();
        }

    }
}
