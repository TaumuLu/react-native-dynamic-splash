package com.taumu.rnDynamicSplash;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import static com.taumu.rnDynamicSplash.DynamicSplash.mDynamicSplash;

public class DynamicSplashModule extends ReactContextBaseJavaModule {
    public DynamicSplashModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "DynamicSplash";
    }

    // @ReactMethod
    // public void show() {
    //     mDynamicSplash.show(getCurrentActivity());
    // }

    @ReactMethod
    public void downloadSplash() {
        mDynamicSplash.downloadSplash(getCurrentActivity());
    }

    @ReactMethod
    public void hide() {
        mDynamicSplash.hide(getCurrentActivity());
    }
}
