package com.example.tianyi.iphoneassist.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.ui.widget.DividerItemDecoration;

import butterknife.BindView;

/**
 * Created by Tianyi on 2017/11/27.
 */

public abstract class BaseAppDownloadManagerFragment extends BaseFragment {

    @BindView(R.id.fragment_download_recyclerview)
    RecyclerView mRecyclerView;

    @Override
    public int setContentView() {
        return R.layout.fragment_download;
    }

    @Override
    public void initView() {

        requestData();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);
        setRecyclerviewAdapter();

    }

    protected abstract void requestData();

    protected abstract void setRecyclerviewAdapter();
}
