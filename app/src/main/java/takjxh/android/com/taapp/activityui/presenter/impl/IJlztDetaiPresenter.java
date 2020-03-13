package takjxh.android.com.taapp.activityui.presenter.impl;


import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.CommDetailBean;
import takjxh.android.com.taapp.activityui.bean.CommQuestionBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 13:27
 * @Description:
 **/
public interface IJlztDetaiPresenter {

    void commdetail(String token,String id);
    void commquestionlist(String token, String topicId, String orderBy, String ascOrDesc, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void commdetailSuccess(CommDetailBean.DetailBean data);
        void commdetailFailed();

        void commquestionlistSuccess(List<CommQuestionBean.CommQuestionsBean> data, String orderBy);
        void commquestionlistFailed();
    }


}
