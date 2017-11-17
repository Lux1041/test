package com.example.tianyi.iphoneassist.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.tianyi.iphoneassist.AppAplication;
import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.di.component.AppComponent;
import com.example.tianyi.iphoneassist.presenter.BasePresenter;
import com.example.tianyi.iphoneassist.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Tianyi on 2017/11/16.
 */

public abstract class ProgressFragment<T extends BasePresenter> extends Fragment implements BaseView{

    private FrameLayout rootView;
    private FrameLayout loadingView;
    private FrameLayout emptyView;
    private FrameLayout contentView;
    private Unbinder unbind;

    @Inject
    T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupAppComponent(((AppAplication)getActivity().getApplication()).getAppComponent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (FrameLayout) inflater.inflate(R.layout.fragment_progress, null);
        loadingView = (FrameLayout) rootView.findViewById(R.id.progress_fragment_loading_layout);
        emptyView = (FrameLayout) rootView.findViewById(R.id.progress_fragment_empty_layout);
        contentView = (FrameLayout) rootView.findViewById(R.id.progress_fragment_content_layout);
        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEmptyViewClick();
            }
        });
        showLoadingView();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LayoutInflater.from(getActivity()).inflate(setRealContent(), contentView, true);
        unbind = ButterKnife.bind(this, contentView);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbind != Unbinder.EMPTY) {
            unbind.unbind();
        }
    }

    /**
     * empty view click
     * */
    public void onEmptyViewClick(){

    }

    public void showLoadingView(){
        selectedItemViewShow(R.id.progress_fragment_loading_layout);
    }

    public void showContentView(){
        selectedItemViewShow(R.id.progress_fragment_content_layout);
    }

    public void showEmptyView(String msg){
        selectedItemViewShow(R.id.progress_fragment_empty_layout);
        ((TextView)emptyView.getChildAt(0)).setText(msg);
    }

    public void selectedItemViewShow(int id){
        for (int i = 0; i < rootView.getChildCount(); i ++){
            if (rootView.getChildAt(i).getId() == id) {
                rootView.getChildAt(i).setVisibility(View.VISIBLE);
            }else{
                rootView.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void loading() {
        showLoadingView();
    }

    @Override
    public void dimissLoading() {
        showContentView();
    }

    @Override
    public void showError(String msg) {
        showEmptyView(msg);
    }

    public abstract int setRealContent();
    public abstract void setupAppComponent(AppComponent appComponent);
    public abstract void initData();
}
