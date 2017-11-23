package com.example.tianyi.iphoneassist.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tianyi.iphoneassist.AppAplication;
import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.common.imageloader.ImageLoader;
import com.example.tianyi.iphoneassist.common.util.DensityUtil;
import com.example.tianyi.iphoneassist.di.component.AppComponent;
import com.example.tianyi.iphoneassist.http.ApiServer;
import com.example.tianyi.iphoneassist.ui.fragment.AppInfoFragment;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;

/**
 * Created by Tianyi on 2017/11/21.
 */

public class AppDetailActivity extends BaseActivity {
    @BindView(R.id.activity_app_detail_framelayout)
    FrameLayout mFrameLayout;
    @BindView(R.id.activity_app_detail_view)
    View mBackgroundView;
    @BindView(R.id.activity_app_detail_icon)
    ImageView mAppIcon;
    @BindView(R.id.activity_app_detail_name)
    TextView mAppName;
    @BindView(R.id.activity_app_detail_content)
    CoordinatorLayout mContentViewLayout;

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar mToolBar;

    private AppInfo appinfo;

    @Override
    public int setLayout() {
        RxBus.get().register(this);
        return R.layout.activity_app_detail;
    }

    @Override
    public void init() {
        mToolBar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_android_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000))
        );
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        View view = ((AppAplication)getApplication()).getItemView();
        if (view != null){
            int[] locations = new int[2];
            view.getLocationOnScreen(locations);

            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            Bitmap bitmap = view.getDrawingCache();

            if (bitmap != null) {
                bitmap = Bitmap.createBitmap(bitmap);
                mBackgroundView.setBackgroundDrawable(new BitmapDrawable(bitmap));
                view.destroyDrawingCache();

                int statusBarHeight = DensityUtil.getStatusBarH(this);

                LinearLayout.MarginLayoutParams marginLayoutParams = new LinearLayout.MarginLayoutParams(mBackgroundView.getLayoutParams());
                marginLayoutParams.topMargin = locations[1] - statusBarHeight;
                marginLayoutParams.leftMargin = locations[0];
                marginLayoutParams.width = view.getWidth();
                marginLayoutParams.height = view.getHeight();
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(marginLayoutParams);
                mBackgroundView.setLayoutParams(lp);

                expandFramelayout();
//            initAppInfo();
            }else{
                finish();
            }
        }else{
            finish();
        }
    }

    //    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Subscribe
    public void getView(View view) {
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        if (bitmap != null) {
            bitmap = Bitmap.createBitmap(bitmap);
            mBackgroundView.setBackgroundDrawable(new BitmapDrawable(bitmap));
            view.destroyDrawingCache();

            int statusBarHeight = DensityUtil.getStatusBarH(this);

            LinearLayout.MarginLayoutParams marginLayoutParams = new LinearLayout.MarginLayoutParams(mBackgroundView.getLayoutParams());
            marginLayoutParams.topMargin = locations[1] - statusBarHeight;
            marginLayoutParams.leftMargin = locations[0];
            marginLayoutParams.width = view.getWidth();
            marginLayoutParams.height = view.getHeight();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(marginLayoutParams);
            mBackgroundView.setLayoutParams(lp);

            expandFramelayout();
//            initAppInfo();
        }else{
            finish();
        }
    }

    private void initAppInfo() {
        appinfo = (AppInfo) getIntent().getSerializableExtra("appInfo");
        ImageLoader.load(ApiServer.BASE_IMAGE_URL + appinfo.getIcon(), mAppIcon);
        mAppName.setText(appinfo.getDisplayName());

        AppInfoFragment appInfoFragment = new AppInfoFragment();
        appInfoFragment.setAppId(appinfo.getId());
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.activity_app_detail_framelayout, appInfoFragment)
                .commit();
    }

    @Override
    public void setupAppComponent(AppComponent appComponent) {

    }

    private void expandFramelayout() {
        final int h = DensityUtil.getScreenH(this);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mBackgroundView, "scaleY", 1, (float) h);
        animator.setDuration(500);
        animator.setStartDelay(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mBackgroundView.setVisibility(View.GONE);
                mContentViewLayout.setVisibility(View.VISIBLE);
                initAppInfo();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mBackgroundView.setBackgroundColor(Color.WHITE);
            }
        });

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                float alpha = value / h;
                mBackgroundView.setAlpha(1 - alpha);
            }
        });
        animator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}
