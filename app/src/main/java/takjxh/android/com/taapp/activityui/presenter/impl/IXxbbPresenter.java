package takjxh.android.com.taapp.activityui.presenter.impl;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.ScoresBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-06 15:59
 * @Description:
 **/
public interface IXxbbPresenter {

    void scoreslist(String token, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void scoreslistSuccess(ScoresBean data);

    }


}
