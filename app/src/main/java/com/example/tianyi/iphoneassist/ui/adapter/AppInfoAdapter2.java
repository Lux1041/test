package com.example.tianyi.iphoneassist.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.bean.AppInfo;

import java.util.List;

/**
 * Created by Tianyi on 2017/11/18.
 */

public class AppInfoAdapter2 extends BaseQuickAdapter<AppInfo, BaseViewHolder> {

    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";


    public AppInfoAdapter2(List<AppInfo> data) {
        super(R.layout.template_appinfo2, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        /*ImageLoader.load(baseImgUrl + item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name, item.getDisplayName())
                .setText(R.id.txt_position, String.valueOf(item.getPosition() + 1))
                .setText(R.id.txt_category, item.getLevel1CategoryName())
                .setText(R.id.txt_brief, item.getBriefShow());*/

    }
}
