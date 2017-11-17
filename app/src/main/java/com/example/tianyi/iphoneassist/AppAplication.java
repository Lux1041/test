package com.example.tianyi.iphoneassist;

import android.app.Application;
import android.content.Context;

import com.example.tianyi.iphoneassist.di.component.AppComponent;
import com.example.tianyi.iphoneassist.di.component.DaggerAppComponent;
import com.example.tianyi.iphoneassist.di.module.AppModule;
import com.example.tianyi.iphoneassist.di.module.HttpModule;

/**
 * Created by Tianyi on 2017/10/18.
 */

public class AppAplication extends Application {

    private AppComponent appComponent;
    public AppComponent getAppComponent(){
        return appComponent;
    }

    public static AppAplication getAppAplication(Context context){
        return (AppAplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().httpModule(new HttpModule()).appModule(new AppModule(this)).build();
    }
}
