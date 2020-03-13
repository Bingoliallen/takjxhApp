package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.IssueListBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 12:52
 * @Description:
 **/
public interface IKtrwListPresenter {

    void issuelist(String token, String type, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void issuelistSuccess(List<IssueListBean.UserIssueTasksBean> data);
        void issuelistFailed();


    }

}
