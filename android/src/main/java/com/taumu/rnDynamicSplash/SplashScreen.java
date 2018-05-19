package com.taumu.rnDynamicSplash;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.ImageView;

import com.terminus.hisensemobile.R;

import java.lang.ref.WeakReference;


public class SplashScreen {
    private static Dialog mSplashDialog;
    private static WeakReference<Activity> mActivity;
    private static ImageView mSpBgImage;

    public void show(final Activity activity, final boolean fullScreen) {
        if (activity == null) return;
        mActivity = new WeakReference<Activity>(activity);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!activity.isFinishing()) {
                    mSplashDialog = new Dialog(activity, fullScreen ? R.style.SplashScreen_Fullscreen : R.style.SplashScreen_SplashTheme);
                    mSplashDialog.setContentView(R.layout.launch_screen);
                    mSplashDialog.setCancelable(false);
                    mSpBgImage = (ImageView) mSplashDialog.findViewById(R.id.sp_bg);
                    SplashDownLoad.setImageBitmap(activity, mSpBgImage);
                    // downloadSplashImg(activity, true);

                    if (!mSplashDialog.isShowing()) {
                        mSplashDialog.show();
                    }
                }
            }
        });
    }

    public void downloadSplashImg(Activity activity) {
        SplashDownLoad.requestSplashInfo(activity);
    }

    public void show(final Activity activity) {
        SharedPreferences sharedPre = activity.getSharedPreferences("splashConfig", activity.MODE_PRIVATE);
        String fileName = sharedPre.getString("fileName", "");
        if (!TextUtils.isEmpty(fileName)) {
            show(activity, false);
        }
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
                if (mSplashDialog != null && mSplashDialog.isShowing()) {
                    mSplashDialog.dismiss();
                    mSplashDialog = null;
                }
            }
        });
    }
}
