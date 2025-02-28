package takjxh.android.com.commlibrary.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;


public final class ThreadUtil {

  private static Handler sHandler = new Handler(Looper.getMainLooper());

  public static boolean isMainThread() {
    return Looper.myLooper() == Looper.getMainLooper();
  }

  public static void runOnMainThread(@NonNull Runnable runnable) {
    sHandler.post(runnable);
  }

  public static void runOnMainThreadDelay(@NonNull Runnable runnable, int delayMillis) {
    sHandler.postDelayed(runnable, delayMillis);
  }

  public static void runInThread(Runnable task) {
    new Thread(task).start();
  }

  public static void runInUiThread(Runnable task) {
    sHandler.post(task);
  }
}
