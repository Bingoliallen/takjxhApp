package takjxh.android.com.taapp.activityui.model;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 15:35
 * @Description:
 **/
public class JlztHdModel {

    public Observable questionanswerlist(String token, String questionId, String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .questionanswerlist(questionId, token, page, pageSize);
    }



}
