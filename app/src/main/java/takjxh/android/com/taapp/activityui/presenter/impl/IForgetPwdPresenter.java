package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-07 13:53
 * @Description:
 **/
public interface IForgetPwdPresenter {

    void updatepwd(String token,Map<String, String> searchRequest);

    void getCode(String phone);


    interface IView extends IBasePresenter.IView {

        void updatepwdSuccess(String msg);
        void updatepwdFailed();

        void getCodeSuccess();
        void getCodeFailed();



    }



}
