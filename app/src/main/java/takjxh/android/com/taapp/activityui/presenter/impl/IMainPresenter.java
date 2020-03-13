package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;

/**
 * Created by Administrator on 2020/3/10.
 */

public interface IMainPresenter {

    void registrationid(Map<String, Object> searchRequest);
    interface IView extends IBasePresenter.IView {

        void registrationidSuccess(String msg);

        void registrationidFailed();
    }
}
