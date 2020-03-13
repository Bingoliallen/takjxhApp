package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.TaskListBean;
import takjxh.android.com.taapp.activityui.bean.TaskTypeListBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-20 11:17
 * @Description:
 **/
public interface IToBbPresenter {

    void tasktypelist(String token);

    void tasklist(String token, String type, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void tasktypelistSuccess(List<TaskTypeListBean.CommTypesBean> data);
        void tasktypelistFailed();

        void tasklistSuccess(List<TaskListBean.ReportTasksBean> data, String type);
        void tasklistFailed();


    }


}
