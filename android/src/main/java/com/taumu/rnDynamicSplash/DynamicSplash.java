package com.taumu.rnDynamicSplash;

import android.app.Activity;
import android.app.Dialog;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import com.taumu.rnDynamicSplash.utils.Config;


public class DynamicSplash {
    private static Dialog mDialog;
    private static WeakReference<Activity> mActivity;
    private static ImageView mSplashImage;

    public static DynamicSplash mDynamicSplash;
    public static Config mConfig;

    public DynamicSplash(Activity activity) {
        this(activity, new Config());
    }

    public DynamicSplash(final Activity activity, Config config) {
        if (activity == null) return;
        if (config == null) {
            config = new Config();
        }
        final Config finalConfig = setConfigField(activity, config);
        mConfig = finalConfig;
        mDynamicSplash = this;
        mActivity = new WeakReference<Activity>(activity);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!activity.isFinishing()) {
                    mDialog = new Dialog(activity, finalConfig.getThemeResId());
                    mDialog.setContentView(finalConfig.getLayoutResID());
                    mDialog.setCancelable(false);
                    mSplashImage = (ImageView) mDialog.findViewById(R.id.dynamicSplash_id);
                    if (finalConfig.isShow()) {
                        DynamicSplashDownLoad.setDialogImage(activity, mSplashImage);
                    }
                    if (finalConfig.isAutoDownload()) {
                        downloadSplash(activity);
                    }

                    if (!mDialog.isShowing()) {
                        mDialog.show();
                    }
                }
            }
        });
    }

    private Config setConfigField(Activity activity, Config config) {
        String savePath = config.getSplashSavePath();
        if (!savePath.startsWith("/")) {
            savePath = "/" + savePath;
        }
        if (!savePath.endsWith("/")) {
            savePath = savePath + "/";
        }
        savePath = activity.getApplication().getFilesDir().getAbsolutePath() + savePath;
        config.setSplashSavePath(savePath);
        return config;
    }

    public void downloadSplash(Activity activity) {
        DynamicSplashDownLoad.downloadSplash(activity);
    }

    public void hide(Activity activity) {
        if (activity == null) {
            if (mActivity == null) {
                return;
            }
            activity = mActivity.get();
        }
        if (activity == null) return;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                    mDialog = null;
                }
            }
        });
    }
}
