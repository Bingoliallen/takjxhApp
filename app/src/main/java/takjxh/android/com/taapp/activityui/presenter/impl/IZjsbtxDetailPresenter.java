package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.ApplyTypeBean;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 14:14
 * @Description:
 **/
public interface IZjsbtxDetailPresenter {
    void policyapplytypelist(String token);

    void policyapplydetail(String token, String id);

    interface IView extends IBasePresenter.IView {

        void policyapplytypelistSuccess(List<ApplyTypeBean.ApplyTypesBean> data);
        void policyapplytypelistFailed();

        void policyapplydetailSuccess(PolicyApplyDetailBean.ApplyInfoBean data);
        void policyapplydetailFailed();

    }

}
