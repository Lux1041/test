package com.example.tianyi.iphoneassist.presenter.contact;

import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.bean.IndexBean;
import com.example.tianyi.iphoneassist.bean.PageBean;
import com.example.tianyi.iphoneassist.ui.BaseView;

import java.util.List;

/**
 * Created by Tianyi on 2017/11/13.
 */

public interface DownLoadContact {
    interface View extends BaseView{
        void showData(List<AppInfo> data);
        void showNoData();
        void showIndexData(IndexBean indexBean);
    }

    interface AppInfoView extends BaseView{
        void showData(PageBean<AppInfo> data);
    }

    interface AppInfoDetailView extends BaseView{
        void showAppInfoDetail(AppInfo appInfo);
    }
}
