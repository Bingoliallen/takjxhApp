package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.QaLatestBean;
import takjxh.android.com.taapp.activityui.bean.QaListBean;
import takjxh.android.com.taapp.activityui.bean.QaQauserListBean;
import takjxh.android.com.taapp.activityui.bean.QafaqListBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 16:51
 * @Description:
 **/
public interface IXsywdyPresenter {

    void qafaqlist(String token,String page,String pageSize);
    void qalist(String token,String page, String pageSize);
    void qaqauserlist(String token,String page, String pageSize);
    void qalatest(String token);

    interface IView extends IBasePresenter.IView {

        void qafaqlistSuccess(QafaqListBean data);
        void qafaqlistFailed();


        void qalistSuccess(List<QaListBean.QasBean> data);
        void qalistFailed();

        void qaqauserlistSuccess(List<QaQauserListBean.QaUsersBean> data);
        void qaqauserlistFailed();

        void qalatestSuccess(QaLatestBean.DetailBean data);
        void qalatestFailed();

    }

}
