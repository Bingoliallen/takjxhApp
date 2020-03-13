package takjxh.android.com.commlibrary;

import android.app.Application;
import android.content.Context;



public class CommonApplication extends BaseApplication {

  public static CommonApplication mCommonApplication;
  public static Context getAppApplicationContext() {
    return mCommonApplication;
  }
  public static Application mApplication;

  @Override
  public void onCreate() {
    super.onCreate();
    mApplication = this;
    mCommonApplication = this;
    CommonAppProfile.init(this);
  }

  public void logout(){

  }

}
