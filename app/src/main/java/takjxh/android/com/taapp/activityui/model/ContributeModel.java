package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-10 20:39
 * @Description:
 **/
public class ContributeModel {

    public Observable appindexcontribute(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .appindexcontribute(token);
    }


    public Observable contributedone(String token, Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .contributedone(token, searchRequest);
    }
}
