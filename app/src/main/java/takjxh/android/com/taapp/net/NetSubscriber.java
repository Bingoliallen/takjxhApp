package takjxh.android.com.taapp.net;

import android.app.Activity;
import android.content.Context;
import android.util.Log;


import org.greenrobot.eventbus.EventBus;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.net.ConnectException;

import takjxh.android.com.commlibrary.BuildConfig;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.activityui.activity.LoginActivity;
import takjxh.android.com.taapp.activityui.bean.LoginOut;
import takjxh.android.com.taapp.utils.UserInfoManager;
import rx.Subscriber;

/**
 * 名称定义
 *
 * @Author: libaibing
 * @Date: 2019-01-07 12:51
 * @Description:
 **/

public abstract class NetSubscriber<T> extends Subscriber<T> {

    private static final String TAG = "NetSubscriber";

    protected WeakReference<Context> mContext;

    public NetSubscriber(Context context) {
        mContext = new WeakReference<>(context);
    }

    @Override
    public void onError(Throwable e) {
        if (e != null) {
            if (BuildConfig.IS_DEBUG) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                Log.d(TAG, String.format("onError: %s", sw.toString()));
            }
            if (e instanceof ConnectException) {
                ToastUtil.showToast(QbApplication.getAppContext(), "网络连接有问题");
            }
            if (e instanceof ApiException) {
                switch (((ApiException) e).code) {
                    case ResponseCode.AUTH_ERROR:
                        if (mContext.get() != null) {
                            ToastUtil.showToast(QbApplication.getAppContext(),"Token已过期，请重新认证");
                            UserInfoManager.delete();
                            EventBus.getDefault().post(new LoginOut());
                            LoginActivity.startAction((Activity) mContext.get());
                        }
                        break;
                    case 0:
                        ToastUtil.showToast(QbApplication.getAppContext(), ((ApiException) e).error);
                        break;
                    case 404:
                        // com.example.common.utils.init.T.showNetworkError();
                        break;
                    default:
                        // com.example.common.utils.init.T.showShort(e.getMessage());
                        break;
                }
            }
        } else {
            Log.d(TAG, "onError: e is null");
        }
    }

    @Override
    public void onNext(T t) {
    }

    @Override
    public void onCompleted() {
    }
}
