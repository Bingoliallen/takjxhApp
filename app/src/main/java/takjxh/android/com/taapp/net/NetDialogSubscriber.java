package takjxh.android.com.taapp.net;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

import takjxh.android.com.commlibrary.widget.ac_process_dialog.ACProgressFlower;

/**
 * 名称定义
 *
 * @Author: libaibing
 * @Date: 2019-01-07 12:51
 * @Description:
 **/

public abstract class NetDialogSubscriber<T> extends NetSubscriber<T> {

    public static final int DEFAULT = 1;
    public static final int CANCEL = 1;
    public static final int FINISH = 2;

    private static final String TAG = "NetDialogSubscriber";
    private ACProgressFlower mACProgressFlower;

    public NetDialogSubscriber(Context context) {
        super(context);
        if (mACProgressFlower == null) {
            mACProgressFlower = new ACProgressFlower.Builder(context).build();
            mACProgressFlower.setCanceledOnTouchOutside(false);
            mACProgressFlower.setCancelable((configuration() & CANCEL) == CANCEL);
            mACProgressFlower.setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    unsubscribe();
                    if ((configuration() & FINISH) == FINISH) {
                        Activity activity = (Activity) mContext.get();
                        if (activity != null) {
                            activity.finish();
                        }
                    }
                }
            });
        }
    }

    /**
     * 存在以下几种情况：
     * 1. isCancel is false, 不可关闭
     * 2. isCancel is true, isFinish is true, 关闭会退出 activity
     * 3. isCancel is true, isFinish is false, 关闭不会退出 activity
     */
    public abstract int configuration();

    @Override
    public void onStart() {
        if (mACProgressFlower != null && !mACProgressFlower.isShowing()) {
            mACProgressFlower.show();
        }
        super.onStart();
    }

    @Override
    public void onError(Throwable e) {
        if (mACProgressFlower != null && mACProgressFlower.isShowing()) {
            mACProgressFlower.dismiss();
        }
        super.onError(e);
    }

    @Override
    public void onNext(T t) {
//    if (mACProgressFlower != null && mACProgressFlower.isShowing()) {
//      mACProgressFlower.dismiss();
//    }
        super.onNext(t);
    }

    @Override
    public void onCompleted() {
        if (mACProgressFlower != null && mACProgressFlower.isShowing()) {
            mACProgressFlower.dismiss();
        }
        super.onCompleted();
    }
}
