package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-07 13:53
 * @Description:
 **/
public class ForgetPwdModel {

    public Observable updatepwd(String phone, Map<String, String> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .updatepwd(phone,searchRequest);
    }


    public Observable getCode(String phone) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .getCode(phone);
    }

}
