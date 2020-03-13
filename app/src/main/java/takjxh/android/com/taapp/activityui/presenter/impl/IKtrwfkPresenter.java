package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 13:32
 * @Description:
 **/
public interface IKtrwfkPresenter {

    void issuefeedbackadd(String token, Map<String, Object> searchRequest);

    interface IView extends IBasePresenter.IView {

        void issuefeedbackaddSuccess(String data);
        void issuefeedbackaddFailed();
    }

}
