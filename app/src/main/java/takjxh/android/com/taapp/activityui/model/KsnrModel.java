package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 14:04
 * @Description:
 **/
public class KsnrModel {

    public Observable examlist(String token, String type, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .examlist(type,token);
    }

    public Observable examadd(String token, Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .examadd(token, searchRequest);
    }


}
