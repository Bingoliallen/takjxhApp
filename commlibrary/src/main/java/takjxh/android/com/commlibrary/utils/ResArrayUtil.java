package takjxh.android.com.commlibrary.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;



public class ResArrayUtil {

  public String[] getStringArray(Context context, int resId) {
    return context.getResources().getStringArray(resId);
  }

  public Drawable[] getDrawableArray(Context context, int resId, int size) {
    Drawable[] drawables = new Drawable[size];
    TypedArray typedArray = context.getResources().obtainTypedArray(resId);
    for (int i = 0; i < size; i++) {
      drawables[i] = AppCompatDrawableManager.get()
          .getDrawable(context, typedArray.getResourceId(i, 0));
    }
    typedArray.recycle();
    return drawables;
  }
}
