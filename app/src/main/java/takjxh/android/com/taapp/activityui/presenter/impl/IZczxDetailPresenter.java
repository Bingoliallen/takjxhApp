package takjxh.android.com.taapp.activityui.presenter.impl;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.PolicyDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 14:46
 * @Description:
 **/
public interface IZczxDetailPresenter {

    void policydetail(String token, String id);

    interface IView extends IBasePresenter.IView {

        void policydetailSuccess(PolicyDetailBean.PolicyBean data);

        void policydetailFailed();

    }



}
