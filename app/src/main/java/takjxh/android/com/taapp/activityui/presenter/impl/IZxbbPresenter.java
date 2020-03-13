package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.OrderGenerateBean;
import takjxh.android.com.taapp.activityui.bean.ZxbbBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-08 11:21
 * @Description:
 **/
public interface IZxbbPresenter {

    void itemlist(String token);

    void ordergenerate(String token, Map<String, Object> searchRequest);

    interface IView extends IBasePresenter.IView {

        void itemlistSuccess(List<ZxbbBean.ApplyItemsBean> data);

        void ordergenerateSuccess(OrderGenerateBean data);
        void ordergenerateFailed();
    }



}
