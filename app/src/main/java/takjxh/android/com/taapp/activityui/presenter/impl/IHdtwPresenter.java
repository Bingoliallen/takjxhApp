package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 15:04
 * @Description:
 **/
public interface IHdtwPresenter {

    void questionansweradd(String token, Map<String, String> searchRequest);

    interface IView extends IBasePresenter.IView {

        void questionansweraddSuccess(String data);

        void questionansweraddFailed();

    }


}
