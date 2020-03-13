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
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 11:20
 * @Description:
 **/
public class JlztFbModel {

 /*   public Observable upload(String token, String uri) {
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
    }*/

    public Observable editHeadImg(String token, String uri) {
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


    public Observable commadd(String token, Map<String, String> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .commadd(token, searchRequest);
    }


}
