package takjxh.android.com.taapp.activityui.model;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 10:46
 * @Description:
 **/
public class JlztModel {

    public Observable commtypelist(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .commtypelist(token);
    }

    public Observable commlist(String token,String type, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .commlist(type, token,page, pageSize);
    }

}
