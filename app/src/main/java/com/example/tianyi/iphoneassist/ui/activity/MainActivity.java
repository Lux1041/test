package com.example.tianyi.iphoneassist.ui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.bean.LoginBean;
import com.example.tianyi.iphoneassist.common.imageloader.GlideCircleTransform;
import com.example.tianyi.iphoneassist.di.component.AppComponent;
import com.example.tianyi.iphoneassist.ui.adapter.MainFragmentAdapter;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.main_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.main_activity_toolbar)
    Toolbar mToolBar;
    @BindView(R.id.main_activity_drawerlayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_activity_navigationview)
    NavigationView mNavigationView;

    private View headerView;
    private ImageView mUserHeadView;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {

        RxBus.get().register(this);

        mToolBar.inflateMenu(R.menu.tool_bar_menu);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);//使得箭头联动

        MainFragmentAdapter adapter = new MainFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(4);

        headerView = mNavigationView.getHeaderView(0);
        mUserHeadView = (ImageView) headerView.findViewById(R.id.header_imageview);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public void setupAppComponent(AppComponent appComponent) {

    }

    @Subscribe
    public void getUserFromLogin(LoginBean loginBean){
        Glide.with(this).load(loginBean.getUser().getLogo_url()).transform(new GlideCircleTransform(this)).into(mUserHeadView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}
