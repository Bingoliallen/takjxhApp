package takjxh.android.com.taapp.activityui.presenter.impl;


import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.KsnrBean;
import takjxh.android.com.taapp.activityui.bean.KsnrDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 14:39
 * @Description:
 **/
public interface IKsctckPresenter {

    void examdetail(String token, String id);

    interface IView extends IBasePresenter.IView {

        void examdetailSuccess(KsnrDetailBean data);
        void examdetailFailed();

    }


}
