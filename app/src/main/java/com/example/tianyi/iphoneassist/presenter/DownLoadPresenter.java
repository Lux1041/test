package com.example.tianyi.iphoneassist.presenter;

import android.Manifest;

import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.bean.IndexBean;
import com.example.tianyi.iphoneassist.bean.PageBean;
import com.example.tianyi.iphoneassist.common.rx.RxErrorHandler;
import com.example.tianyi.iphoneassist.common.rx.RxHttpResponseCompose;
import com.example.tianyi.iphoneassist.common.rx.subscriber.ProgressSubscriber;
import com.example.tianyi.iphoneassist.data.DownLoadModule;
import com.example.tianyi.iphoneassist.presenter.contact.DownLoadContact;
import com.example.tianyi.iphoneassist.ui.fragment.DownLoadFragment;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Tianyi on 2017/11/13.
 */

public class DownLoadPresenter extends BasePresenter<DownLoadModule, DownLoadContact.View> {

    private RxErrorHandler rxErrorHandler;

    @Inject
    public DownLoadPresenter(DownLoadModule downLoadModule, DownLoadContact.View mView, RxErrorHandler rxErrorHandler) {//必须和提供出来的类一样，不能是其子类或实现
        super(downLoadModule, mView);
        this.rxErrorHandler = rxErrorHandler;

    }

    public void getAppInfos(){

        RxPermissions rxPermissions = new RxPermissions(((DownLoadFragment)mView).getActivity());

        rxPermissions.request(Manifest.permission.READ_PHONE_STATE).flatMap(new Func1<Boolean, Observable<PageBean<AppInfo>>>() {
            @Override
            public Observable<PageBean<AppInfo>> call(Boolean aBoolean) {
                if (aBoolean) {
                    return moudle.getApps().compose(RxHttpResponseCompose.<PageBean<AppInfo>>compatResult());
                }else{
//                    mView.requestPermissionFailed();
                    return Observable.error(null);
                }
            }
        }).subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mView, rxErrorHandler) {
            @Override
            public void onNext(PageBean<AppInfo> response) {
                if (response != null){
                    if (response.getStatus() == 1){
                        if (response.getDatas() == null || response.getDatas().size() == 0){
                            mView.showNoData();
                        }else{
                            mView.showData(response.getDatas());
                        }
                    }else{
                        mView.showNoData();
                    }
                }else{
                    mView.showNoData();
                }
            }
        });

       /* moudle.getApps()
                .compose(RxHttpResponseCompose.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mView, rxErrorHandler) {
                    @Override
                    public void onNext(PageBean<AppInfo> response) {
                        if (response != null){
                            if (response.getStatus() == 1){
                                if (response.getDatas() == null || response.getDatas().size() == 0){
                                    mView.showNoData();
                                }else{
                                    mView.showData(response.getDatas());
                                }
                            }else{
                                mView.showNoData();
                            }
                        }else{
                            mView.showNoData();
                        }
                    }
                });*/

       /* mView.loading();
        moudle.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                if (response != null){
                    if (response.body().getStatus() == 1){
                        if (response.body().getDatas() == null || response.body().getDatas().size() == 0){
                            mView.showNoData();
                        }else{
                            mView.showData(response.body().getDatas());
                        }
                        mView.dimissLoading();
                    }else{
                        mView.showNoData();
                        mView.dimissLoading();
                    }
                }
            }


             @Override
                    public void onStart() {
                        super.onStart();
                        mView.loading();
                    }

                    @Override
                    public void onCompleted() {
                        mView.dimissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dimissLoading();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(PageBean<AppInfo> response) {
                        if (response != null){
                            if (response.getStatus() == 1){
                                if (response.getDatas() == null || response.getDatas().size() == 0){
                                    mView.showNoData();
                                }else{
                                    mView.showData(response.getDatas());
                                }
                            }else{
                                mView.showNoData();
                            }
                        }else{
                            mView.showNoData();
                        }
                    }


            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                mView.dimissLoading();
                mView.showError(t.toString());
            }
        });*/
    }

    public void getIndexAppInfos(){

        RxPermissions rxPermissions = new RxPermissions(((DownLoadFragment)mView).getActivity());
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE).flatMap(new Func1<Boolean, Observable<IndexBean>>() {
            @Override
            public Observable<IndexBean> call(Boolean aBoolean) {

                if (aBoolean){
                    return moudle.getIndexAppInfos().compose(RxHttpResponseCompose.<IndexBean>compatResult());
                }else{
                    return Observable.error(null);
                }
            }
        }).subscribe(new ProgressSubscriber<IndexBean>(mView, rxErrorHandler) {
            @Override
            public void onNext(IndexBean indexBean) {
                mView.showIndexData(indexBean);
            }
        });

      /*  moudle.getIndexAppInfos()
                .compose(RxHttpResponseCompose.<IndexBean>compatResult())
                .subscribe(new ProgressSubscriber<IndexBean>(mView, rxErrorHandler) {
                    @Override
                    public void onNext(IndexBean indexBean) {
                        mView.showIndexData(indexBean);
                    }
                });*/
    }
}
