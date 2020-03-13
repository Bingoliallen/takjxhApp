package takjxh.android.com.taapp.presenter.impl;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.bean.CenterBean;
import takjxh.android.com.taapp.bean.UserInfoBean;

/**
 * 名称定义
 *
 * @Author: libaibing
 * @Date: 2019-01-22 12:51
 * @Description:
 **/

public interface IMinePresenter {
    void getUserInfo(String token);

    void getCenter(String token);

    void loginOut(String token);

    interface IView extends IBasePresenter.IView {
        void getUserInfoSuccess(UserInfoBean data);

        void getCenterSuccess(CenterBean data);

        void loginOutSuccess();

        void loginOutError();

    }


}
