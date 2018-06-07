package com.xingguang.localrun.http;

import com.xingguang.core.http.api.HttpResult;
import com.xingguang.localrun.maincode.home.model.ProcurementIndexBean;
import java.util.Map;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;


/**
 * 创建日期：2018/5/22
 * 描述:网络接口
 * 作者:LiuYu
 */
public interface Service {


    @GET("{url}")
    Observable<ResponseBody> getWeatherStr(@Path("url") String url, @QueryMap Map<String, String> maps);


    //6.1办公采购首页
    @POST("ProcurementIndex")
    Observable<HttpResult<ProcurementIndexBean>> ProcurementIndex(@Body RequestBody route);



}
