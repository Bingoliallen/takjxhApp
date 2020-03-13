package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 9:49
 * @Description:
 **/
public class ZjsbQzDetailModel {

    public Observable policyapplyhelpdetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyapplyhelpdetail(id, token);
    }
}
