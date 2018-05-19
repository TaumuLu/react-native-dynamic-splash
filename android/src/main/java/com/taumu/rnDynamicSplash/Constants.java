package com.taumu.rnDynamicSplash;

import com.terminus.hisensemobile.MainApplication;


public class Constants {
  public static String SPLASH_PATH = MainApplication.getContext().getFilesDir().getAbsolutePath() + "/splash/";
  public static String requestURL = "/api/app/config?keys=app.startup.img";
}
