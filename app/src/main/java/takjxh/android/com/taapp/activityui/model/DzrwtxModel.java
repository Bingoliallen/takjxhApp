package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-20 13:02
 * @Description:
 **/
public class DzrwtxModel {

    public Observable taskdetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .taskdetail(id, token);
    }

    public Observable taskdetail1(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .taskdetail1(id, token);
    }

    public Observable taskansweradd(String token, Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .taskansweradd(token, searchRequest);
    }
}
