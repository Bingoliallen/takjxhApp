package takjxh.android.com.taapp.activityui.model;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-20 11:16
 * @Description:
 **/
public class ToBbModel {

    public Observable tasktypelist(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .tasktypelist(token);
    }

    public Observable tasklist(String token, String type, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .tasklist(type,token, page, pageSize);
    }

}
