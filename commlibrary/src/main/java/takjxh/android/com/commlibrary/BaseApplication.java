package takjxh.android.com.commlibrary;


import android.app.Activity;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import java.util.ArrayList;




public abstract class BaseApplication extends MultiDexApplication {

  private final static ArrayList<Activity> ACTIVITIES = new ArrayList<>();

  static {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
  }

  public void addActivity(Activity activity) {
    ACTIVITIES.add(activity);
  }

  public void removeActivity(Activity activity) {
    ACTIVITIES.remove(activity);
  }

  /**
   * 销毁所有的 activity
   */
  @Override
  public void onTerminate() {
    super.onTerminate();

    for (Activity activity : ACTIVITIES) {
      activity.finish();
    }

    System.exit(0);
  }
}
