package takjxh.android.com.taapp.activityui.presenter.impl;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.BannnerDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 16:23
 * @Description:
 **/
public interface IBannersDetailPresenter {

    void bannnerdetail(String id);

    interface IView extends IBasePresenter.IView {

        void bannnerdetailSuccess(BannnerDetailBean.DetailBean data);


    }



}
