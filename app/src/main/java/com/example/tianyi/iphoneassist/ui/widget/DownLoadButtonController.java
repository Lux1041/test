package com.example.tianyi.iphoneassist.ui.widget;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.common.util.AppUtils;
import com.jakewharton.rxbinding2.view.RxView;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;

/**
 * Created by Tianyi on 2017/11/24.
 */

public class DownLoadButtonController {

    public static final String BASE_DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    private RxDownload rxDownload;

    public DownLoadButtonController(RxDownload rxDownload){
        this.rxDownload = rxDownload;
    }

    public void handleDownLoadButtonStatus(Context context, final DownloadProgressButton btn, final AppInfo appInfo){

        isDownLoadApk(context, appInfo)
                .flatMap(new Function<DownloadEvent, ObservableSource<DownloadEvent>>() {
                    @Override
                    public ObservableSource<DownloadEvent> apply(@NonNull DownloadEvent downloadEvent) throws Exception {

                        if (downloadEvent.getFlag() == DownloadFlag.UN_INSTALL) {
                            return isDownFileExists(appInfo);
                        }else{
                            return Observable.just(downloadEvent);
                        }
                    }
        })
                .flatMap(new Function<DownloadEvent, ObservableSource<DownloadEvent>>() {
                    @Override
                    public ObservableSource<DownloadEvent> apply(@NonNull DownloadEvent downloadEvent) throws Exception {
                        if (downloadEvent.getFlag() == DownloadFlag.FILE_EXIST){
                            return isApkFileDownLoadTotal(appInfo);
                        }else{
                            return Observable.just(downloadEvent);
                        }
                    }
        })
                .subscribe(new Consumer<DownloadEvent>() {
                    @Override
                    public void accept(@NonNull DownloadEvent downloadEvent) throws Exception {

                        bindButton(btn, downloadEvent.getFlag(), appInfo);

                        switch (downloadEvent.getFlag()){

                            case DownloadFlag.INSTALLED:
                                btn.setText("运行");
                                break;
                            case DownloadFlag.NORMAL:
                                btn.setText("未安装");
                                break;
                            case DownloadFlag.COMPLETED:
                                btn.setText("待安装");
                                break;
                            case DownloadFlag.PAUSED:
                                btn.setText("已暂停");
                                break;
                        }
                    }
        });

    }

    private void bindButton(final DownloadProgressButton btn, final int flag, final AppInfo appInfo) {
        RxView.clicks(btn).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                switch (flag){
                    case DownloadFlag.INSTALLED:
                        AppUtils.runApp(btn.getContext(), appInfo.getPackageName());
                        break;
                    case DownloadFlag.NORMAL:
                    case DownloadFlag.PAUSED:
//                        rxDownload.download()
                        break;
                    case DownloadFlag.COMPLETED:
                        AppUtils.installApk(btn.getContext(), BASE_DOWNLOAD_PATH + File.separator + appInfo.getReleaseKeyHash());
                        break;
                }
            }
        });
    }

    //判断app是否已安装
    public Observable<DownloadEvent> isDownLoadApk(Context context, AppInfo appInfo){
        String apkPackageName = appInfo.getPackageName();
        DownloadEvent event = new DownloadEvent();
        if (AppUtils.isInstalled(context, apkPackageName)) {
            event.setFlag(DownloadFlag.INSTALLED);
        }else{
            event.setFlag(DownloadFlag.UN_INSTALL);
        }
        return Observable.just(event);
    }

    //判断app安装文件是否存在
    public Observable<DownloadEvent> isDownFileExists(AppInfo appInfo){

        String path = BASE_DOWNLOAD_PATH + File.separator + appInfo.getReleaseKeyHash() + ".apk";

        if (appInfo.getDisplayName().equals("今日头条")){
            Log.e("tag", "PATH = " + path);
        }

        File apkFile = new File(path);

        DownloadEvent event = new DownloadEvent();

        if (apkFile.exists()) {
            event.setFlag(DownloadFlag.FILE_EXIST);
        }else{
            event.setFlag(DownloadFlag.NORMAL);
        }
        return Observable.just(event);
    }

    //判断下载的apk文件是否完整
    public Observable<DownloadEvent> isApkFileDownLoadTotal(AppInfo appInfo){
        String path = BASE_DOWNLOAD_PATH + File.separator + appInfo.getReleaseKeyHash();

        File file = new File(path);

        DownloadEvent event = new DownloadEvent();

        if (file.getUsableSpace() == appInfo.getApkSize()) {
            event.setFlag(DownloadFlag.COMPLETED);
        }else{
            event.setFlag(DownloadFlag.PAUSED);
        }

        return Observable.just(event);
    }

    //判断是否正在下载
    public Observable<DownloadEvent> isRunningDownLoad(AppInfo appInfo){

        DownloadEvent event = new DownloadEvent();

        return Observable.just(event);
    }

}
