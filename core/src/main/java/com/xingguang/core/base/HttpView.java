package com.xingguang.core.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * 用于http请求的基类 Interface
 *
 * @author LiuYu
 * @date 2016-11-25
 */
public interface HttpView {

    void onLoading();
    void loadingFinished();
    void showMsg(String msg);
    <T> LifecycleTransformer<T> bindRecycler();
}
