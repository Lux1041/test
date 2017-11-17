package com.example.tianyi.iphoneassist.di.module;

import android.app.Application;

import com.example.tianyi.iphoneassist.AppAplication;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tianyi on 2017/11/14.
 */

@Module
public class AppModule {

    private AppAplication appAplication;
    public AppModule(Application application){
        this.appAplication = (AppAplication) application;
    }

    @Singleton
    @Provides
    public AppAplication provideAppAplication(){
        return appAplication;
    }

    @Singleton
    @Provides
    public Gson provideGson(){
        return new Gson();
    }
}
