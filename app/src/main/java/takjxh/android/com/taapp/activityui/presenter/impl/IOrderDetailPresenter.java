package takjxh.android.com.taapp.activityui.presenter.impl;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.OrderDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-08 15:50
 * @Description:
 **/
public interface IOrderDetailPresenter {

    void orderdetail(String token,String id);

    interface IView extends IBasePresenter.IView {

        void orderdetailSuccess(OrderDetailBean.OrderBean data);

    }

}
