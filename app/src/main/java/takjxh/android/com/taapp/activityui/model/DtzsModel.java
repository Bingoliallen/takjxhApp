package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 16:21
 * @Description:
 **/
public class DtzsModel {

    public Observable companytypelist(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .companytypelist(token);
    }

    public Observable companyslist(String token, String type, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .companyslist(token, type, page, pageSize);
    }


}
