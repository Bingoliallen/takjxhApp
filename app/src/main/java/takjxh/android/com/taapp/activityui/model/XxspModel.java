package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 9:35
 * @Description:
 **/
public class XxspModel {

    public Observable studysdetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .studysdetail(id, token);
    }

    public Observable trainsdetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .trainsdetail(id, token);
    }


    public Observable studyreaddone(String token, Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .studyreaddone(token, searchRequest);
    }

    public Observable trainreaddone(String token, Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .trainreaddone(token, searchRequest);
    }

}
