package takjxh.android.com.taapp.activityui.presenter.impl;


import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.view.mulitmenuselect.Contribute;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-10 20:39
 * @Description:
 **/
public interface IContributePresenter {

    void appindexcontribute(String token);

    void contributedone(String token, Map<String, Object> searchRequest);

    interface IView extends IBasePresenter.IView {

        void appindexcontributeSuccess(List<Contribute> data);
        void appindexcontributeFailed();

        void contributedoneSuccess(String data);
        void contributedoneFailed();

    }
}
