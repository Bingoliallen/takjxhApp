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
 * @Date: 2019-10-23 16:05
 * @Description:
 **/
public class RegisterGLModel {

    public Observable register(Map<String, String> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .register(searchRequest);
    }


    public Observable getCode(String phone) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .getCode(phone);
    }


    public Observable tradetreelistt() {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .tradetreelistt();
    }


    public Observable paramlist() {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .paramlist();
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
