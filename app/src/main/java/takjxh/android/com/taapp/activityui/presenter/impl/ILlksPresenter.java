package takjxh.android.com.taapp.activityui.presenter.impl;


import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.ExamIndexBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 13:48
 * @Description:
 **/
public interface ILlksPresenter {

    void examindex(String token);

    interface IView extends IBasePresenter.IView {

        void examindexSuccess(ExamIndexBean data);
        void examindexFailed();

    }

}
