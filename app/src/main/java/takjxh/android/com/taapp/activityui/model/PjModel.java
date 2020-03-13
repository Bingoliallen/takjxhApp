package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 11:02
 * @Description:
 **/
public class PjModel {

    public Observable policyapplyevaluatedetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyapplyevaluatedetail(id, token);
    }

    public Observable applyadddevaluate(String token, Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .applyadddevaluate(token, searchRequest);
    }

}
