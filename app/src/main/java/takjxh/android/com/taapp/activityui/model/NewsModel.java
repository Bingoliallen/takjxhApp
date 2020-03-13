package takjxh.android.com.taapp.activityui.model;


import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 14:40
 * @Description:
 **/
public class NewsModel {

    public Observable bannners() {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .bannners();
    }

    public Observable newslist(String typeLike, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .newslist(typeLike, page, pageSize);
    }


}
