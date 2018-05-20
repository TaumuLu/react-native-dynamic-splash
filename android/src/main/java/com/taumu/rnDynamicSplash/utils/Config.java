package com.taumu.rnDynamicSplash.utils;

import com.taumu.rnDynamicSplash.R;

public class Config {
    private String imageUrl = "";
    private String splashSavePath = "/splash/";
    private int themeResId = R.style.DynamicSplash_Theme;
    private int layoutResId = R.layout.splash_dynamic;
    private boolean autoDownload = true;
    private boolean dynamicShow = true;
    private boolean autoHide = false;
    private long autoHideTime = 3000;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getThemeResId() {
        return themeResId;
    }

    public void setThemeResId(int themeResId) {
        this.themeResId = themeResId;
    }

    public String getSplashSavePath() {
        return splashSavePath;
    }

    public void setSplashSavePath(String splashSavePath) {
        this.splashSavePath = splashSavePath;
    }

    public boolean isAutoDownload() {
        return autoDownload;
    }

    public void setAutoDownload(boolean autoDownload) {
        this.autoDownload = autoDownload;
    }

    public boolean isAutoHide() {
        return autoHide;
    }

    public void setAutoHide(boolean autoHide) {
        this.autoHide = autoHide;
    }

    public long getAutoHideTime() {
        return autoHideTime;
    }

    public void setAutoHideTime(long autoHideTime) {
        this.autoHideTime = autoHideTime;
    }

    public boolean isDynamicShow() {
        return dynamicShow;
    }

    public void setDynamicShow(boolean dynamicShow) {
        this.dynamicShow = dynamicShow;
    }

    public int getLayoutResId() {
        return layoutResId;
    }

    public void setLayoutResId(int layoutResId) {
        this.layoutResId = layoutResId;
    }
}
