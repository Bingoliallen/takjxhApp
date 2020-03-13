package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyHelpBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 10:21
 * @Description:
 **/
public interface IWdZjsbQzPresenter {

    void policyapplyhelplist(String token, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void policyapplyhelplistSuccess(List<PolicyApplyHelpBean.HelpListBean> data);
        void policyapplyhelplistFailed();

    }


}
