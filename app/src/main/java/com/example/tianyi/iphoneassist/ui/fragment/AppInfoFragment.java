package com.example.tianyi.iphoneassist.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.common.imageloader.ImageLoader;
import com.example.tianyi.iphoneassist.common.util.DateUtils;
import com.example.tianyi.iphoneassist.di.component.AppComponent;
import com.example.tianyi.iphoneassist.di.component.DaggerAppInfoDetailComponent;
import com.example.tianyi.iphoneassist.di.module.AppInfoDetailModule;
import com.example.tianyi.iphoneassist.http.ApiServer;
import com.example.tianyi.iphoneassist.presenter.AppInfoDetailPresenter;
import com.example.tianyi.iphoneassist.presenter.contact.DownLoadContact;
import com.example.tianyi.iphoneassist.ui.adapter.AppInfoAdapter;
import com.example.tianyi.iphoneassist.ui.adapter.AppInfoAdapter2;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Tianyi on 2017/11/22.
 */

public class AppInfoFragment extends ProgressFragment<AppInfoDetailPresenter> implements DownLoadContact.AppInfoDetailView {
    @BindView(R.id.view_gallery)
    LinearLayout viewGallery;
    @BindView(R.id.expandable_text)
    TextView expandableText;
    @BindView(R.id.expand_collapse)
    ImageButton expandCollapse;
    @BindView(R.id.view_introduction)
    ExpandableTextView viewIntroduction;
    @BindView(R.id.txt_update_time)
    TextView txtUpdateTime;
    @BindView(R.id.txt_version)
    TextView txtVersion;
    @BindView(R.id.txt_apk_size)
    TextView txtApkSize;
    @BindView(R.id.txt_publisher)
    TextView txtPublisher;
    @BindView(R.id.txt_publisher2)
    TextView txtPublisher2;
    @BindView(R.id.recycler_view_same_dev)
    RecyclerView recyclerViewSameDev;
    @BindView(R.id.recycler_view_relate)
    RecyclerView recyclerViewRelate;
    Unbinder unbinder;

    private int appId;
    private LayoutInflater inflater;

    private AppInfoAdapter2 adapter;

   /* public AppInfoFragment(int id){
        this.appId = id;
    }*/

    public void setAppId(int id){
        this.appId = id;
    }

    @Override
    public int setRealContent() {
        return R.layout.fragment_app_info;
    }

    @Override
    public void setupAppComponent(AppComponent appComponent) {
        DaggerAppInfoDetailComponent.builder().appComponent(appComponent)
                .appInfoDetailModule(new AppInfoDetailModule(this))
                .build().inject(this);
    }

    @Override
    public void initData() {
        inflater = LayoutInflater.from(getActivity());
        mPresenter.requestData(appId);
    }

    @Override
    public void showAppInfoDetail(AppInfo appInfo) {
        viewIntroduction.setText(appInfo.getIntroduction());

        String screenShotsStr = appInfo.getScreenshot();
        String[] screenShots = screenShotsStr.split(",");
        for (String image : screenShots){
            String imageUrl = ApiServer.BASE_IMAGE_URL + image;
            ImageView imageview = (ImageView) inflater.inflate(R.layout.template_imageview, viewGallery, false);
            imageview.setAdjustViewBounds(true);
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(480,640);
            imageview.setLayoutParams(layoutParams);
            ImageLoader.load(imageUrl, imageview);
            viewGallery.addView(imageview);
        }

        txtUpdateTime.setText(DateUtils.formatDate(appInfo.getUpdateTime()));
        txtApkSize.setText(appInfo.getApkSize() / 1024 / 1024 + "Mb");
        txtPublisher.setText(appInfo.getPublisherName());
        txtPublisher2.setText(appInfo.getPublisherName());
        txtVersion.setText(appInfo.getVersionName());

        adapter = new AppInfoAdapter2(appInfo.getSameDevAppInfoList());
        recyclerViewSameDev.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewSameDev.setAdapter(adapter);

        adapter = new AppInfoAdapter2(appInfo.getRelateAppInfoList());
        recyclerViewRelate.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewRelate.setAdapter(adapter);
    }
}
