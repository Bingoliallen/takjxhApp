package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-23 15:34
 * @Description:
 **/
public interface ILoginUIPresenter {


    void loginUI(Map<String,String> searchRequest);

    interface IView extends IBasePresenter.IView {

        void loginSuccess();

        void loginFailed();
    }

}
