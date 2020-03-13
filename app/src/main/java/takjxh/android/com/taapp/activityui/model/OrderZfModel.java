package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-08 13:56
 * @Description:
 **/
public class OrderZfModel {

    public Observable orderquery(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .orderquery(id, token);
    }

    public Observable orderdone(String token, Map<String, String> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .orderdone(token, searchRequest);
    }


}
