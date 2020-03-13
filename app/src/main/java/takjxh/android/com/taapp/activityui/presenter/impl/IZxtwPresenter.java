package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.CommDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 14:07
 * @Description:
 **/
public interface IZxtwPresenter {

    void commdetail(String token,String id);
    void questionadd(String token,Map<String, String> searchRequest);

    interface IView extends IBasePresenter.IView {
        void commdetailSuccess(CommDetailBean.DetailBean data);
        void commdetailFailed();

        void questionaddSuccess(String data);
        void questionaddFailed();

    }



}
