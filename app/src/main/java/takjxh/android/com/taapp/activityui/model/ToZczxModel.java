package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 13:50
 * @Description:
 **/
public class ToZczxModel {

    public Observable policyindex(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyindex(token);
    }

}
