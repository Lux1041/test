package com.example.tianyi.iphoneassist.bean;

import java.io.Serializable;

import io.reactivex.disposables.Disposable;

/**
 * Created by Tianyi on 2017/11/25.
 */

public class AppDownloadInfo implements Serializable {
    private String thumbnail;
    private String releaseKeyHash;
    private String icon;
    private String apkHash;
    private int appendExpansionPackSize;
    private HdIconEntity hdIcon;
    private int mainExpansionPackSize;
    private int channelApkId;
    private int fitness;
    private int gamePackSize;
    private String host;
    private int diffFileSize;
    private int apkSize;
    private int id;
    private String apk;
    private int refPosition;

    private Disposable mDisposable;


    private String dowanloadUrl;
    public String getDownloadUrl(){

        if(dowanloadUrl !=null)
            return  dowanloadUrl;

        return  this.getHost() + this.getApk();
    }

    public void setDowanloadUrl(String dowanloadUrl) {
        this.dowanloadUrl = dowanloadUrl;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setReleaseKeyHash(String releaseKeyHash) {
        this.releaseKeyHash = releaseKeyHash;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setApkHash(String apkHash) {
        this.apkHash = apkHash;
    }

    public void setAppendExpansionPackSize(int appendExpansionPackSize) {
        this.appendExpansionPackSize = appendExpansionPackSize;
    }

    public void setHdIcon(HdIconEntity hdIcon) {
        this.hdIcon = hdIcon;
    }

    public void setMainExpansionPackSize(int mainExpansionPackSize) {
        this.mainExpansionPackSize = mainExpansionPackSize;
    }

    public void setChannelApkId(int channelApkId) {
        this.channelApkId = channelApkId;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void setGamePackSize(int gamePackSize) {
        this.gamePackSize = gamePackSize;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setDiffFileSize(int diffFileSize) {
        this.diffFileSize = diffFileSize;
    }

    public void setApkSize(int apkSize) {
        this.apkSize = apkSize;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setApk(String apk) {
        this.apk = apk;
    }

    public void setRefPosition(int refPosition) {
        this.refPosition = refPosition;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getReleaseKeyHash() {
        return releaseKeyHash;
    }

    public String getIcon() {
        return icon;
    }

    public String getApkHash() {
        return apkHash;
    }

    public int getAppendExpansionPackSize() {
        return appendExpansionPackSize;
    }

    public HdIconEntity getHdIcon() {
        return hdIcon;
    }

    public int getMainExpansionPackSize() {
        return mainExpansionPackSize;
    }

    public int getChannelApkId() {
        return channelApkId;
    }

    public int getFitness() {
        return fitness;
    }

    public int getGamePackSize() {
        return gamePackSize;
    }

    public String getHost() {
        return host;
    }

    public int getDiffFileSize() {
        return diffFileSize;
    }

    public int getApkSize() {
        return apkSize;
    }

    public int getId() {
        return id;
    }

    public String getApk() {
        return apk;
    }

    public int getRefPosition() {
        return refPosition;
    }

    public class HdIconEntity implements Serializable {
        /**
         * main : AppStore/07750d40a68e2445a3439a8f781083c431bfa5934
         */
        private String main;

        public void setMain(String main) {
            this.main = main;
        }

        public String getMain() {
            return main;
        }
    }


    public Disposable getDisposable() {
        return mDisposable;
    }

    public void setDisposable(Disposable disposable) {
        mDisposable = disposable;
    }
}
