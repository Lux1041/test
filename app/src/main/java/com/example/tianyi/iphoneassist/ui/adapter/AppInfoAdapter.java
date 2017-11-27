package com.example.tianyi.iphoneassist.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.common.imageloader.ImageLoader;
import com.example.tianyi.iphoneassist.http.ApiServer;
import com.example.tianyi.iphoneassist.ui.widget.DownLoadButtonController;
import com.example.tianyi.iphoneassist.ui.widget.DownloadProgressButton;

import java.util.List;

import zlc.season.rxdownload2.RxDownload;

/**
 * Created by Tianyi on 2017/11/18.
 */

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo, BaseViewHolder> {

    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    private ApiServer apiServer;
    private RxDownload rxDownload;

    public AppInfoAdapter(List<AppInfo> data) {
        super(R.layout.template_appinfo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        ImageLoader.load(baseImgUrl + item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name, item.getDisplayName())
                .setText(R.id.txt_position, String.valueOf(item.getPosition() + 1))
                .setText(R.id.txt_category, item.getLevel1CategoryName())
                .setText(R.id.txt_brief, item.getBriefShow());

        if (rxDownload != null){
            helper.addOnClickListener(R.id.btn_download);
            DownLoadButtonController buttonController = new DownLoadButtonController(rxDownload, apiServer);
            buttonController.handleDownLoadButtonStatus(mContext, (DownloadProgressButton) helper.getView(R.id.btn_download), item);
        }
    }

    public void setApiServer(ApiServer apiserver){
        this.apiServer = apiserver;
    }

    public void setRxDownload(RxDownload rxDownload){
        this.rxDownload = rxDownload;
    }
}
