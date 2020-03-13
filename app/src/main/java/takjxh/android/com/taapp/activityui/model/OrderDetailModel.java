package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-08 15:50
 * @Description:
 **/
public class OrderDetailModel {

    public Observable orderdetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .orderdetail(id, token);
    }

}
