package com.example.tianyi.iphoneassist.presenter;

import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.bean.PageBean;
import com.example.tianyi.iphoneassist.common.rx.RxErrorHandler;
import com.example.tianyi.iphoneassist.common.rx.RxHttpResponseCompose;
import com.example.tianyi.iphoneassist.common.rx.subscriber.ErrorSubscriber;
import com.example.tianyi.iphoneassist.common.rx.subscriber.ProgressSubscriber;
import com.example.tianyi.iphoneassist.data.DownLoadModule;
import com.example.tianyi.iphoneassist.presenter.contact.DownLoadContact;
import com.example.tianyi.iphoneassist.ui.fragment.HistoryFragment;

import javax.inject.Inject;

/**
 * Created by Tianyi on 2017/11/19.
 */

public class AppinfoPresenter extends BasePresenter<DownLoadModule, DownLoadContact.AppInfoView> {

    private RxErrorHandler rxErrorHandler;
    @Inject
    public AppinfoPresenter(DownLoadModule downLoadModule, DownLoadContact.AppInfoView mView, RxErrorHandler rxErrorHandler) {
        super(downLoadModule, mView);
        this.rxErrorHandler = rxErrorHandler;
    }


    public void requestData(int page){

        if (page == 0){
            moudle.getAppInfos(page).compose(RxHttpResponseCompose.<PageBean<AppInfo>>compatResult())
                    .subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mView, rxErrorHandler) {
                        @Override
                        public void onNext(PageBean<AppInfo> appInfoPageBean) {
                            mView.showData(appInfoPageBean);
                        }

                       /* @Override
                        public void onCompleted() {
                            super.onCompleted();
                            ((HistoryFragment)mView).onLoadComplete();//这行代码必须要的，不然没有加载更多的功能
                        }*/
                    });
        }else{
            moudle.getAppInfos(page)
                    .compose(RxHttpResponseCompose.<PageBean<AppInfo>>compatResult())
                    .subscribe(new ErrorSubscriber<PageBean<AppInfo>>(rxErrorHandler) {
                        @Override
                        public void onCompleted() {
                            ((HistoryFragment)mView).onLoadComplete();
                        }

                        @Override
                        public void onNext(PageBean<AppInfo> appInfoPageBean) {
                            mView.showData(appInfoPageBean);
                        }
                    });
        }
    }
}
