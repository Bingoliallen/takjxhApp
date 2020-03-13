package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-07 14:44
 * @Description:
 **/
public class WdZxbbModel {


    public Observable applyslist(String token, String key, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .applyslist(token, key, page, pageSize);
    }

}
