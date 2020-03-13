package takjxh.android.com.taapp.activityui.model;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * Created by Administrator on 2020/3/7.
 */

public class UserExtsListModel {


    public Observable userextlist(String token, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .userextlist(token, page, pageSize);
    }


    public Observable userextdefault(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .userextdefault(id,token);
    }


    public Observable userextdel(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .userextdel(id,token);
    }


}
