package com.example.tianyi.iphoneassist.ui.fragment;

import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.di.component.AppComponent;
import com.example.tianyi.iphoneassist.presenter.DownLoadPresenter;

/**
 * Created by Tianyi on 2017/11/13.
 */

public class HistoryFragment extends ProgressFragment<DownLoadPresenter> {
    @Override
    public int setRealContent() {
        return R.layout.fragment_history;
    }

    @Override
    public void setupAppComponent(AppComponent appComponent) {

    }

    @Override
    public void initData() {

    }
}
