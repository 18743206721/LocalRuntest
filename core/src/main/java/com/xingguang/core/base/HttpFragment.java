package com.xingguang.core.base;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xingguang.core.http.loading.CustomProgressDialog;
import com.xingguang.core.utils.ToastUtils;

/**
 * 用于http请求的基类 Fragment
 *
 * @author LiuYu
 * @date 2016-12-09
 */
public abstract class HttpFragment extends BaseFragment implements HttpView {

    CustomProgressDialog pd = null;
    protected int postCount = 0;
    protected int finishCount = 0;

    @Override
    public void onLoading() {
        postCount++;
        if (pd == null)
            pd = CustomProgressDialog.createDialog(getActivity());
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
        ToastUtils.showToast(getActivity(), msg);
    }
}
