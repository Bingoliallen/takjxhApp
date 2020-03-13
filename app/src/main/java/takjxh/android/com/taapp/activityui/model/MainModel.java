package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * Created by Administrator on 2020/3/10.
 */

public class MainModel {

    public Observable registrationid(String token,Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .registrationid( token,searchRequest);
    }

}
