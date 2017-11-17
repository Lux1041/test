package com.example.tianyi.iphoneassist.di.component;

import com.example.tianyi.iphoneassist.AppAplication;
import com.example.tianyi.iphoneassist.common.rx.RxErrorHandler;
import com.example.tianyi.iphoneassist.di.module.AppModule;
import com.example.tianyi.iphoneassist.di.module.HttpModule;
import com.example.tianyi.iphoneassist.http.ApiServer;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Tianyi on 2017/11/14.
 */


@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    public ApiServer getapiserver();//外界需要一个APIserver对象，所以必须提供一个返回值为ApiServer类的方法，方法名任意

    public RxErrorHandler getRxErrorHandler();

    public AppAplication getAppAplication();

    public Retrofit getRetrofit();
}
