package com.example.tianyi.iphoneassist.presenter;

import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.common.rx.RxErrorHandler;
import com.example.tianyi.iphoneassist.common.rx.RxHttpResponseCompose;
import com.example.tianyi.iphoneassist.common.rx.subscriber.ProgressSubscriber;
import com.example.tianyi.iphoneassist.data.AppInfoDetailModule;
import com.example.tianyi.iphoneassist.presenter.contact.DownLoadContact;

import javax.inject.Inject;

/**
 * Created by Tianyi on 2017/11/22.
 */

public class AppInfoDetailPresenter extends BasePresenter<AppInfoDetailModule, DownLoadContact.AppInfoDetailView> {

    private RxErrorHandler rxErrorHandler;
    @Inject
    public AppInfoDetailPresenter(AppInfoDetailModule appInfoDetailModule, DownLoadContact.AppInfoDetailView mView, RxErrorHandler rxErrorHandler) {
        super(appInfoDetailModule, mView);
        this.rxErrorHandler = rxErrorHandler;
    }

    public void requestData(int id){
        moudle.requestData(id).compose(RxHttpResponseCompose.<AppInfo>compatResult())
                .subscribe(new ProgressSubscriber<AppInfo>(mView, rxErrorHandler) {
                    @Override
                    public void onNext(AppInfo appInfo) {
                        mView.showAppInfoDetail(appInfo);
                    }
                });
    }
}
