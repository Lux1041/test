package com.example.tianyi.iphoneassist.presenter;

import com.example.tianyi.iphoneassist.bean.LoginBean;
import com.example.tianyi.iphoneassist.bean.requestbean.LoginRequestBean;
import com.example.tianyi.iphoneassist.common.Constant;
import com.example.tianyi.iphoneassist.common.rx.RxErrorHandler;
import com.example.tianyi.iphoneassist.common.rx.RxHttpResponseCompose;
import com.example.tianyi.iphoneassist.common.rx.subscriber.ErrorSubscriber;
import com.example.tianyi.iphoneassist.common.util.ACache;
import com.example.tianyi.iphoneassist.common.util.VerificationUtils;
import com.example.tianyi.iphoneassist.data.LoginModule;
import com.example.tianyi.iphoneassist.presenter.contact.LoginContact;
import com.example.tianyi.iphoneassist.ui.activity.LoginActivity;
import com.hwangjr.rxbus.RxBus;

/**
 * Created by Tianyi on 2017/11/20.
 */

public class LoginPresenter extends BasePresenter<LoginModule, LoginContact.View> {
    private RxErrorHandler rxErrorHandler;
    public LoginPresenter(LoginModule loginModule, LoginContact.View mView, RxErrorHandler rxErrorHandler) {
        super(loginModule, mView);
        this.rxErrorHandler = rxErrorHandler;
    }

    public void login(String mobile, String password){

        if (!VerificationUtils.matcherPhoneNum(mobile)) {
            mView.checkPhoneNumFailed();
            return;
        }else{
            mView.checkPhoneNumSuccess();
        }

        LoginRequestBean bean = new LoginRequestBean();
        bean.setEmail(mobile);
        bean.setPassword(password);

        moudle.login(bean)
                .compose(RxHttpResponseCompose.<LoginBean>compatResult())
                .subscribe(new ErrorSubscriber<LoginBean>(rxErrorHandler) {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.loginSuccess(loginBean);
                        ACache aCache = ACache.get((LoginActivity)mView);
                        aCache.put(Constant.TOKEN, loginBean.getToken());
                        aCache.put(Constant.USER, loginBean.getUser());

                        RxBus.get().post(loginBean);
                    }
                });
    }
}
