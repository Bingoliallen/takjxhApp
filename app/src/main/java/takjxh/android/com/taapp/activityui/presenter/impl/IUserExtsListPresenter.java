package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.base.UserExtsListBean;

/**
 * Created by Administrator on 2020/3/7.
 */

public interface IUserExtsListPresenter {

    void userExtsList(String token, String page, String pageSize);

    void userextdefault(String token, String id);
    void userextdel(String token, String id);




    interface IView extends IBasePresenter.IView {

        void userExtsListSuccess(List<UserExtsListBean.UserExtListVosBean> data);
        void userExtsListFailed();

        void userextdefaultSuccess(String data);
        void userextdefaultFailed();

        void userextdelSuccess(String data);
        void userextdelFailed();

    }


}
