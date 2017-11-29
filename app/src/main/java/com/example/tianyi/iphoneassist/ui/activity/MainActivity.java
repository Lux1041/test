package com.example.tianyi.iphoneassist.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.bean.LoginBean;
import com.example.tianyi.iphoneassist.common.Constant;
import com.example.tianyi.iphoneassist.common.imageloader.GlideCircleTransform;
import com.example.tianyi.iphoneassist.common.rx.RxBus;
import com.example.tianyi.iphoneassist.common.util.ACache;
import com.example.tianyi.iphoneassist.di.component.AppComponent;
import com.example.tianyi.iphoneassist.ui.adapter.MainFragmentAdapter;
import com.example.tianyi.iphoneassist.ui.widget.BadgeActionProvider;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

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

//        PermissionUtil.requestPermisson(this, Manifest.permission.READ_PHONE_STATE).subscribe();

//        RxBus.get().register(this);
        RxBus.getDefault().toObservable(LoginBean.class).subscribe(new Consumer<LoginBean>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull LoginBean loginBean) throws Exception {
                getUserFromLogin(loginBean);
            }
        });


        mToolBar.inflateMenu(R.menu.tool_bar_menu);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);//使得箭头联动

        MenuItem menuItem = mToolBar.getMenu().findItem(R.id.tool_bar_download);
        BadgeActionProvider badgeActionProvider = (BadgeActionProvider) MenuItemCompat.getActionProvider(menuItem);
        badgeActionProvider.setNumber(10);

        badgeActionProvider.setOnBadgeActionProviderClickListener(new BadgeActionProvider.OnBadgeActionProviderClickListener() {
            @Override
            public void onBadgeActionProviderClicklistener(View view) {
                Toast.makeText(MainActivity.this, "点击了下载按钮", Toast.LENGTH_SHORT).show();
            }
        });

        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tool_bar_search:
//                        Toast.makeText(MainActivity.this, "点击了搜索按钮", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, SearchActivity.class));
                        break;
                }
                return false;
            }
        });


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

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu_logout:
                        loginout();
                        break;
                    case R.id.menu_download_manager:
                        startActivity(new Intent(MainActivity.this, AppDownloadManagerActivity.class));
                        break;
                }

                return false;
            }
        });


    }

    private void loginout() {
        ACache aCache = ACache.get(this);
        aCache.put(Constant.TOKEN, "");
        aCache.put(Constant.USER, "");
        Glide.with(this).load(R.mipmap.ic_launcher).into(mUserHeadView);
        mUserHeadView.setEnabled(true);
    }

    @Override
    public void setupAppComponent(AppComponent appComponent) {

    }

    public void getUserFromLogin(LoginBean loginBean){
        Glide.with(this).load(loginBean.getUser().getLogo_url()).transform(new GlideCircleTransform(this)).into(mUserHeadView);
        mUserHeadView.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
