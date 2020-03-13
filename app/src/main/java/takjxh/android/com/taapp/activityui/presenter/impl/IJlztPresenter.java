package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.CommListBean;
import takjxh.android.com.taapp.activityui.bean.CommTypeListBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 10:47
 * @Description:
 **/
public interface IJlztPresenter {

    void commtypelist(String token);
    void commlist(String token,String type, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void commtypelistSuccess(List<CommTypeListBean.CommTypesBean> data);
        void commtypelistFailed();

        void commlistSuccess(List<CommListBean.CommTopicsBean> data);

    }



}
