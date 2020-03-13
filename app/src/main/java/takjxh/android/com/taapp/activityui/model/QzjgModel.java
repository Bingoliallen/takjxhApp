package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 14:48
 * @Description:
 **/
public class QzjgModel {

    public Observable policyapplyorganslist(String token, String key, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyapplyorganslist(token, key, page, pageSize);
    }

    public Observable applyadddhelp(String token, Map<String, String> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .applyadddhelp(token, searchRequest);
    }

}
