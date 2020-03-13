package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-06 15:06
 * @Description:
 **/
public class ZFModel {

    public Observable bannners() {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .bannners();
    }



}
