package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.PolicysBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 14:06
 * @Description:
 **/
public interface IZczxPresenter {

    void policyslist(String token,  String type, String key, String createUnit, String trade, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void policyslistSuccess(List<PolicysBean.PolicyInfosBean> data);

        void policyslistFailed();

    }


}
