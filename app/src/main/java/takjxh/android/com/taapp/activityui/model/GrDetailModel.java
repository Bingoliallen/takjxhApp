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
 * @Date: 2019-11-20 14:25
 * @Description:
 **/
public class GrDetailModel {

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
                .editHeadImg1(body);
    }

    public Observable usercancel(String token) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .usercancel(token);
    }


    public Observable updateinfo(String token,Map<String, Object> searchRequest) {
        return BaseAppProfile.app_client.getApi(AppApi.class)
                .updateinfo(token,searchRequest);
    }

}
