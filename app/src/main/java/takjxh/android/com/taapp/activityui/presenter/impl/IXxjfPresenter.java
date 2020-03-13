package takjxh.android.com.taapp.activityui.presenter.impl;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.XxjfBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-07 11:12
 * @Description:
 **/
public interface IXxjfPresenter {

    void myscore(String token);

    interface IView extends IBasePresenter.IView {

        void myscoreSuccess(XxjfBean data);

    }


}
