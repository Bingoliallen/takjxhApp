package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.FeedbackListBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-06 15:19
 * @Description:
 **/
public interface IJyfkPresenter {

    void feedback(String token,Map<String, String> searchRequest);
    void feedbacklist(String token, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void feedbackSuccess(String msg);
        void feedbackFailed();
        void feedbacklistSuccess(List<FeedbackListBean.UserFeedbackVosBean> data);
        void feedbacklistFailed();
    }

}
