package com.xingguang.localrun.http;

import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.xingguang.core.http.api.HttpResult;
import com.xingguang.localrun.login.model.LoginBean;
import com.xingguang.localrun.login.model.SMSBean;
import com.xingguang.localrun.maincode.home.model.HIndexBean;
import com.xingguang.localrun.view.Star;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * 创建日期：2018/5/22
 * 描述:网络接口
 * 作者:LiuYu
 */
public interface Service {


//    @GET("{url}")
//    Observable<ResponseBody> getWeatherStr(@Path("url") String url, @QueryMap Map<String, String> maps);

    //发送验证码
//    @FormUrlEncoded
//    @POST("Public/sendSms")
//    Observable<HttpResult<SMSBean>> getsendsms(@FieldMap Map<String, Object> map);
//
//    //首页广告图
//    @POST("Index/index")
//    Observable<HttpResult<HIndexBean>> getindex(@Body RequestBody route);
//
//    //登录
//    @POST("Public/login")
//    Observable<HttpResult<LoginBean>> getlogin(@Body RequestBody route);




}
