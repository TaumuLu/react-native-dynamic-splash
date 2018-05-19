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
        // splashConfig.setShow(false);
        new DynamicSplash(this, splashConfig);
        super.onCreate(savedInstanceState);
    }
}
