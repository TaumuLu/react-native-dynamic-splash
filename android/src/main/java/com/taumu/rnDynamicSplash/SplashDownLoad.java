package com.taumu.rnDynamicSplash;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.terminus.hisensemobile.BuildConfig;
import com.terminus.hisensemobile.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class SplashDownLoad {
    public static String imageUrl;

    public static void requestSplashInfo(final Activity activity) {
        String path = BuildConfig.agreement + BuildConfig.domain + Constants.requestURL;

        HttpUtils.get(path, new HttpUtils.Callback() {
            @Override
            public void onResponse(String jsonString) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONObject valueObject = jsonObject.getJSONObject("app.startup.img");
                    String value = valueObject.getString("value");
                    if (!TextUtils.isEmpty(value)) {
                        JSONObject jsonValue = new JSONObject(value);
                        imageUrl = "http:" + jsonValue.getString("img");
                        String fileName = getFileName(imageUrl);
                        File imageFile = new File(Constants.SPLASH_PATH + fileName);

                        if (!imageFile.exists()) {
                            new DownImgAsyncTask().execute(imageUrl);
                        }
                        //获取SharedPreferences对象
                        SharedPreferences sharedPre = activity.getSharedPreferences("splashConfig", activity.MODE_PRIVATE);
                        //获取Editor对象
                        SharedPreferences.Editor editor = sharedPre.edit();
                        //设置参数
                        editor.putString("fileName", fileName);
                        editor.commit();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String getFileName(String url) {
        int index = url.lastIndexOf("/") + 1;
        return url.substring(index);
    }

    public static void setImageBitmap(Activity activity, ImageView imageView) {
        SharedPreferences sharedPre = activity.getSharedPreferences("splashConfig", activity.MODE_PRIVATE);
        String fileName = sharedPre.getString("fileName", "");

        if (!TextUtils.isEmpty(fileName)) {
            String filePath = Constants.SPLASH_PATH + fileName;
            Bitmap loacalBitmap = SplashDownLoad.getLoacalBitmap(filePath);

            if (loacalBitmap != null) {
                imageView.setImageBitmap(loacalBitmap);
                Log.d("SplashDownLoad", "本地获取");
            }
        }
    }

    public static Bitmap getLoacalBitmap(String url) {
        if (url != null) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(url);
                return BitmapFactory.decodeStream(fis); // /把流转化为Bitmap图片
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            return null;
        }
    }

    public static Bitmap getBitmapByUrl(final String url) {
        URL fileUrl;
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            fileUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //保存图片到本地路径
    public static File saveImage(Bitmap bmp, String filePath, String fileName) {
        File appDir = new File(filePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File[] files = appDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            deleteFile(files[i].getAbsolutePath());
        }

        File file = new File(appDir, fileName);
        try {
            // if(file.exists()){
            //   file.delete();
            // }
            // file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            // JPEG
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    static class DownImgAsyncTask extends AsyncTask<String, Void, Bitmap> {

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
                File file = saveImage(result, Constants.SPLASH_PATH, getFileName(imageUrl));
                Log.d("SplashDownLoad", file.toString());
            }
        }
    }
}
