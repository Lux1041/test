package com.example.tianyi.iphoneassist.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.bean.IndexBean;
import com.example.tianyi.iphoneassist.di.component.AppComponent;
import com.example.tianyi.iphoneassist.di.component.DaggerDownLoadComponent;
import com.example.tianyi.iphoneassist.di.module.DownLoadModule;
import com.example.tianyi.iphoneassist.presenter.DownLoadPresenter;
import com.example.tianyi.iphoneassist.presenter.contact.DownLoadContact;
import com.example.tianyi.iphoneassist.ui.adapter.DownAppInfoAdapter;
import com.example.tianyi.iphoneassist.ui.adapter.IndexMultiAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Tianyi on 2017/11/13.
 */

public class DownLoadFragment extends ProgressFragment<DownLoadPresenter> implements DownLoadContact.View {
    @BindView(R.id.fragment_download_recyclerview)
    RecyclerView recyclerView;

    @Override
    public void showData(List<AppInfo> data) {
        DownAppInfoAdapter adapter = new DownAppInfoAdapter(data, getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showNoData() {
        Toast.makeText(getActivity(), "暂时没有数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showIndexData(IndexBean indexBean) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        IndexMultiAdapter adapter = new IndexMultiAdapter(getActivity());
        adapter.setData(indexBean);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int setRealContent() {
        return R.layout.fragment_download;
    }

    @Override
    public void setupAppComponent(AppComponent appComponent) {
        DaggerDownLoadComponent.builder()
                .downLoadModule(new DownLoadModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initData() {
//        mPresenter.getAppInfos();
        mPresenter.getIndexAppInfos();
    }


  /*  @Override
    public int setContentView() {
        return R.layout.fragment_download;
    }

    @Override
    public void initView() {
        mPresenter.getAppInfos();
    }

    @Override
    public void setupAppComponent(AppComponent appComponent) {
        DaggerDownLoadComponent.builder()
                .downLoadModule(new DownLoadModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    private void initRecyclerData(List<AppInfo> data) {
        DownAppInfoAdapter adapter = new DownAppInfoAdapter(data, getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showData(List<AppInfo> data) {
        initRecyclerData(data);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNoData() {
        Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_LONG).show();
    }*/
}
