package com.example.tianyi.iphoneassist.common.rx.subscriber;

import com.example.tianyi.iphoneassist.common.exception.BaseException;
import com.example.tianyi.iphoneassist.common.rx.RxErrorHandler;
import com.example.tianyi.iphoneassist.ui.BaseView;
import com.example.tianyi.iphoneassist.ui.fragment.DownLoadFragment;

/**
 * Created by Tianyi on 2017/11/16.
 */

public abstract class ProgressSubscriber<T> extends ErrorSubscriber<T> {

    private BaseView mView;

    public ProgressSubscriber(BaseView view, RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
        this.mView = view;
    }

    @Override
    public void onCompleted() {
        mView.dimissLoading();
    }

    @Override
    public void onError(Throwable e) {
//        super.onError(e);
        BaseException exception = rxErrorHandler.handlerError(e);
        mView.dimissLoading();
        if (mView instanceof DownLoadFragment) {
            ((DownLoadFragment) mView).showError(exception.getDisplayMessage());
        }
    }

    @Override
    public void onStart() {
        mView.loading();
    }
}
