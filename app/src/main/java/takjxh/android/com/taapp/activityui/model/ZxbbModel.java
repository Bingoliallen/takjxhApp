package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-08 11:21
 * @Description:
 **/
public class ZxbbModel {

    public Observable itemlist(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .itemlist(token);
    }


    public Observable ordergenerate(String token, Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .ordergenerate(token, searchRequest);
    }
}
