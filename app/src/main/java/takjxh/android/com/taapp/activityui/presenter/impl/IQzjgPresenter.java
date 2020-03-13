package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyOrgansBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 14:49
 * @Description:
 **/
public interface IQzjgPresenter {

    void policyapplyorganslist(String token, String key, String page, String pageSize);

    void applyadddhelp(String token, Map<String, String> searchRequest);

    interface IView extends IBasePresenter.IView {

        void policyapplyorganslistSuccess(List<PolicyApplyOrgansBean.OrganListBean> data);

        void policyapplyorganslistFailed();

        void applyadddhelpSuccess(String data);

        void applyadddhelpFailed();

    }

}
