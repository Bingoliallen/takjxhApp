package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.activityui.bean.UserExtDetailBean;

/**
 * Created by Administrator on 2020/3/7.
 */

public interface IUserExtsEditPresenter {


    void userextadd(String token, Map<String, Object> searchRequest);
    void userextupdate(String token, Map<String, Object> searchRequest);
    void userextdetail(String token, String id);

    void paramlist();
    void upload(String token, String uri);

    interface IView extends IBasePresenter.IView {


        void userextaddSuccess(String msg);
        void userextaddFailed();

        void userextupdateSuccess(String msg);
        void userextupdateFailed();

        void userextdetailSuccess(UserExtDetailBean data);
        void userextdetailFailed();


        void paramlistSuccess(SysParamBean bean);
        void paramlistFailed();

        void uploadSuccess(UploadFileBean data);

    }


}
