package takjxh.android.com.taapp.activityui.model;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-10 23:01
 * @Description:
 **/
public class ContributeDetialModel {

    public Observable contributedetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .contributedetail(id, token);
    }

}
