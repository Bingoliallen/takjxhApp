package takjxh.android.com.taapp.activityui.presenter.impl;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.ContributeDetial;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-10 23:01
 * @Description:
 **/
public interface IContributeDetialPresenter {

    void contributedetail(String token,String id);

    interface IView extends IBasePresenter.IView {
        void contributedetailSuccess(ContributeDetial data);
        void contributedetailFailed();


    }


}
