package com.xingguang.localrun.http;

import android.support.annotation.NonNull;

import com.xingguang.core.base.BaseApplication;
import com.xingguang.core.http.api.HttpResult;
import com.xingguang.core.http.cookie.CacheInterceptor;
import com.xingguang.core.http.exception.ApiException;
import com.xingguang.core.utils.NetUtil;
import com.xingguang.localrun.maincode.home.model.ProcurementIndexBean;

import org.json.JSONObject;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建日期：2018/5/22
 * 描述:
 * 作者:LiuYu
 */
public class HttpManager {

    //线上
    public static final String BASE_URL = " http://47.52.240.241/app/";
    //    public static final String BASE_URL = "http://192.168.51.19:8088/app/";


    private volatile static HttpManager INSTANCE;
    private Service httpService;

    //一页加载多少条数据
    public static int CONTENT_COUNT = 10;
    //打电话，电话号
    public static int CALL_PHONE = 10086;

    /**
     * 超时时间-默认6秒
     */
    public static int CONNECTION_TIME = 6;

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;

    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";

    /**
     * 是否显示log
     */
    private static final Boolean SHOW_LOG = true;

    /**
     * 根据网络状况获取缓存的策略
     */
    @NonNull
    private String getCacheControl() {
        return NetUtil.isNetworkAvailable() ? CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE;
    }

    //构造方法私有
    private HttpManager(String baseUrl) {

        //手动创建一个OkHttpClient并设置超时时间缓存等设置
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (SHOW_LOG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        builder.connectTimeout(CONNECTION_TIME, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(new CacheInterceptor());
        /*缓存位置和大小*/
        builder.cache(new Cache(BaseApplication.app.getCacheDir(), 10 * 1024 * 1024));

        /*创建retrofit对象*/
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        httpService = retrofit.create(Service.class);
    }

    //获取单例 无缓存
    public static HttpManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpManager(BASE_URL);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Function<HttpResult<T>, T> {

//        @Override
//        public T call(HttpResult<T> httpResult) {
//            if (!"success".equals(httpResult.getState())) {
//                throw new ApiException(httpResult.getMsg());
//            }
//            return httpResult.getData();
//        }

        @Override
        public T apply(HttpResult<T> httpResult)  {
            if (!"success".equals(httpResult.getState())) {
                throw new ApiException(httpResult.getMsg());
            }
            return httpResult.getData();
        }
    }

    /**
     * 用RXAndroid来统一处理网络请求的线程
     */
    public Observable initObservable(Observable observable) {
        /*失败后的retry配置*/
//        return observable.retryWhen(new RetryWhenNetworkException())
//                /*http请求线程*/
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                /*回调线程*/
//                .observeOn(AndroidSchedulers.mainThread());

        return observable
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 用于配置请求参数为json
     */
    public RequestBody initRequestBody(String json) {
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
    }

    public MultipartBody.Part getRequestBody(File file) {

        if (file == null) {
            return null;
        }

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        return body;
    }

    //办公采购首页
    public Observable ProcurementIndex() {
        JSONObject json = new JSONObject();
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        Observable observable = httpService.ProcurementIndex(initRequestBody(json.toString()))
                .map(new HttpResultFunc<ProcurementIndexBean>());
        return initObservable(observable);
    }



}
