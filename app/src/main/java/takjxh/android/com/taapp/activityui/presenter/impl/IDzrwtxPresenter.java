package takjxh.android.com.taapp.activityui.presenter.impl;


import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.TaskDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-20 13:02
 * @Description:
 **/
public interface IDzrwtxPresenter {

    void taskdetail(String token, String id);


    void taskansweradd(String token, Map<String, Object> searchRequest);

    interface IView extends IBasePresenter.IView {

        void taskdetailSuccess(TaskDetailBean data);
        void taskdetailFailed();
        void taskansweraddSuccess(String data);
        void taskansweraddFailed();
    }

}
