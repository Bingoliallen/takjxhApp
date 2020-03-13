package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.HealthListBean;

/**
 * Created by Administrator on 2020/3/10.
 */

public interface IHealthDailyListPresenter {

    void healthlist(String token, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void healthlistSuccess(List<HealthListBean.HealthListVosBean> data);
        void healthlistFailed();
    }

}
