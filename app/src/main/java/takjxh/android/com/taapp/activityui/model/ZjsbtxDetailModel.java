package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 14:14
 * @Description:
 **/
public class ZjsbtxDetailModel {

    public Observable policyapplytypelist(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyapplytypelist(token);
    }



    public Observable policyapplydetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyapplydetail(id, token);
    }


}
