package takjxh.android.com.taapp.activityui.model;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-10 22:27
 * @Description:
 **/
public class ContributeListModel {

    public Observable contributeList(String token, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .contributeList(token, page, pageSize);
    }


}
