package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 14:20
 * @Description:
 **/
public class ZYModel {

    public Observable newstype() {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .newstype();
    }

}
