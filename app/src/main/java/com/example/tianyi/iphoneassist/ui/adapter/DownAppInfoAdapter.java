package com.example.tianyi.iphoneassist.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.http.ApiServer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tianyi on 2017/11/13.
 */

public class DownAppInfoAdapter extends RecyclerView.Adapter<DownAppInfoAdapter.DownAppInfoViewHolder> {

    private List<AppInfo> data = new ArrayList<>();
    private LayoutInflater inflater;
    private Context mContext;

    public DownAppInfoAdapter(List<AppInfo> data, Context context) {
        this.data = data;
        inflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public DownAppInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_for_down_load_appinfo, parent, false);
        return new DownAppInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DownAppInfoViewHolder holder, int position) {
        AppInfo appInfo = data.get(position);
        holder.itemDownLoadName.setText(appInfo.getDisplayName());
        holder.itemDownLoadDesc.setText(appInfo.getBriefShow());
        String icon = ApiServer.BASE_IMAGE_URL + appInfo.getIcon();
        Glide.with(mContext).load(icon).into(holder.itemDownLoadIcon);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DownAppInfoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_down_load_icon)
        ImageView itemDownLoadIcon;
        @BindView(R.id.item_down_load_name)
        TextView itemDownLoadName;
        @BindView(R.id.item_down_load_desc)
        TextView itemDownLoadDesc;
        @BindView(R.id.item_down_load_download)
        TextView itemDownLoadDownload;
        public DownAppInfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
