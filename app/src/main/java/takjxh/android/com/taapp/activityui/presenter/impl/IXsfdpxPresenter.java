package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.TrainTypeBean;
import takjxh.android.com.taapp.activityui.bean.TrainsBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 11:19
 * @Description:
 **/
public interface IXsfdpxPresenter {

    void traintypelist(String token);

    void trainslist(String typeLike, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void traintypelistSuccess(List<TrainTypeBean.TrainTypesBean> data);

        void trainslistSuccess(List<TrainsBean.TrainListBean> data);

    }


}
