package takjxh.android.com.taapp.activityui.presenter.impl;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.AdsDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 14:17
 * @Description:
 **/
public interface IXsggDetialPresenter {

    void adsdetail(String token, String id);

    interface IView extends IBasePresenter.IView {

        void adsdetailSuccess(AdsDetailBean.AdBean data);

    }


}
