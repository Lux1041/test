package com.example.tianyi.iphoneassist.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.di.component.AppComponent;
import com.example.tianyi.iphoneassist.ui.adapter.AppManagerFragmentAdapter;

import butterknife.BindView;

/**
 * Created by Tianyi on 2017/11/25.
 */

public class AppDownloadManagerActivity extends BaseActivity {


    @BindView(R.id.mtablayout)
    TabLayout mTablayout;
    @BindView(R.id.mviewpager)
    ViewPager mViewpager;

    @Override
    public int setLayout() {
        return R.layout.activity_app_download_manager;
    }

    @Override
    public void init() {
        AppManagerFragmentAdapter adapter = new AppManagerFragmentAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(adapter);
        mTablayout.setupWithViewPager(mViewpager);
        mViewpager.setOffscreenPageLimit(4);
    }

    @Override
    public void setupAppComponent(AppComponent appComponent) {

    }
}
