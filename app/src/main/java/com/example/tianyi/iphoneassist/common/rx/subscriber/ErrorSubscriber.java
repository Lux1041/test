package com.example.tianyi.iphoneassist.common.rx.subscriber;

import com.example.tianyi.iphoneassist.common.exception.BaseException;
import com.example.tianyi.iphoneassist.common.rx.RxErrorHandler;

/**
 * Created by Tianyi on 2017/11/16.
 */

public abstract class ErrorSubscriber<T> extends DefaultSubscriber<T> {


    protected RxErrorHandler rxErrorHandler;
    public ErrorSubscriber(RxErrorHandler rxErrorHandler){
        this.rxErrorHandler = rxErrorHandler;
    }

    @Override
    public void onError(Throwable e) {
        BaseException exception = rxErrorHandler.handlerError(e);

        rxErrorHandler.showError(exception);
    }
}
