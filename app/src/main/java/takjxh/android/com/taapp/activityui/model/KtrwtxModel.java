package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 13:09
 * @Description:
 **/
public class KtrwtxModel {

    public Observable issuedetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .issuedetail(id, token);
    }


    public Observable issueansweradd(String token, Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .issueansweradd(token, searchRequest);
    }

}
