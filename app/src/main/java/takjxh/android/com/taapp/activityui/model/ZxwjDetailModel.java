package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 11:32
 * @Description:
 **/
public class ZxwjDetailModel {

    public Observable surveydetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .surveydetail(id, token);
    }

    public Observable surveyansweradd(String token, Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .surveyansweradd(token, searchRequest);
    }

}
