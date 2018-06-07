package com.xingguang.core.http.subscribers;

import com.xingguang.core.base.HttpView;
import com.xingguang.core.http.listener.HttpOnNextListener;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 用于在Http请求时的缓存处理
 *
 * @author LiuYu
 * @date 2016-12-5
 */
public class BaseSubscriber<T> implements Observer<T> {

    // 回调接口
    private HttpOnNextListener<T> mSubscriberOnNextListener;

    // 拿到view的实例，用于处理共同部分
    private HttpView mView;


    /**
     * 构造 （不需缓存）
     */
    public BaseSubscriber(HttpOnNextListener<T> listener, HttpView view) {
        this.mSubscriberOnNextListener = listener;
        this.mView = view;
    }

    /**
     * 开始
     */
//    @Override
//    public void onSubscribe(Subscription s) {
//        if (mView != null)
//            mView.onLoading();
//    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        if (mView != null)
//            mView.onLoading();
//    }

    /**
     * 完成
     */
    @Override
    public void onComplete() {

    }

    /**
     * 对错误进行统一处理
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onError(e);
            String error;
            if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
                error = "网络中断，请检查您的网络状态";
            } else {
                error = e.getMessage();
            }
            if (mView != null) {
                mView.showMsg(error);
                mView.loadingFinished();
            }
        }
    }


    @Override
    public void onSubscribe(Disposable disposable) {
        if (mView != null)
            mView.onLoading();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }

}