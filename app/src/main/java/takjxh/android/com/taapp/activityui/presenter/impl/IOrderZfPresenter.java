package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.OrderQueryBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-08 13:57
 * @Description:
 **/
public interface IOrderZfPresenter {

    void orderquery(String token, String id);

    void orderdone(String token, Map<String, String> searchRequest);


    interface IView extends IBasePresenter.IView {

        void orderquerySuccess(OrderQueryBean data);

        void orderqueryFailed();

        void orderdoneSuccess(String data);

        void orderdoneFailed();

    }

}
