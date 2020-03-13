package takjxh.android.com.taapp.activityui.model;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 13:48
 * @Description:
 **/
public class LlksModle {

    public Observable examindex(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .examindex(token);
    }

}
