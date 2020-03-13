package takjxh.android.com.commlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;



public class SoftKeyboardUtil {

  /**
   * 隐藏键盘
   */
  public static void hideSoftKeyboard(Activity activity) {
    if (activity.getWindow().getAttributes().softInputMode
        != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
      if (activity.getCurrentFocus() != null) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity
            .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
            InputMethodManager.HIDE_NOT_ALWAYS);
      }
    }
  }
}
