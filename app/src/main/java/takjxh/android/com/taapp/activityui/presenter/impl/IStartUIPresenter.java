package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 11:01
 * @Description:
 **/
public interface IStartUIPresenter {

    void startUI();
    void paramlist();
    interface IView extends IBasePresenter.IView {

        void startSuccess(List<String> bean);
        void startFailed();

        void paramlistSuccess(SysParamBean bean);
        void paramlistFailed();

    }
}
