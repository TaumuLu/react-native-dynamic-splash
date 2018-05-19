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
        splashConfig.setImageUrl("https://b-ssl.duitang.com/uploads/item/201601/30/20160130164234_Rv52c.jpeg");
        // splashConfig.setShow(false);
        new DynamicSplash(this, splashConfig);
        super.onCreate(savedInstanceState);
    }
}
