package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-06 15:17
 * @Description:
 **/
public class JyfkModel {

    public Observable feedback(String phone, Map<String, String> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .feedback(phone, searchRequest);
    }


    public Observable feedbacklist(String token, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .feedbacklist( token, page, pageSize);
    }


}
