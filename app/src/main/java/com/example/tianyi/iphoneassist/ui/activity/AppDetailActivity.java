package com.example.tianyi.iphoneassist.ui.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.common.util.DensityUtil;
import com.example.tianyi.iphoneassist.di.component.AppComponent;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

import butterknife.BindView;

/**
 * Created by Tianyi on 2017/11/21.
 */

public class AppDetailActivity extends BaseActivity {
    @BindView(R.id.activity_app_detail_imageview)
    ImageView mImageView;

    @Override
    public int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    public void init() {
        RxBus.get().register(this);
    }

    @Subscribe
    public void getView(View view){
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        if (bitmap != null){
            bitmap = Bitmap.createBitmap(bitmap);
            mImageView.setImageBitmap(bitmap);
            view.destroyDrawingCache();

            int statusBarHeight = DensityUtil.getStatusBarH(this);

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mImageView.getLayoutParams();
            params.topMargin = locations[1] - statusBarHeight;
            params.leftMargin = locations[0];
            params.width = view.getWidth();
            params.height = view.getHeight();
            mImageView.setLayoutParams(params);
        }
    }

    @Override
    public void setupAppComponent(AppComponent appComponent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

}
