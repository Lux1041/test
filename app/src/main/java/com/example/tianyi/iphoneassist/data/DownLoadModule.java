package com.example.tianyi.iphoneassist.data;

import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.bean.BaseBean;
import com.example.tianyi.iphoneassist.bean.IndexBean;
import com.example.tianyi.iphoneassist.bean.PageBean;
import com.example.tianyi.iphoneassist.http.ApiServer;

import rx.Observable;

/**
 * Created by Tianyi on 2017/11/13.
 */

public class DownLoadModule {

    private ApiServer mApiServer;
    public DownLoadModule(ApiServer apiServer){
        mApiServer = apiServer;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps(){

        String jsonParams = "{'page':0}";
        return mApiServer.getDownLoadAppInfo2(jsonParams);
    }

    public Observable<BaseBean<IndexBean>> getIndexAppInfos(){
        return mApiServer.index();
    }
}
