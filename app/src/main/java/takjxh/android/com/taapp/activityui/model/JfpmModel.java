package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-06 16:23
 * @Description:
 **/
public class JfpmModel {

    public Observable rankslist(String token, String type, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .rankslist(token, type, page, pageSize);
    }


}
