package com.example.tianyi.iphoneassist.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.bean.Banner;
import com.example.tianyi.iphoneassist.bean.IndexBean;
import com.example.tianyi.iphoneassist.common.imageloader.ImageLoader;
import com.example.tianyi.iphoneassist.ui.widget.BannerLayout;
import com.example.tianyi.iphoneassist.ui.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tianyi on 2017/11/17.
 */

public class IndexMultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int TYPE_BANNER = 1;
    private static final int TYPE_ICON = 2;
    private static final int TYPE_APPS = 3;
    private static final int TYPE_GAMES = 4;

    private Context mContext;
    private LayoutInflater inflater;
    private IndexBean mIndexBean;


    public IndexMultiAdapter(Context context){
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(IndexBean indexBean){
        this.mIndexBean = indexBean;
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        switch (position){
            case 0:
                type = TYPE_BANNER;
                break;
            case 1:
                type = TYPE_ICON;
                break;
            case 2:
                type = TYPE_APPS;
                break;
            case 3:
                type = TYPE_GAMES;
                break;
        }
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewholder = null;
        switch (viewType){
            case TYPE_BANNER:
                View bannerView = inflater.inflate(R.layout.template_banner, parent, false);
                viewholder = new BannerViewHolder(bannerView);
                break;
            case TYPE_ICON:
                View iconView = inflater.inflate(R.layout.template_nav_icon, parent, false);
                viewholder = new IconViewHolder(iconView);
                break;
            case TYPE_APPS:
                View appView = inflater.inflate(R.layout.template_recyleview_with_title, null, false);//parent设置为null时可以自己测试高度
//                View appView = inflater.inflate(resId, parent, false);
                viewholder = new AppViewHolder(appView);
                break;
            case TYPE_GAMES:
                // TODO: 2017/11/18 注意参数中的null对界面的影响
                View gameView = inflater.inflate(R.layout.template_recyleview_with_title, null, false);
                viewholder = new GameViewHolder(gameView);
                break;
        }
        return viewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0){
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            List<Banner> bannerList = mIndexBean.getBanners();
            List<String> urls = new ArrayList<>(bannerList.size());
            for (Banner banner : bannerList){
                urls.add(banner.getThumbnail());
            }
            bannerViewHolder.mBanner.setViewUrls(urls);
        }else if (position == 1){

        }else if (position == 2){

            AppViewHolder appViewHolder = (AppViewHolder) holder;
            List<AppInfo> recommendApps = mIndexBean.getRecommendApps();
            AppInfoAdapter appInfoAdapter = new AppInfoAdapter(recommendApps);
            appViewHolder.mRecyclerView.setAdapter(appInfoAdapter);
            appViewHolder.mText.setText("热门应用");
        }else if (position == 3){
            GameViewHolder gameViewHolder = (GameViewHolder) holder;
            List<AppInfo> gameApps = mIndexBean.getRecommendGames();
            AppInfoAdapter appInfoAdapter = new AppInfoAdapter(gameApps);
            gameViewHolder.mRecyclerView.setAdapter(appInfoAdapter);
            gameViewHolder.mText.setText("热门游戏");
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.banner)
        BannerLayout mBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mBanner.setImageLoader(new ImgLoader());
        }
    }

    class IconViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.layout_hot_app)
        LinearLayout mLayoutHotApp;
        @BindView(R.id.layout_hot_game)
        LinearLayout mLayoutHotGame;
        @BindView(R.id.layout_hot_subject)
        LinearLayout mLayoutHotSubject;

        public IconViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class AppViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.text)
        TextView mText;
        @BindView(R.id.recycler_view)
        RecyclerView mRecyclerView;

        public AppViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            initRecyclerView();
        }

        private void initRecyclerView() {

            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext) {

                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });

            DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL_LIST);

            mRecyclerView.addItemDecoration(itemDecoration);

        }
    }

    class GameViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.text)
        TextView mText;
        @BindView(R.id.recycler_view)
        RecyclerView mRecyclerView;

        public GameViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            initRecyclerView();
        }

        private void initRecyclerView() {

            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext) {

                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });

            DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL_LIST);

            mRecyclerView.addItemDecoration(itemDecoration);

        }
    }


    class ImgLoader implements BannerLayout.ImageLoader {

        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoader.load(path, imageView);
        }
    }

}
