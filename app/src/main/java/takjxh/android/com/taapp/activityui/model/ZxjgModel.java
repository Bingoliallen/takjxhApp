package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 15:19
 * @Description:
 **/
public class ZxjgModel {

    public Observable organslist(String token, String key, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .organslist(token, key, page, pageSize);
    }


}
