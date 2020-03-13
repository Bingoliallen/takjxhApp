package takjxh.android.com.taapp.activityui.model;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.taapp.api.AppApi;

/**
 * Created by Administrator on 2020/3/7.
 */

public class UserExtsEditMode {

    public Observable userextadd(String token,Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .userextadd(token,searchRequest);
    }


    public Observable userextupdate(String token,Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .userextupdate(token,searchRequest);
    }

    public Observable userextdetail(String token, String id) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .userextdetail(id,  token);
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
