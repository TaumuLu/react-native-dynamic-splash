package com.taumu.rnDynamicSplash.utils;

import com.taumu.rnDynamicSplash.R;

public class Config {
    private String imageUrl = "";
    private int themeResId = R.style.DynamicSplash_Theme;
    private int layoutResID = R.layout.splash_dynamic;
    private String splashSavePath = "/splash/";
    private boolean autoDownload = true;
    private boolean isShow = true;

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

    public int getLayoutResID() {
        return layoutResID;
    }

    public void setLayoutResID(int layoutResID) {
        this.layoutResID = layoutResID;
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

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
