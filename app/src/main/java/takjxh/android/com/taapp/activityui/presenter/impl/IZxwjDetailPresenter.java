package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.SurveyDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 11:33
 * @Description:
 **/
public interface IZxwjDetailPresenter {


    void surveydetail(String token, String id);

    void surveyansweradd(String token, Map<String, Object> searchRequest);

    interface IView extends IBasePresenter.IView {

        void surveydetailSuccess(SurveyDetailBean data);
        void surveydetailFailed();

        void surveyansweraddSuccess(String data);
        void surveyansweraddFailed();
    }
}
