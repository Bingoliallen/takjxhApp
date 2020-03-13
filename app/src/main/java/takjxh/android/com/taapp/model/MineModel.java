package takjxh.android.com.taapp.model;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 数据层
 *
 * @Author: libaibing
 * @Date: 2019-01-22 12:51
 * @Description:
 **/

public class MineModel {


    public Observable getCenter(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .getCenter(token);
    }

    public Observable getUserInfo(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .getUserInfo(token);
    }

    public Observable loginOut(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .loginOut(token);
    }


}
