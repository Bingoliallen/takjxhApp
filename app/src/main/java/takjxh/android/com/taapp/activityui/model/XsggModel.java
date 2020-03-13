package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 13:31
 * @Description:
 **/
public class XsggModel {

    public Observable adslist(String token, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .adslist(token, page, pageSize);
    }


}
