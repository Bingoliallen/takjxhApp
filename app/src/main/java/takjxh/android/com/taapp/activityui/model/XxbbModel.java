package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-06 15:59
 * @Description:
 **/
public class XxbbModel {

    public Observable scoreslist(String token, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .scoreslist(token, page, pageSize);
    }

}
