package com.xingguang.localrun.http;

import com.xingguang.localrun.maincode.classify.model.MyBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 创建日期：2018/5/22
 * 描述:网络接口
 * 作者:LiuYu
 */
public interface Service {

    @POST("user/register")
    Observable<MyBean> getSougu(@QueryMap Map<String, Object> maps);

    @GET("{url}")
    Observable<ResponseBody> getWeatherStr(@Path("url") String url, @QueryMap Map<String, String> maps);




}
