package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-07 9:12
 * @Description:
 **/
public class JfgzModel {

    public Observable scorerule(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .scorerule(token);
    }

}
