package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-06 15:07
 * @Description:
 **/
public class QYModel {

    public Observable bannners() {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .bannners();
    }



}
