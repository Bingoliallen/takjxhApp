package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.XxshBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-07 11:55
 * @Description:
 **/
public interface IXxshPresenter {

    void auditlist(String token, String status, String page, String pageSize);

    void updateaudit(String token, Map<String, String> searchRequest);

    interface IView extends IBasePresenter.IView {

        void auditlistSuccess(List<XxshBean.AuditsBean> data);

        void updateauditSuccess(String data);
        void updateauditFailed();

    }


}
