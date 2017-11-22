package com.example.tianyi.iphoneassist.di.module;

import com.example.tianyi.iphoneassist.http.ApiServer;
import com.example.tianyi.iphoneassist.presenter.contact.DownLoadContact;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tianyi on 2017/11/19.
 */

@Module
public class AppInfoDetailModule {

    private DownLoadContact.AppInfoDetailView mView;
    public AppInfoDetailModule(DownLoadContact.AppInfoDetailView view){
        this.mView = view;
    }

    @Provides
    public com.example.tianyi.iphoneassist.data.AppInfoDetailModule provideAppInfoDetailModule(ApiServer apiServer){
        return new com.example.tianyi.iphoneassist.data.AppInfoDetailModule(apiServer);
    }

    @Provides
    public DownLoadContact.AppInfoDetailView provideBaseView(){
        return mView;
    }

//    @Provides
//    public AppinfoPresenter providePresenter(DownLoadModule module, DownLoadContact.AppInfoView view, RxErrorHandler rxErrorHandler){
//        return new AppinfoPresenter(module, view, rxErrorHandler);
//    }
}
