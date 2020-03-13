package takjxh.android.com.taapp.activityui.presenter.impl;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyHelpDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 9:50
 * @Description:
 **/
public interface IZjsbQzDetailPresenter {


    void policyapplyhelpdetail(String token, String id);

    interface IView extends IBasePresenter.IView {

        void policyapplyhelpdetailSuccess(PolicyApplyHelpDetailBean.HelpBean data);

        void policyapplyhelpdetailFailed();

    }

}
