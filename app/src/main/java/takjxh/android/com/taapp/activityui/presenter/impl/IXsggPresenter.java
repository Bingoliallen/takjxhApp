package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.AdsBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 13:31
 * @Description:
 **/
public interface IXsggPresenter {

    void adslist(String token, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void adslistSuccess(List<AdsBean.AdListBean> data);

    }


}
