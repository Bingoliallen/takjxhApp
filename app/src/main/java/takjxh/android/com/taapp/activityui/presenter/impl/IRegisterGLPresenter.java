package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-23 16:04
 * @Description:
 **/
public interface IRegisterGLPresenter {

    void register(Map<String, String> searchRequest);

    void getCode(String phone);
    void paramlist();

    void upload(String token, String uri);


    interface IView extends IBasePresenter.IView {

        void registerSuccess(String msg);
        void registerFailed();

        void getCodeSuccess();
        void getCodeFailed();

        void paramlistSuccess(SysParamBean bean);
        void paramlistFailed();

        void uploadSuccess(UploadFileBean data);
    }


}
