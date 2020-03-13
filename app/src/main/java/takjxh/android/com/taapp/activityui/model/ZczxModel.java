package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 14:05
 * @Description:
 **/
public class ZczxModel {

    public Observable policyslist(String token, String type, String key, String createUnit, String trade, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyslist(token, type, key, createUnit, trade, page, pageSize);
    }

}
