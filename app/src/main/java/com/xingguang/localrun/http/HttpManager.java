package com.xingguang.localrun.http;

import android.support.annotation.NonNull;

import com.xingguang.core.base.BaseApplication;
import com.xingguang.core.http.api.HttpResult;
import com.xingguang.core.http.cookie.CacheInterceptor;
import com.xingguang.core.utils.NetUtil;

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
    public static final String BASE_URL = "http://140.143.248.102/index.php/";
    //本地测试
//    public static final String BASE_URL = "http://192.168.0.230/index.php/";


    //登录接口
    public static final String Login= BASE_URL+"Public/login";

    //发送验证码
    public static final String sendSms= BASE_URL+"Public/sendSms";

    //注册
    public static final String register= BASE_URL+"Public/register";

    //退出
    public static final String logout= BASE_URL+"Public/logout";

    //申请入驻
    public static final String apply= BASE_URL+"User/apply";

    //申请入驻类别
    public static final String applyindex = BASE_URL+"Goods/index";

    //修改头像
    public static final String update_avatar= BASE_URL+"User/update_avatar";

    //修改昵称
    public static final String update_nickname= BASE_URL+"User/update_nickname";

    //修改手机号
    public static final String update_mobile= BASE_URL+"User/update_mobile";

    //首页轮播图
    public static final String Index= BASE_URL+"Index/index";

    //忘记密码
    public static final String forgotPassword= BASE_URL+"Public/forgotPassword";

    //推荐商品
    public static final String tjgoods= BASE_URL+"Index/tjgoods";

    //推荐代办
    public static final String tjtask= BASE_URL+"Index/tjtask";

    //代办列表
    public static final String Taskindex= BASE_URL+"Task/index";

    //代办详情
    public static final String Taskdetail= BASE_URL+"Task/detail";

    //添加地址
    public static final String Addadres= BASE_URL+"User/addAddress";

    //收货地址列表
    public static final String ListAddress= BASE_URL+"User/address";

    //收货地址详情
    public static final String UserADSdetail= BASE_URL+"User/address_detail";

    //删除收货地址
    public static final String deletedads= BASE_URL+"User/delAddress";

    //设置默认收货地址
    public static final String usersetAds= BASE_URL+"User/setAddress";

    //编辑收货地址
    public static final String editAddress= BASE_URL+"User/editAddress";

    //关注店铺列表
    public static final String collectShop= BASE_URL+"User/collectShop";

    //取消关注店铺
    public static final String cancelCollectShop= BASE_URL+"User/cancelCollectShop";

    //足迹列表
    public static final String visit= BASE_URL+"User/visit";

    //删除足迹
    public static final String delVisit= BASE_URL+"User/delVisit";



    private volatile static HttpManager INSTANCE;
    private Service httpService;

    //index.php
    public static final String INDEX = "http://140.143.248.102/";

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
        @Override
        public T apply(HttpResult<T> httpResult) {
//            if (!"SUCCESS".equals(httpResult.getStatus())) {
//                throw new ApiException(httpResult.getMsg());
//            }
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
//    public RequestBody initRequestBody(String json) {
//        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
//    }

    /**
     * 用于配置请求参数为表单
     */
//    public Map<String,Object> initRequestBody(String data) {
//        return FormBody.create(okhttp3.MediaType.parse("application/x-www-form-urlencoded"), formdata);
//        return new MultipartBody.Builder().setType(MultipartBody.FORM).addPart(formdata).build();
//        return RequestBody.create(MediaType.parse("multipart/form-data"),data);
//        FormBody.Builder builder = new FormBody.Builder();
//        if (params!=null&&!params.isEmpty()){
//            for(Map.Entry<String,String> entry:params.entrySet()){
//                builder.add(entry.getKey(),entry.getValue());
//            }
//        }
//        return MultipartBody.Part
//    }

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

    //请求验证码
//    public Observable getsendsms(String mobile, String type) {
////        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
////                .addFormDataPart("mobile", mobile)
////                .addFormDataPart("type", type).build();
////        RequestBody requestApiSecret = RequestBody.create(MediaType.parse("multipart/form-data"), mobile);
////        RequestBody body = new FormBody.Builder()
////                .add("mobile", mobile)
////                .add("type", type)
////                .build();
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("mobile", mobile);
//        map.put("type",type);
//
//        Observable observable = httpService.getsendsms(
//                initRequestBody(map.toString()))
//                /*结果判断*/
//                .map(new HttpResultFunc<SMSBean>());
//        return initObservable(observable);
//    }


    //首页广告
//    public Observable getindex() {
//        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("","" )
//                  .build();
//        RequestBody body = new FormBody.Builder()
////                .add("mobile", mobile)
////                .add("type", type)
//                .build();
//        Observable observable = httpService.getindex(initRequestBody(requestBody.toString()));
////                .map(new HttpResultFunc<HIndexBean>());
//        return initObservable(observable);
//    }
//
//    //登录
//    public Observable getlogin(String mobile, String password) {

//        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("mobile", mobile)
//                .addFormDataPart("password", password).build();
//
//        Observable observable = httpService.getlogin(initRequestBody(requestBody.toString()))
//                /*结果判断*/
//                .map(new HttpResultFunc<LoginBean>());
//        return Ok;
//    }

//    public Observable getlogin(String mobile, String password) {
//
//        Observable<Response<String>> observable = OkGo.<String>post(BASE_URL)
//                .params("mobile", mobile)
//                .converter(new StringConvert())
//                .adapt(ObservableResponse<String>);
//
//
//        return observable;
//    }

//    OkGo.<String>post(HttpManager.BASE_URL)
//                .tag(this)
//                .cacheKey("cachePostKey")
//                .cacheMode(CacheMode.DEFAULT)
//                .converter(new StringConvert())
//            .adapt(ObservableResponse<String>);
//                .params("mobile", loginPhone.getText().toString())
//            .params("password", loginPwd.getText().toString())
//            .execute(new StringCallback() {
//        @Override
//        public void onSuccess(Response<String> response) {
//        }
//    });

//    public static Observable<String> getString(String param){
////        HttpHeaders headers = new HttpHeaders();
//////        headers.put();
////        HttpParams params = new HttpParams();
////        params.put("");
//
//        return OkGo.<String>post(BASE_URL)
//                .converter(new StringConvert())
//                .adapt(ObservableResponse<String>);
//    }


}
