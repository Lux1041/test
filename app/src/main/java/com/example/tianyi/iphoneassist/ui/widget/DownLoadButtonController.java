package com.example.tianyi.iphoneassist.ui.widget;

import android.Manifest;
import android.content.Context;
import android.os.Environment;

import com.example.tianyi.iphoneassist.bean.AppDownloadInfo;
import com.example.tianyi.iphoneassist.bean.AppInfo;
import com.example.tianyi.iphoneassist.common.rx.RxHttpResponseCompose;
import com.example.tianyi.iphoneassist.common.util.AppUtils;
import com.example.tianyi.iphoneassist.common.util.PermissionUtil;
import com.example.tianyi.iphoneassist.http.ApiServer;
import com.jakewharton.rxbinding2.view.RxView;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;

/**
 * Created by Tianyi on 2017/11/24.
 */

public class DownLoadButtonController {

    public static final String BASE_DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    private RxDownload rxDownload;
    private ApiServer apiServer;

    public DownLoadButtonController(RxDownload rxDownload, ApiServer apiServer){
        this.rxDownload = rxDownload;
        this.apiServer = apiServer;
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
                            return getAppDownloadInfo(appInfo).flatMap(new Function<AppDownloadInfo, ObservableSource<DownloadEvent>>() {
                                @Override
                                public ObservableSource<DownloadEvent> apply(@NonNull AppDownloadInfo appDownloadInfo) throws Exception {
                                    appInfo.setmAppDownloadInfo(appDownloadInfo);
                                    return receiveDownloadStatus(appDownloadInfo.getDownloadUrl());
                                }
                            });
//                            return isApkFileDownLoadTotal(appInfo);
                        }else{
                            return Observable.just(downloadEvent);
                        }
                    }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DownloadConsumer(btn, appInfo));

    }

    private ObservableSource<DownloadEvent> receiveDownloadStatus(String downloadUrl) {
        return rxDownload.receiveDownloadStatus(downloadUrl);
    }

    //获取App下载的情况
    private Observable<AppDownloadInfo> getAppDownloadInfo(AppInfo appInfo) {
        return apiServer.getAppDownloadInfo(appInfo.getId())
                .compose(RxHttpResponseCompose.<AppDownloadInfo>compatResult());
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
                        donwloadApk(btn, appInfo);
                        break;
                    case DownloadFlag.COMPLETED:
                        AppUtils.installApk(btn.getContext(), BASE_DOWNLOAD_PATH + File.separator + appInfo.getReleaseKeyHash());
                        break;
                    case DownloadFlag.STARTED:
                        pausedDownload(appInfo.getmAppDownloadInfo().getDownloadUrl());
                        break;
                }
            }
        });
    }

    private void pausedDownload(String downloadUrl) {
        rxDownload.pauseServiceDownload(downloadUrl).subscribe();
    }

    //下载apk
    private void donwloadApk(final DownloadProgressButton btn, final AppInfo appInfo) {

        PermissionUtil.requestPermisson(btn.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                if (aBoolean){

                    if (appInfo.getmAppDownloadInfo() == null){
                        //第一次下载，文件不存在的情况
                        getAppDownloadInfo(appInfo).subscribe(new Consumer<AppDownloadInfo>() {
                            @Override
                            public void accept(@NonNull AppDownloadInfo appDownloadInfo) throws Exception {
                                appInfo.setmAppDownloadInfo(appDownloadInfo);
                                rxDownload.serviceDownload(appDownloadInfo.getDownloadUrl()).subscribe();
                                rxDownload.receiveDownloadStatus(appDownloadInfo.getDownloadUrl()).subscribe(new DownloadConsumer(btn, appInfo));
                            }
                        });
                    }else{
                        //下载文件存在的情况
                        rxDownload.serviceDownload(appInfo.getmAppDownloadInfo().getDownloadUrl()).subscribe();
                        rxDownload.receiveDownloadStatus(appInfo.getmAppDownloadInfo().getDownloadUrl()).subscribe(new DownloadConsumer(btn, appInfo));
                    }

                }else{

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

    class DownloadConsumer implements Consumer<DownloadEvent> {

        DownloadProgressButton btn;
        private AppInfo appInfo;
        public DownloadConsumer(DownloadProgressButton btn, AppInfo appinfo){
            this.btn = btn;
            this.appInfo = appinfo;
        }

        @Override
        public void accept(@NonNull DownloadEvent downloadEvent) throws Exception {

            bindButton(btn, downloadEvent.getFlag(), appInfo);

            switch (downloadEvent.getFlag()){

                case DownloadFlag.INSTALLED:
                    btn.setText("运行");
                    break;


                case DownloadFlag.NORMAL:
                    btn.download();
                    break;


                case DownloadFlag.STARTED:
                    btn.setProgress((int) downloadEvent.getDownloadStatus().getPercentNumber());
                    break;

                case DownloadFlag.PAUSED:
                    btn.setProgress((int) downloadEvent.getDownloadStatus().getPercentNumber());
                    btn.paused();
                    break;


                case DownloadFlag.COMPLETED: //已完成
                    btn.setText("安装");
                    break;
                case DownloadFlag.FAILED://下载失败
                    btn.setText("失败");
                    break;
                case DownloadFlag.DELETED: //已删除

                    break;
                default:btn.download();
            }
        }
    }
}
