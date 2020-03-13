package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.BannnersBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-06 15:06
 * @Description:
 **/
public interface IZFPresenter {

    void bannnerslist();

    interface IView extends IBasePresenter.IView {

        void bannnerslistSuccess(List<BannnersBean.BannersBean> data);

    }



}
