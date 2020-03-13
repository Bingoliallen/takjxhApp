package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 11:01
 * @Description:
 **/
public class StartUIModel {

    public Observable start() {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .start();
    }

    public Observable paramlist() {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .paramlist();
    }


}
