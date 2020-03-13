package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.TaskDetailBean1;

/**
 * Created by Administrator on 2019/12/29.
 */

public interface IDzrwtxPresenter1 {

    void taskdetail(String token, String id);


    void taskansweradd(String token, Map<String, Object> searchRequest);

    interface IView extends IBasePresenter.IView {

        void taskdetailSuccess(TaskDetailBean1 data);
        void taskdetailFailed();
        void taskansweraddSuccess(String data);
        void taskansweraddFailed();
    }


}
