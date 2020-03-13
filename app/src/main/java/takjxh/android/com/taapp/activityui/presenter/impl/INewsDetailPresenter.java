package takjxh.android.com.taapp.activityui.presenter.impl;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.NewsDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 15:24
 * @Description:
 **/
public interface INewsDetailPresenter {

    void newsdetail(String id);

    interface IView extends IBasePresenter.IView {

        void newsdetailSuccess(NewsDetailBean.NewsBean data);


    }


}
