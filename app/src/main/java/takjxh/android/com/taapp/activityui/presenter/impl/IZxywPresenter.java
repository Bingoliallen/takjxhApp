package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.QaTypeListBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 8:50
 * @Description:
 **/
public interface IZxywPresenter {

    void qatypelist(String token);
    void commquestionadd(String token,Map<String, String> searchRequest);

    interface IView extends IBasePresenter.IView {
        void qatypelistSuccess(List<QaTypeListBean.CommTypesBean> data);
        void qatypelistFailed();

        void commquestionaddSuccess(String data);
        void commquestionaddFailed();

    }




}
