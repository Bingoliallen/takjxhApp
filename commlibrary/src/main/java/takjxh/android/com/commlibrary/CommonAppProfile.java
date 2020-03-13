package takjxh.android.com.commlibrary;

import android.app.Application;

import takjxh.android.com.commlibrary.net.HttpClientWrapper;




public class CommonAppProfile extends BaseAppProfile {

  public static void init(Application application) {
    BaseAppProfile.init(application);
   CommonAppProfile.app_client = HttpClientWrapper.getDefault();
  }
}
