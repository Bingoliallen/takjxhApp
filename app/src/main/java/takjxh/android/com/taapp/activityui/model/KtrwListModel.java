package takjxh.android.com.taapp.activityui.model;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 12:52
 * @Description:
 **/
public class KtrwListModel {

    public Observable issuelist(String token, String type, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .issuelist(type,token, page, pageSize);
    }

}
