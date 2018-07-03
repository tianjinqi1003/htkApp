package com.htkapp.modules.API.entity;

/**
 * Created by yinqilei on 17-7-4.
 * app版本检验更新表
 */
public class AppCheckVersion {

    private Integer id;

    private String appId; //appId号

    private String appName; //app名字

    private String clientVersion; // 客户端版本号

    private String downloadUrl;  //  下载地址url

    private String uploadLog;  //更新日志

    private boolean updateInstall;  //是否强制安装

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getUploadLog() {
        return uploadLog;
    }

    public void setUploadLog(String uploadLog) {
        this.uploadLog = uploadLog;
    }

    public boolean isUpdateInstall() {
        return updateInstall;
    }

    public void setUpdateInstall(boolean updateInstall) {
        this.updateInstall = updateInstall;
    }
}
