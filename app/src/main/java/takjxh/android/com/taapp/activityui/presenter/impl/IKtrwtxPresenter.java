package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.IssueDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 13:09
 * @Description:
 **/
public interface IKtrwtxPresenter {

    void issuedetail(String token, String id);


    void issueansweradd(String token, Map<String, Object> searchRequest);

    interface IView extends IBasePresenter.IView {

        void issuedetailSuccess(IssueDetailBean data);
        void issuedetailFailed();
        void issueansweraddSuccess(String data);
        void issueansweraddFailed();
    }


}
