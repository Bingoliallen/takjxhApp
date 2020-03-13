package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 9:14
 * @Description:
 **/
public class ZjsbQzModel {

    public Observable policyapplyhelplist2(String token, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyapplyhelplist2(token, page, pageSize);
    }


}
