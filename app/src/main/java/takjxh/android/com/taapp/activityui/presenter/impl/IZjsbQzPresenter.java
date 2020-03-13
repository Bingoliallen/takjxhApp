package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyHelpBean2;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 9:14
 * @Description:
 **/
public interface IZjsbQzPresenter {

    void policyapplyhelplist2(String token, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void policyapplyhelplist2Success(List<PolicyApplyHelpBean2.HelpListBean> data);

        void policyapplyhelplist2Failed();


    }

}
