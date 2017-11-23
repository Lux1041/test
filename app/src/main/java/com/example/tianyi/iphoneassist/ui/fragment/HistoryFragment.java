package com.example.tianyi.iphoneassist.ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.tianyi.iphoneassist.AppAplication;
import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.bean.PageBean;
import com.example.tianyi.iphoneassist.di.component.AppComponent;
import com.example.tianyi.iphoneassist.di.component.DaggerAppInfoComponent;
import com.example.tianyi.iphoneassist.di.module.AppInfoModule;
import com.example.tianyi.iphoneassist.presenter.AppinfoPresenter;
import com.example.tianyi.iphoneassist.presenter.contact.DownLoadContact;
import com.example.tianyi.iphoneassist.ui.activity.AppDetailActivity;
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

    private Handler handler = new Handler();

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

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                AppInfo appinfo = (AppInfo) adapter.getData().get(position);
                jumpActivity(view, appinfo);
            }
        });
    }

    private void jumpActivity(final View view, AppInfo appInfo) {
       /* handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RxBus.get().post(view);
            }
        }, 100);*/
        ((AppAplication)getActivity().getApplication()).setItemView(view);
        Intent intent = new Intent(getActivity(), AppDetailActivity.class);
        intent.putExtra("appInfo", appInfo);
        startActivity(intent);
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
