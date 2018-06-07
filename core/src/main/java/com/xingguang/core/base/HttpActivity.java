package com.xingguang.core.base;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xingguang.core.http.loading.CustomProgressDialog;
import com.xingguang.core.utils.ToastUtils;

/**
 * 用于http请求的基类 Activity
 *
 * @author LiuYu
 * @date 2016-12-09
 */
public abstract class HttpActivity extends BaseActivity implements HttpView {

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
