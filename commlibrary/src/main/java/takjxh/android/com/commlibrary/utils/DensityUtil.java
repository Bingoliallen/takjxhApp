package takjxh.android.com.commlibrary.utils;


import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import java.text.DecimalFormat;

public class DensityUtil {

  enum Orientation {
    WIDTH, HEIGHT
  }

  public static float appDensity;
  public static float appScaledDensity;
  public static DisplayMetrics appDisplayMetrics;

  public static void setDensity(@NonNull final Application application) {
    //获取 application 的 DisplayMetrics
    appDisplayMetrics = application.getResources().getDisplayMetrics();

    //初始化的时候赋值
    appDensity = appDisplayMetrics.density;
    appScaledDensity = appDisplayMetrics.scaledDensity;

    //添加字体变化的监听
    application.registerComponentCallbacks(new ComponentCallbacks() {
      @Override
      public void onConfigurationChanged(Configuration newConfig) {
        //字体改变后,将 appScaledDensity 重新赋值
        if (newConfig != null && newConfig.fontScale > 0) {
          appScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
        }
      }

      @Override
      public void onLowMemory() {
      }
    });
  }

  /**
   * 此方法在 BaseActivity 中做初始化(如果不封装BaseActivity的话,直接用下面那个方法就好了)
   */
  public static void setDensity(Activity activity) {
    setDensity(activity, Orientation.WIDTH);
  }

  /**
   * targetDensity
   * targetScaledDensity
   * targetDensityDpi
   * 这三个参数是统一修改过后的值
   *
   * orientation：方向值，传入 WIDTH 或 HEIGHT
   */
  public static void setDensity(Activity activity, Orientation orientation) {

    float targetDensity = 0;
    try {
      Double division;
      //根据带入参数选择不同的适配方向
      if (orientation.equals(Orientation.HEIGHT)) {
        division = division(appDisplayMetrics.heightPixels, 667);
      } else {
        division = division(appDisplayMetrics.widthPixels, 360);
      }
      //由于手机的长宽不尽相同,肯定会有除不尽的情况,有失精度,所以在这里把所得结果做了一个保留两位小数的操作
      DecimalFormat df = new DecimalFormat("0.00");
      String s = df.format(division);
      targetDensity = Float.parseFloat(s);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }

    float targetScaledDensity = targetDensity * (appScaledDensity / appDensity);
    int targetDensityDpi = (int) (160 * targetDensity);

    /**
     *
     * 最后在这里将修改过后的值赋给系统参数
     *
     * 只修改Activity的density值
     */
    DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
    activityDisplayMetrics.density = targetDensity;
    activityDisplayMetrics.scaledDensity = targetScaledDensity;
    activityDisplayMetrics.densityDpi = targetDensityDpi;
  }

  private static double division(double a, double b) {
    double div;
    if (b != 0) {
      div = a / b;
    } else {
      div = 0;
    }
    return div;
  }
}
