package takjxh.android.com.taapp.activityui.presenter.impl;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.PolicyIndexBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 13:50
 * @Description:
 **/
public interface IToZczxPresenter {

    void policyindex(String token);

    interface IView extends IBasePresenter.IView {

        void policyindexSuccess(PolicyIndexBean data);

        void policyindexFailed();

    }


}
