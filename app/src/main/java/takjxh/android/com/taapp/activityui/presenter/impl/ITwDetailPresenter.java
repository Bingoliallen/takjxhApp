package takjxh.android.com.taapp.activityui.presenter.impl;


import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.QaAnswerListBean;
import takjxh.android.com.taapp.activityui.bean.QaDetailBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 9:11
 * @Description:
 **/
public interface ITwDetailPresenter {

    void qadetail(String token, String id);


    void qaanswerlist(String token, String qaId, String page, String pageSize);

    void answeraccept(String token, Map<String, Object> searchRequest);


    interface IView extends IBasePresenter.IView {

        void qadetailSuccess(QaDetailBean.DetailBean data);
        void qadetailFailed();

        void qaanswerlistSuccess(QaAnswerListBean data);
        void qaanswerlistFailed();

        void answeracceptSuccess(String data);
        void answeracceptFailed();

    }


}
