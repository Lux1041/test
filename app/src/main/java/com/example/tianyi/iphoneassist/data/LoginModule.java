package com.example.tianyi.iphoneassist.data;

import com.example.tianyi.iphoneassist.bean.BaseBean;
import com.example.tianyi.iphoneassist.bean.LoginBean;
import com.example.tianyi.iphoneassist.bean.requestbean.LoginRequestBean;
import com.example.tianyi.iphoneassist.http.ApiServer;

import io.reactivex.Observable;


/**
 * Created by Tianyi on 2017/11/13.
 */

public class LoginModule {

    private ApiServer mApiServer;
    public LoginModule(ApiServer apiServer){
        mApiServer = apiServer;
    }


    public Observable<BaseBean<LoginBean>> login(LoginRequestBean loginRequestBean){
        return mApiServer.login(loginRequestBean);
    }
}
