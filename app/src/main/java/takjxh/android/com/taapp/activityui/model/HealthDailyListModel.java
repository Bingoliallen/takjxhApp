package takjxh.android.com.taapp.activityui.model;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * Created by Administrator on 2020/3/10.
 */

public class HealthDailyListModel {

    public Observable healthlist(String token, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .healthlist( token, page, pageSize);
    }

}
