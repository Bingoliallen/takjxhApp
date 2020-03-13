package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.MessagesBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 13:17
 * @Description:
 **/
public interface IXxzzPresenter {

    void messagelist(String token, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void messagelistSuccess(List<MessagesBean.SysMessagesBean> data);

        void messagelistFailed();

    }


}
