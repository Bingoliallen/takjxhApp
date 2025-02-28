package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.DownFileByApplyId;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 13:36
 * @Description:
 **/
public interface IWdZjsbPresenter {

    void policyapplylist(String token, String status, String page, String pageSize);

    void downFileByApplyId(String token,String applyId);
    void downSJByApplyId(String token,String applyId);

    interface IView extends IBasePresenter.IView {

        void policyapplylistSuccess(List<PolicyApplyBean.ApplyInfosBean> data);
        void policyapplylistFailed();


        void downFileByApplyIdSuccess(DownFileByApplyId data);
        void downFileByApplyIdFailed(String data);

        void downSJByApplyIdSuccess(DownFileByApplyId data);
        void downSJByApplyIdFailed(String data);

    }


}
