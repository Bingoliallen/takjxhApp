package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-07 11:54
 * @Description:
 **/
public class XxshModel {

    public Observable auditlist(String token, String status, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .auditlist(token, status, page, pageSize);
    }

    public Observable updateaudit(String token, Map<String, String> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .updateaudit(token, searchRequest);
    }


}
