package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 11:22
 * @Description:
 **/
public interface IJlztFbPresenter {

    void commadd(String token,Map<String, String> searchRequest);
    void editHeadImg(String token, String uri);
    interface IView extends IBasePresenter.IView {

        void commaddSuccess(String data);
        void commaddFailed();

        void editHeadImgSuccess(UploadFileBean data);

        void editHeadImgError();
    }



}
