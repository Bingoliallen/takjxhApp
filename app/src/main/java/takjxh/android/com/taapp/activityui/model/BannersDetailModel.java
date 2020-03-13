package takjxh.android.com.taapp.activityui.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 16:23
 * @Description:
 **/
public class BannersDetailModel {

    public Observable bannnerdetail(String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .bannnerdetail(id);
    }

}
