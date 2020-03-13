package takjxh.android.com.taapp.activityui.model;

import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-23 15:35
 * @Description:
 **/
public class LoginUIModel {

    public Observable loginUI(Map<String,String> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .loginUI(searchRequest);
    }


}
