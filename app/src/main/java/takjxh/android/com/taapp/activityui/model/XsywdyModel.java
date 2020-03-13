package takjxh.android.com.taapp.activityui.model;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 16:50
 * @Description:
 **/
public class XsywdyModel {

    public Observable qafaqlist(String token,String page,String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .qafaqlist(token, page, pageSize);
    }

    public Observable qalist(String token,String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .qalist(token, page,  pageSize);
    }

    public Observable qaqauserlist(String token,String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .qaqauserlist(token, page,  pageSize);
    }

    public Observable qalatest(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .qalatest(token);
    }

}
