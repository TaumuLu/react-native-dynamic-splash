package com.taumu.rnDynamicSplash;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import java.io.File;

import static com.taumu.rnDynamicSplash.utils.FileUtils.*;
import static com.taumu.rnDynamicSplash.DynamicSplash.mConfig;

public class DynamicSplashDownLoad {
    private static String sharedName = "dynamicSplashConfig";
    private static String sharedKeyName = "fileName";

    public static void setSharedValue(Activity activity, String value) {
        SharedPreferences sharedPre = activity.getSharedPreferences(sharedName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString(sharedKeyName, value);
        editor.apply();
    }

    public static String getSharedValue(Activity activity) {
        SharedPreferences sharedPre = activity.getSharedPreferences(sharedName, Context.MODE_PRIVATE);
        return sharedPre.getString(sharedKeyName, "");
    }

    public static void downloadSplash(Activity activity) {
        String imageUrl = mConfig.getImageUrl();
        if (imageUrl != null && !imageUrl.equals("")) {
            String fileName = getFileName(imageUrl);
            try {
                File imageFile = new File(mConfig.getSplashSavePath() + fileName);

                if (!imageFile.exists()) {
                    new DownloadAsyncTask().execute(imageUrl);
                }
                setSharedValue(activity, fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setDialogImage(Activity activity, ImageView imageView) {
        String fileName = getSharedValue(activity);

        if (!TextUtils.isEmpty(fileName)) {
            String filePath = mConfig.getSplashSavePath() + fileName;
            Bitmap loacalBitmap = getLoacalBitmap(filePath);

            if (loacalBitmap != null) {
                imageView.setImageBitmap(loacalBitmap);
                Log.d("SplashDownLoad", "本地获取");
            }
        }
    }

    static class DownloadAsyncTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap b = getBitmapByUrl(params[0]);
            return b;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null) {
                File file = saveImage(result, mConfig.getSplashSavePath(), getFileName(mConfig.getImageUrl()));
                Log.d("SplashDownLoad", file.toString());
            }
        }
    }
}
