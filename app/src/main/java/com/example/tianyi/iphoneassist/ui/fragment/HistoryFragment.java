package com.example.tianyi.iphoneassist.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.bean.PageBean;
import com.example.tianyi.iphoneassist.di.component.AppComponent;
import com.example.tianyi.iphoneassist.di.component.DaggerAppInfoComponent;
import com.example.tianyi.iphoneassist.di.module.AppInfoModule;
import com.example.tianyi.iphoneassist.presenter.AppinfoPresenter;
import com.example.tianyi.iphoneassist.presenter.contact.DownLoadContact;
import com.example.tianyi.iphoneassist.ui.adapter.AppInfoAdapter;
import com.example.tianyi.iphoneassist.ui.widget.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Tianyi on 2017/11/13.
 */

public class HistoryFragment extends ProgressFragment<AppinfoPresenter> implements DownLoadContact.AppInfoView, BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.fragment_history_recyclerivew)
    RecyclerView mRecyclerView;

    private int page = 0;
    private AppInfoAdapter mAdapter;

    @Override
    public int setRealContent() {
        return R.layout.fragment_history;
    }

    @Override
    public void setupAppComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initData() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);
        mPresenter.requestData(page);
        mAdapter = new AppInfoAdapter(new ArrayList<AppInfo>());
        mAdapter.setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showData(PageBean<AppInfo> data) {
        mAdapter.addData(data.getDatas());
        if (data.isHasMore()) {
            page ++;
        }
        mAdapter.setEnableLoadMore(data.isHasMore());
    }

    public void onLoadComplete(){
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.requestData(page);
    }
}
