package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 10:20
 * @Description:
 **/
public class WdZjsbQzModel {

    public Observable policyapplyhelplist(String token, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyapplyhelplist(token, page, pageSize);
    }

}
