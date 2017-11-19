package com.example.tianyi.iphoneassist.di.module;

import com.example.tianyi.iphoneassist.common.rx.RxErrorHandler;
import com.example.tianyi.iphoneassist.data.DownLoadModule;
import com.example.tianyi.iphoneassist.http.ApiServer;
import com.example.tianyi.iphoneassist.presenter.AppinfoPresenter;
import com.example.tianyi.iphoneassist.presenter.contact.DownLoadContact;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tianyi on 2017/11/19.
 */

@Module
public class AppInfoModule {

    private DownLoadContact.AppInfoView mView;
    public AppInfoModule(DownLoadContact.AppInfoView view){
        this.mView = view;
    }

    @Provides
    public com.example.tianyi.iphoneassist.data.DownLoadModule provideDownLoaderModuel(ApiServer apiServer){
        return new DownLoadModule(apiServer);
    }

    @Provides
    public DownLoadContact.AppInfoView provideBaseView(){
        return mView;
    }

    @Provides
    public AppinfoPresenter providePresenter(DownLoadModule module, DownLoadContact.AppInfoView view, RxErrorHandler rxErrorHandler){
        return new AppinfoPresenter(module, view, rxErrorHandler);
    }
}
