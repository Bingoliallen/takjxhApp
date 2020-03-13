package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 8:39
 * @Description:
 **/
public class KjllxxModel {

    public Observable studytype(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .studytype(token);
    }

    public Observable studyslist(String token,String typeLike, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .studyslist(typeLike, token,page, pageSize);
    }



}
