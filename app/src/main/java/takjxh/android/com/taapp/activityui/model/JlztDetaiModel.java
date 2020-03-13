package takjxh.android.com.taapp.activityui.model;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 13:27
 * @Description:
 **/
public class JlztDetaiModel {

    public Observable commdetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .commdetail(id, token);
    }

    public Observable commquestionlist(String token, String topicId, String orderBy, String ascOrDesc, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .commquestionlist(topicId, token, orderBy, ascOrDesc, page, pageSize);
    }

}
