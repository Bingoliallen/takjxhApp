package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 13:35
 * @Description:
 **/
public class WdZjsbModel {

    public Observable policyapplylist(String token, String status, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyapplylist(token, status, page, pageSize);
    }
}
