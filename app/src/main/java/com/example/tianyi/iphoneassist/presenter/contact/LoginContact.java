package com.example.tianyi.iphoneassist.presenter.contact;

import com.example.tianyi.iphoneassist.bean.LoginBean;
import com.example.tianyi.iphoneassist.ui.BaseView;

/**
 * Created by Tianyi on 2017/11/20.
 */

public interface LoginContact {
    interface View extends BaseView{
        void loginSuccess(LoginBean loginBean);
        void loginFail(String error);

        void checkPhoneNumFailed();
        void checkPhoneNumSuccess();
    }
}
