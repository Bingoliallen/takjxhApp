package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * Created by Administrator on 2020/3/10.
 */

public class HealthDailyModel {

    public Observable healthindex(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .healthindex(token);
    }

    public Observable healthadd(String token,Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .healthadd(token,searchRequest);
    }


}
