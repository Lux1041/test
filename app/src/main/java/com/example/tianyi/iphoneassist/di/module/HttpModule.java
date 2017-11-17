package com.example.tianyi.iphoneassist.di.module;

import com.example.tianyi.iphoneassist.AppAplication;
import com.example.tianyi.iphoneassist.common.http.CommonParamsInterceptor;
import com.example.tianyi.iphoneassist.common.rx.RxErrorHandler;
import com.example.tianyi.iphoneassist.http.ApiServer;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tianyi on 2017/11/14.
 */

@Module
public class HttpModule {

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(AppAplication context, Gson mGson){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging)
                .addInterceptor(new CommonParamsInterceptor(context, mGson))
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS);
        return builder.build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient client){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(ApiServer.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client);
        return builder.build();
    }

    @Provides
    @Singleton
    public ApiServer provideApiServer(Retrofit retrofit){
        return retrofit.create(ApiServer.class);
    }

    @Provides
    @Singleton
    public RxErrorHandler provideErrorHandler(AppAplication appAplication){
        return new RxErrorHandler(appAplication);
    }


}
