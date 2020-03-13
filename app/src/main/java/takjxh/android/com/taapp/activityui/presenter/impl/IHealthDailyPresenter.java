package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.HealthIndexBean;

/**
 * Created by Administrator on 2020/3/10.
 */

public interface IHealthDailyPresenter {

    void healthindex(String token);

    void healthadd(String token, Map<String, Object> searchRequest);

    interface IView extends IBasePresenter.IView {

        void healthindexSuccess(HealthIndexBean data);
        void healthindexFailed();



        void healthaddSuccess(String msg);
        void healthaddFailed();

    }



}
