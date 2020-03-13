package takjxh.android.com.commlibrary;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import takjxh.android.com.commlibrary.net.HttpClientWrapper;
import takjxh.android.com.commlibrary.utils.DensityUtil;
import takjxh.android.com.commlibrary.utils.init.CrashUtil;




public abstract class BaseAppProfile {

  public static HttpClientWrapper app_client;

  private static BaseApplication sApplication;
  //private static DisplayMetrics sDisplayMetrics;
  private static String sPackageName;
  private static String sAppVersionName;
  private static int sAppVersionCode;

  public static void init(Application application) {
    sApplication = (BaseApplication) application;

    //WindowManager wm = (WindowManager) application.getSystemService(Context.WINDOW_SERVICE);
    //sDisplayMetrics = new DisplayMetrics();
    //wm.getDefaultDisplay().getMetrics(sDisplayMetrics);
    DensityUtil.setDensity(application);

    sPackageName = application.getPackageName();

    try {
      PackageManager pm = application.getPackageManager();
      PackageInfo pi = pm.getPackageInfo(application.getPackageName(), 0);
      sAppVersionName = pi.versionName;
      sAppVersionCode = pi.versionCode;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }


    if (BuildConfig.IS_DEBUG) {
      CrashUtil.getInstance().init(application);
    }
   // CrashReport.initCrashReport(application, "979abef51c", false);
  }

  public static BaseApplication getApplication() {
    return sApplication;
  }

  //public static DisplayMetrics getDisplayMetrics() {
  //  return sDisplayMetrics;
  //}

  public static String getPackageName() {
    return sPackageName;
  }

  public static String getAppVersionName() {
    return sAppVersionName;
  }

  public static int getAppVersionCode() {
    return sAppVersionCode;
  }
}
