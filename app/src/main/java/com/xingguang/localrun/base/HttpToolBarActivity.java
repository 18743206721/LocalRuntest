package com.xingguang.localrun.base;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xingguang.core.base.HttpView;
import com.xingguang.core.http.loading.CustomProgressDialog;
import com.xingguang.core.utils.ToastUtils;

/**
 * 用于http请求的基类 Activity
 *
 * @author BiHaidong
 * @date 2017-4-25
 */
public abstract class HttpToolBarActivity extends ToolBarActivity implements HttpView {

    CustomProgressDialog pd = null;
    protected int postCount = 0;
    protected int finishCount = 0;

    @Override
    public void onLoading() {
        postCount++;
        if (pd == null)
            pd = CustomProgressDialog.createDialog(this);
    }

    @Override
    public void loadingFinished() {
        finishCount++;
        if (finishCount == postCount) {
            if (pd != null)
                pd.dismiss();
        }
    }

    @Override
    public <T> LifecycleTransformer<T> bindRecycler() {
        return this.bindToLifecycle();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.showToast(this, msg);
    }


}
