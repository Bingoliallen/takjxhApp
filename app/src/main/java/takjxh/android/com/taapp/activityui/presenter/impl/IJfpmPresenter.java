package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.JfpmBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-06 16:23
 * @Description:
 **/
public interface IJfpmPresenter {

    void rankslist(String token,String type, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void rankslistSuccess(List<JfpmBean.UserScoresBean> data);

    }

}
