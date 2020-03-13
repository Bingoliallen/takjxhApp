package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 8:50
 * @Description:
 **/
public class ZxywModel {

    public Observable qatypelist(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .qatypelist(token);
    }


    public Observable commquestionadd(String token, Map<String, String> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .commquestionadd(token, searchRequest);
    }


}
