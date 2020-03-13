package takjxh.android.com.taapp.activityui.model;


import java.util.Map;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 9:46
 * @Description:
 **/
public class TwDyModel {

    public Observable qaansweradd(String token, Map<String, String> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .qaansweradd(token, searchRequest);
    }


}
