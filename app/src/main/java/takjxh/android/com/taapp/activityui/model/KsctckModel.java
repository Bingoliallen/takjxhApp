package takjxh.android.com.taapp.activityui.model;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 14:39
 * @Description:
 **/
public class KsctckModel {


    public Observable examdetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .examdetail(id,token);
    }
}
