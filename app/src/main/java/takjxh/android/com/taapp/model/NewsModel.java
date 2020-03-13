package takjxh.android.com.taapp.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 数据层
 *
 * @Author: libaibing
 * @Date: 2019-01-17 12:51
 * @Description:
 **/

public class NewsModel {

    public Observable hotNews(String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .hotNews(id);
    }

}
