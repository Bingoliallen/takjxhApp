package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 11:19
 * @Description:
 **/
public class XsfdpxModel {

    public Observable traintype(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .traintype(token);
    }

    public Observable trainslist(String token,String typeLike, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .trainslist(typeLike,token, page, pageSize);
    }



}
