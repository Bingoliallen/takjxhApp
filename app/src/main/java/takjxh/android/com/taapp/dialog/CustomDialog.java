package takjxh.android.com.taapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import takjxh.android.com.taapp.R;

/**
 * 基类dialog
 *
 * @Author: libaibing
 * @Date: 2019-01-13 12:54
 * @Description:
 **/

public class CustomDialog extends Dialog {

    //定义模版
    public CustomDialog(Context context, int layout, int style) {
        this(context, WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,layout,style, Gravity.CENTER);
    }

    //定义属性
    public CustomDialog(Context context, int width, int height, int layout, int style, int gravity, int anim){
        super(context, style);

        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = width;
        layoutParams.height = height;
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);
        window.setWindowAnimations(anim);
    }

    //定义属性
    public CustomDialog(Context context, int width, int height, View layout, int style, int gravity, int anim){
        super(context, style);

        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = width;
        layoutParams.height = height;
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);
        window.setWindowAnimations(anim);
    }

    //实例
    public CustomDialog(Context context, int width, int height, int layout, int style, int gravity){
        this(context,width,height,layout,style,gravity, R.style.anim_base_dialog_slide_at_bottom);
    }

}
