package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 14:17
 * @Description:
 **/
public class XsggDetialModel {

    public Observable adsdetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .adsdetail(id, token);
    }



}
