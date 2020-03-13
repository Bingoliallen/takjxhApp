package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.SurveyListBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 10:07
 * @Description:
 **/
public interface IZxwjPresenter {

    void surveylist(String token, String createUnit, String status, String orderBy, String ascOrDesc,String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void surveylistSuccess(List<SurveyListBean.MarketSuveysBean> data);
        void surveylistFailed();

    }

    

}
