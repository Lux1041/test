package com.example.tianyi.iphoneassist.di.module;

import com.example.tianyi.iphoneassist.common.rx.RxErrorHandler;
import com.example.tianyi.iphoneassist.http.ApiServer;
import com.example.tianyi.iphoneassist.presenter.LoginPresenter;
import com.example.tianyi.iphoneassist.presenter.contact.LoginContact;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tianyi on 2017/11/20.
 */

@Module
public class LoginModule {

    private LoginContact.View mView;
    public LoginModule(LoginContact.View view){
        mView = view;
    }

    @Provides
    public LoginPresenter provideLoginPresenter(com.example.tianyi.iphoneassist.data.LoginModule module, LoginContact.View view, RxErrorHandler rxErrorHandler){
        return new LoginPresenter(module, view, rxErrorHandler);
    }

    @Provides
    public LoginContact.View provideVIew(){
        return mView;
    }

    @Provides
    public com.example.tianyi.iphoneassist.data.LoginModule provideModule(ApiServer apiServer){
        return new com.example.tianyi.iphoneassist.data.LoginModule(apiServer);
    }




}
