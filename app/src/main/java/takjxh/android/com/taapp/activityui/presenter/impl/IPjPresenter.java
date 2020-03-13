package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyEvaluateDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 11:03
 * @Description:
 **/
public interface IPjPresenter {

    void policyapplyevaluatedetail(String token, String id);
    void applyadddevaluate(String token, Map<String, Object> searchRequest);

    interface IView extends IBasePresenter.IView {

        void policyapplyevaluatedetailSuccess(PolicyApplyEvaluateDetailBean.DetailVoBean data);
        void policyapplyevaluatedetailFailed();

        void applyadddevaluateSuccess(String data);
        void applyadddevaluateFailed();

    }

}
