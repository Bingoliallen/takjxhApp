package takjxh.android.com.taapp.activityui.model;

import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 10:07
 * @Description:
 **/
public class ZxwjModel {

    public Observable surveylist(String token, String createUnit, String status, String orderBy, String ascOrDesc,String page, String pageSize) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .surveylist(token, createUnit,status,ascOrDesc, page, pageSize);
    }



}
