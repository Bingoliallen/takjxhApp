package takjxh.android.com.taapp.activityui.presenter.impl;


import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.ExamAddBean;
import takjxh.android.com.taapp.activityui.bean.KsnrBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 14:05
 * @Description:
 **/
public interface IKsnrPresenter {

    void examlist(String token, String type, String page, String pageSize);

    void examadd(String token, Map<String, Object> searchRequest);

    interface IView extends IBasePresenter.IView {

        void examlistSuccess(KsnrBean data);
        void examlistFailed();

        void examaddSuccess(ExamAddBean data);
        void examaddFailed();
    }


}
