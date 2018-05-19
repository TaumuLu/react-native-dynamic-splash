package com.taumu.rnDynamicSplash;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.terminus.hisensemobile.MainActivity;

public class SplashScreenModule extends ReactContextBaseJavaModule {
    public SplashScreenModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "DynamicSplash";
    }

    @ReactMethod
    public void show() {
        MainActivity.splashScreen.show(getCurrentActivity());
    }

    @ReactMethod
    public void downloadSplashImg() {
        MainActivity.splashScreen.downloadSplashImg(getCurrentActivity());
    }

    @ReactMethod
    public void hide() {
        MainActivity.splashScreen.hide(getCurrentActivity());
    }
}
