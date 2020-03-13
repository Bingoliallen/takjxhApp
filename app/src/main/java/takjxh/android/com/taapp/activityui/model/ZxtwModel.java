package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 14:06
 * @Description:
 **/
public class ZxtwModel {

    public Observable commdetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .commdetail(id, token);
    }


    public Observable questionadd(String token, Map<String, String> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .questionadd(token, searchRequest);
    }


}
