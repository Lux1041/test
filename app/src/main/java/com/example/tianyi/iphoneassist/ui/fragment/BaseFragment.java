package com.example.tianyi.iphoneassist.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tianyi.iphoneassist.AppAplication;
import com.example.tianyi.iphoneassist.di.component.AppComponent;
import com.example.tianyi.iphoneassist.presenter.BasePresenter;
import com.example.tianyi.iphoneassist.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Tianyi on 2017/10/20.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView{
    private Unbinder unbinder;

    @Inject
    T mPresenter;
    private View rootView;

    private ProgressDialog mProgressdialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setContentView(), null);
        unbinder = ButterKnife.bind(this, rootView);

        initView();

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupAppComponent(((AppAplication)getActivity().getApplication()).getAppComponent());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void loading() {
        if (mProgressdialog == null || !mProgressdialog.isShowing()) {
            mProgressdialog = new ProgressDialog(getActivity());
            mProgressdialog.show();
        }
    }

    @Override
    public void dimissLoading() {
        if (mProgressdialog != null && mProgressdialog.isShowing()) {
            mProgressdialog.dismiss();
        }
    }

    @Override
    public void showError(String msg) {

    }

    public abstract int setContentView();
    public abstract void initView();
    public abstract void setupAppComponent(AppComponent appComponent);
}
