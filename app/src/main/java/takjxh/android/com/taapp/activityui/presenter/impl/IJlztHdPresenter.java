package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.QuestionAnswerListBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 15:35
 * @Description:
 **/
public interface IJlztHdPresenter {

    void questionanswerlist(String token, String questionId, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void questionanswerlistSuccess(List<QuestionAnswerListBean.CommAnswersBean> data);
        void questionanswerlistFailed();
    }


}
