package takjxh.android.com.commlibrary.view.activity.advert;



import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;



import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import takjxh.android.com.commlibrary.CommonAppProfile;
import takjxh.android.com.commlibrary.consts.CommonPreferenceConst;
import takjxh.android.com.commlibrary.utils.FileUtil;
import takjxh.android.com.commlibrary.utils.wrapper.PreferenceWrapper;

/**
 * 后台下载图片
 */
public class AdvertImageLoadTask {

  private static final String IMAGE_FOLDER_NAME = "AdvertPictures";

  private WeakReference<CallBack> mReference;
  private String mNewUrl;

  public AdvertImageLoadTask(CallBack callBack) {
    mReference = new WeakReference<>(callBack);
  }

  public void execute(@NonNull String oldUrl, String newUrl) {
    mNewUrl = newUrl;

    if (!TextUtils.isEmpty(oldUrl)) {
      if (mReference.get() != null) {
        Activity activity = (Activity) mReference.get();
        if (!activity.isDestroyed() && !activity.isFinishing()) {
          mReference.get().setImage(String.format("%s/%s.jpg",
              CommonAppProfile.getApplication().getExternalFilesDir(IMAGE_FOLDER_NAME)
                  .getAbsolutePath(),
              oldUrl));
        }
      }
    }

    if (!TextUtils.isEmpty(mNewUrl)) {
      downloadImage();
    }
  }

  private void downloadImage() {
    OkHttpClient client = new OkHttpClient();
    final Request request = new Request.Builder()
        .get()
        .url(mNewUrl)
        .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {

      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        FileUtil.createFile(response.body().bytes(), String.format("%s/%s.jpg",
            CommonAppProfile.getApplication().getExternalFilesDir(IMAGE_FOLDER_NAME)
                .getAbsolutePath(), mNewUrl));
        PreferenceWrapper.setString(CommonAppProfile.getApplication(),
            CommonPreferenceConst.MAIN_PREFERENCE_FILE,
            CommonPreferenceConst.PREFERENCE_KEY.SPLASH_URL, mNewUrl);
      }
    });
  }

  interface CallBack {

    void setImage(String filePath);
  }
}
