package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 9:46
 * @Description:
 **/
public interface ITwDyPresenter {

    void qaansweradd(String token, Map<String, String> searchRequest);

    interface IView extends IBasePresenter.IView {

        void qaansweraddSuccess(String data);

        void qaansweraddFailed();

    }


}
