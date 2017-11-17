package com.example.tianyi.iphoneassist.presenter;

import com.example.tianyi.iphoneassist.ui.BaseView;

/**
 * Created by Tianyi on 2017/11/13.
 */

public class BasePresenter<M,V extends BaseView> {
    protected M moudle;
    protected V mView;

    public BasePresenter(M m, V mView){
        this.moudle = m;
        this.mView = mView;
    }
}
