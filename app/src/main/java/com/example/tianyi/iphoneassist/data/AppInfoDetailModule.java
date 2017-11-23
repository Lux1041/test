package com.example.tianyi.iphoneassist.data;

import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.bean.BaseBean;
import com.example.tianyi.iphoneassist.http.ApiServer;

import io.reactivex.Observable;


/**
 * Created by Tianyi on 2017/11/22.
 */

public class AppInfoDetailModule {

    private ApiServer apiServer;
    public AppInfoDetailModule(ApiServer apiServer){
        this.apiServer = apiServer;
    }

    public Observable<BaseBean<AppInfo>> requestData(int id){
        return apiServer.getAppDetail(id);
    }
}
