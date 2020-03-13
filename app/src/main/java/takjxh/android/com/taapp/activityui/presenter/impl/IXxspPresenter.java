package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.StudysDetailBean;
import takjxh.android.com.taapp.activityui.bean.TrainsDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 9:31
 * @Description:
 **/
public interface IXxspPresenter {

    void studysdetail(String token, String id);
    void trainsdetail(String token, String id);

    void studyreaddone(String token, Map<String, Object> searchRequest);
    void trainreaddone(String token, Map<String, Object> searchRequest);


    interface IView extends IBasePresenter.IView {

        void studysdetailSuccess(StudysDetailBean.StudyBean data);
        void trainsdetailSuccess(TrainsDetailBean.TrainBean data);

        void studyreaddoneSuccess(String data);
        void trainreaddoneSuccess(String data);

        void studyreaddoneFailed();
        void trainreaddoneFailed();


    }


}
