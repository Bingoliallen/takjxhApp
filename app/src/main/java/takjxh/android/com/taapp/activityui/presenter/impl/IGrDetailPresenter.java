package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-20 14:26
 * @Description:
 **/
public interface IGrDetailPresenter {

    void editHeadImg(String token, String uri);

    void usercancel(String token);


    void updateinfo(String token, Map<String, Object> searchRequest);

    interface IView extends IBasePresenter.IView {

        void editHeadImgSuccess(UploadFileBean data);

        void editHeadImgError();

        void usercancelSuccess(String data);
        void usercancelError();

        void updateinfoSuccess(String data);
        void updateinfoError();

    }


}
