package com.example;

import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.taumu.rnDynamicSplash.DynamicSplash;
import com.taumu.rnDynamicSplash.utils.Config;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "example";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Config splashConfig = new Config();
        splashConfig.setImageUrl("http://chuantu.biz/t6/315/1526808193x-1404792987.png");

        splashConfig.setAutoHide(true);
        // splashConfig.setAutoHideTime(2000);
        // splashConfig.setLayoutResID(R.layout.custom_splash_dynamic);
        // splashConfig.setThemeResId(R.style.Custom_Splash_Theme);
        // splashConfig.setAutoDownload(false);
        // splashConfig.setSplashSavePath("/customSplashPath/");
        // splashConfig.setDynamicShow(false);
        new DynamicSplash(this, splashConfig);
        super.onCreate(savedInstanceState);
    }
}
