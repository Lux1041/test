package com.example.tianyi.iphoneassist.http;

import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.bean.BaseBean;
import com.example.tianyi.iphoneassist.bean.IndexBean;
import com.example.tianyi.iphoneassist.bean.PageBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Tianyi on 2017/11/13.
 */

public interface ApiServer {

    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";
    public static final String BASE_IMAGE_URL = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

//    @GET("featured")
//    public Call<PageBean<AppInfo>> getDownLoadAppInfo(@Query("p")String jsonParams);

    @GET("featured")
    public Observable<PageBean<AppInfo>> getDownLoadAppInfo(@Query("p")String jsonParams);

    @GET("featured2")
    public Observable<BaseBean<PageBean<AppInfo>>> getDownLoadAppInfo2(@Query("p")String jsonParams);

    @GET("index")
    public Observable<BaseBean<IndexBean>> index();

    @GET("toplist")
    public  Observable<BaseBean<PageBean<AppInfo>>> topList(@Query("page") int page);

    @GET("game")
    public  Observable<BaseBean<PageBean<AppInfo>>> games(@Query("page") int page);


//    @POST("login")
//    Observable<BaseBean<LoginBean>> login(@Body LoginRequestBean param);
}
