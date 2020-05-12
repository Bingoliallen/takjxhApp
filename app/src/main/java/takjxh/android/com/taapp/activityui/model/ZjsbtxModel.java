package takjxh.android.com.taapp.activityui.model;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;
import rx.Observable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 16:04
 * @Description:
 **/
public class ZjsbtxModel {

    public Observable policyapplycheckApply(String token, String typeId) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyapplycheckApply(typeId, token);
    }

    public Observable policyapplydetail1(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyapplydetail1(id, token);
    }

    public Observable policyapplyupdate(String token, String applyId) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyapplyupdate(applyId, token);
    }

    public Observable policyapplyadd(String token, String typeId) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyapplyadd(typeId, token);
    }


    public Observable policyapplydetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyapplydetail(id, token);
    }


    public Observable policyapplytypelist(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .policyapplytypelist(token);
    }


    public Observable applyadddtempone(String token, Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .applyadddtempone(token, searchRequest);
    }


    public Observable applyadddone(String token, Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .applyadddone(token, searchRequest);
    }

    public Observable getPredictAmount(String token, Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .getPredictAmount(token, searchRequest);
    }



    public Observable applyupdatedone(String token, Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .applyupdatedone(token, searchRequest);
    }

    public Observable tradetreelistt() {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .tradetreelistt();
    }

    public Observable upload(String token, String uri) {
        File file = null;
        try {
            file = new File(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .upload(body);
    }

}
