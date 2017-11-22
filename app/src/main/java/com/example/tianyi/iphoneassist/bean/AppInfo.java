package com.example.tianyi.iphoneassist.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tianyi on 2017/11/13.
 */

public class AppInfo implements Serializable{


    /**
     * addTime : 0
     * hasSameDevApp : false
     * videoId : 0
     * source :
     * versionName : 2.3.1
     * ratingScore : 5
     * briefShow : 新用户专享14天5.5%+9.5%
     * developerId : 0
     * fitness : 0
     * id : 376591
     * level1CategoryId : 1
     * releaseKeyHash : cb316259954d2ae4ed13314c5b143375
     * relateAppHasMore : false
     * rId : 0
     * suitableType : 0
     * briefUseIntro : false
     * ads : 0
     * publisherName : 北京掌众财富资产管理有限公司
     * level2CategoryId : 0
     * position : 0
     * favorite : false
     * isFavorite : false
     * appendSize : 0
     * level1CategoryName : 金融理财
     * samDevAppHasMore : false
     * displayName : 掌众财富
     * icon : AppStore/04c0340f817fd64fade64fce33afd2d494442bde9
     * screenshot : AppStore/0d95e4be5287c25c6e2465c81b29418fcff41ff17,AppStore/04c0350f867fd84fa3e646ce3aafd3d49b442bde9,AppStore/0dc0394f8479d44f22e640ce3d0fded290faba4b4,AppStore/06dc653c825bda7de8ddddd21e38aa51533413cb7
     * ratingTotalCount : 0
     * adType : 0
     * apkSize : 6482511
     * packageName : cn.com.wealth365.licai
     * updateTime : 1510224452068
     * grantCode : 0
     * versionCode : 33751296
     * appTags : [{"tagId":249,"link":"sametag/249","tagName":"投资提示"}]
     * diffFileSize : 0
     */

    private HdIconEntity hdIcon;
    private List<AppInfo> relateAppInfoList;
    private String changeLog;
    private String permissionIds;
    private String web;
    private String keyWords;
    private String detailHeaderImage;
    //    private List<AppInfo> moduleInfoList;
    private List<AppInfo> sameDevAppInfoList;
    private String categoryId;


    private AppDownloadInfo mAppDownloadInfo;

    /***************************************************************************************************/


    private int addTime;
    private boolean hasSameDevApp;
    private int videoId;
    private String source;
    private String versionName;
    private float ratingScore;
    private String briefShow;
    private int developerId;
    private int fitness;
    private int id;
    private int level1CategoryId;
    private String releaseKeyHash;
    private boolean relateAppHasMore;
    private int rId;
    private int suitableType;
    private boolean briefUseIntro;
    private int ads;
    private String publisherName;
    private int level2CategoryId;
    private int position;
    private boolean favorite;
    private boolean isFavorite;
    private int appendSize;
    private String level1CategoryName;
    private boolean samDevAppHasMore;
    private String displayName;
    private String icon;
    private String screenshot;
    private int ratingTotalCount;
    private int adType;
    private int apkSize;
    private String packageName;
    private long updateTime;
    private int grantCode;
    private int versionCode;
    private int diffFileSize;
    private String introduction;
    private List<AppTagsBean> appTags;


    public HdIconEntity getHdIcon() {
        return hdIcon;
    }

    public void setHdIcon(HdIconEntity hdIcon) {
        this.hdIcon = hdIcon;
    }

    public List<AppInfo> getRelateAppInfoList() {
        return relateAppInfoList;
    }

    public void setRelateAppInfoList(List<AppInfo> relateAppInfoList) {
        this.relateAppInfoList = relateAppInfoList;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getDetailHeaderImage() {
        return detailHeaderImage;
    }

    public void setDetailHeaderImage(String detailHeaderImage) {
        this.detailHeaderImage = detailHeaderImage;
    }

    public List<AppInfo> getSameDevAppInfoList() {
        return sameDevAppInfoList;
    }

    public void setSameDevAppInfoList(List<AppInfo> sameDevAppInfoList) {
        this.sameDevAppInfoList = sameDevAppInfoList;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public AppDownloadInfo getmAppDownloadInfo() {
        return mAppDownloadInfo;
    }

    public void setmAppDownloadInfo(AppDownloadInfo mAppDownloadInfo) {
        this.mAppDownloadInfo = mAppDownloadInfo;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public boolean isHasSameDevApp() {
        return hasSameDevApp;
    }

    public void setHasSameDevApp(boolean hasSameDevApp) {
        this.hasSameDevApp = hasSameDevApp;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public float getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(float ratingScore) {
        this.ratingScore = ratingScore;
    }

    public String getBriefShow() {
        return briefShow;
    }

    public void setBriefShow(String briefShow) {
        this.briefShow = briefShow;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel1CategoryId() {
        return level1CategoryId;
    }

    public void setLevel1CategoryId(int level1CategoryId) {
        this.level1CategoryId = level1CategoryId;
    }

    public String getReleaseKeyHash() {
        return releaseKeyHash;
    }

    public void setReleaseKeyHash(String releaseKeyHash) {
        this.releaseKeyHash = releaseKeyHash;
    }

    public boolean isRelateAppHasMore() {
        return relateAppHasMore;
    }

    public void setRelateAppHasMore(boolean relateAppHasMore) {
        this.relateAppHasMore = relateAppHasMore;
    }

    public int getRId() {
        return rId;
    }

    public void setRId(int rId) {
        this.rId = rId;
    }

    public int getSuitableType() {
        return suitableType;
    }

    public void setSuitableType(int suitableType) {
        this.suitableType = suitableType;
    }

    public boolean isBriefUseIntro() {
        return briefUseIntro;
    }

    public void setBriefUseIntro(boolean briefUseIntro) {
        this.briefUseIntro = briefUseIntro;
    }

    public int getAds() {
        return ads;
    }

    public void setAds(int ads) {
        this.ads = ads;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getLevel2CategoryId() {
        return level2CategoryId;
    }

    public void setLevel2CategoryId(int level2CategoryId) {
        this.level2CategoryId = level2CategoryId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public int getAppendSize() {
        return appendSize;
    }

    public void setAppendSize(int appendSize) {
        this.appendSize = appendSize;
    }

    public String getLevel1CategoryName() {
        return level1CategoryName;
    }

    public void setLevel1CategoryName(String level1CategoryName) {
        this.level1CategoryName = level1CategoryName;
    }

    public boolean isSamDevAppHasMore() {
        return samDevAppHasMore;
    }

    public void setSamDevAppHasMore(boolean samDevAppHasMore) {
        this.samDevAppHasMore = samDevAppHasMore;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public int getRatingTotalCount() {
        return ratingTotalCount;
    }

    public void setRatingTotalCount(int ratingTotalCount) {
        this.ratingTotalCount = ratingTotalCount;
    }

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public int getApkSize() {
        return apkSize;
    }

    public void setApkSize(int apkSize) {
        this.apkSize = apkSize;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getGrantCode() {
        return grantCode;
    }

    public void setGrantCode(int grantCode) {
        this.grantCode = grantCode;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public int getDiffFileSize() {
        return diffFileSize;
    }

    public void setDiffFileSize(int diffFileSize) {
        this.diffFileSize = diffFileSize;
    }

    public List<AppTagsBean> getAppTags() {
        return appTags;
    }

    public void setAppTags(List<AppTagsBean> appTags) {
        this.appTags = appTags;
    }

    public static class AppTagsBean implements Serializable{
        /**
         * tagId : 249
         * link : sametag/249
         * tagName : 投资提示
         */

        private int tagId;
        private String link;
        private String tagName;

        public int getTagId() {
            return tagId;
        }

        public void setTagId(int tagId) {
            this.tagId = tagId;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }
    }

    public static class AppDownloadInfo implements Serializable{
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

//        private Disposable mDisposable;

        private String dowanloadUrl;

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getReleaseKeyHash() {
            return releaseKeyHash;
        }

        public void setReleaseKeyHash(String releaseKeyHash) {
            this.releaseKeyHash = releaseKeyHash;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getApkHash() {
            return apkHash;
        }

        public void setApkHash(String apkHash) {
            this.apkHash = apkHash;
        }

        public int getAppendExpansionPackSize() {
            return appendExpansionPackSize;
        }

        public void setAppendExpansionPackSize(int appendExpansionPackSize) {
            this.appendExpansionPackSize = appendExpansionPackSize;
        }

        public HdIconEntity getHdIcon() {
            return hdIcon;
        }

        public void setHdIcon(HdIconEntity hdIcon) {
            this.hdIcon = hdIcon;
        }

        public int getMainExpansionPackSize() {
            return mainExpansionPackSize;
        }

        public void setMainExpansionPackSize(int mainExpansionPackSize) {
            this.mainExpansionPackSize = mainExpansionPackSize;
        }

        public int getChannelApkId() {
            return channelApkId;
        }

        public void setChannelApkId(int channelApkId) {
            this.channelApkId = channelApkId;
        }

        public int getFitness() {
            return fitness;
        }

        public void setFitness(int fitness) {
            this.fitness = fitness;
        }

        public int getGamePackSize() {
            return gamePackSize;
        }

        public void setGamePackSize(int gamePackSize) {
            this.gamePackSize = gamePackSize;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getDiffFileSize() {
            return diffFileSize;
        }

        public void setDiffFileSize(int diffFileSize) {
            this.diffFileSize = diffFileSize;
        }

        public int getApkSize() {
            return apkSize;
        }

        public void setApkSize(int apkSize) {
            this.apkSize = apkSize;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getApk() {
            return apk;
        }

        public void setApk(String apk) {
            this.apk = apk;
        }

        public int getRefPosition() {
            return refPosition;
        }

        public void setRefPosition(int refPosition) {
            this.refPosition = refPosition;
        }

        public String getDowanloadUrl() {
            return dowanloadUrl;
        }

        public void setDowanloadUrl(String dowanloadUrl) {
            this.dowanloadUrl = dowanloadUrl;
        }
    }

    public static class HdIconEntity implements Serializable{
        private String main;

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }
    }
}
