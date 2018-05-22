package com.xingguang.localrun.http;

import android.content.Context;

import com.tamic.novate.Novate;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建日期：2018/5/22
 * 描述:
 * 作者:LiuYu
 */
public class HttpManager {

    //线上
    public static final String BASE_URL = " http://47.52.240.241/app/";

    private volatile static HttpManager INSTANCE;
    private final Novate novate;
    private Service httpService;

    /**
     * 是否显示log
     */
    private static final Boolean SHOW_LOG = true;

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
     * 超时时间-默认6秒
     */
    public static int CONNECTION_TIME = 6;

    //构造方法私有
    private HttpManager(Context context) {
        novate = new Novate.Builder(context)
                .connectTimeout(8)
                .baseUrl(BASE_URL)
                //.addApiManager(ApiManager.class)
                .addLog(true)
                .build();

        Service myAPI  = novate.create(Service.class);
//        novate = new Novate.Builder(this)
//                .addHeader(headers) //添加公共请求头
//                .addParameters(parameters)//公共参数
//                .connectTimeout(10)  //连接时间 可以忽略
//                .addCookie(false)  //是否同步cooike 默认不同步
//                .addCache(true)  //是否缓存 默认缓存
//                .addCache(cache, cacheTime)   //自定义缓存
//                .baseUrl("Url") //base URL
//                .addLog(true) //是否开启log
//                .cookieManager(new NovateCookieManager()) // 自定义cooike，可以忽略
//                .addInterceptor() // 自定义Interceptor
//                .addNetworkInterceptor() // 自定义NetworkInterceptor
//                .proxy(proxy) //代理
//                .client(client)  //clent 默认不需要
//                .build();

    }


    public static HttpManager getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (HttpManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpManager(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    public void userLogin(String username, String password) {
        Map<String, Object> parameters = new HashMap<>();
        try {
            parameters.put("username", "zhansa");
            parameters.put("password", "12313");
        } catch (Exception e) {
            e.printStackTrace();
        }



//        novate.call(myAPI.getSougu(parameters), new MyBaseSubscriber<MyBean>(getActivity()) {
//            @Override
//            public void onNext(MyBean souguBean) {
//            }
//            @Override
//            public void onError(Throwable e) {
//            }
//        });

//        Observable observable = myAPI.getSougu(parameters)
//                .map(new HttpResultFunc<MyBean>());
////        return initObservable(observable);
    }


}
