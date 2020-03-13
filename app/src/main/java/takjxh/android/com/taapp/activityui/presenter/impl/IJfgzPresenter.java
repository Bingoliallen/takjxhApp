package takjxh.android.com.taapp.activityui.presenter.impl;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.ScoreRuleBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-07 9:09
 * @Description:
 **/
public interface IJfgzPresenter {

    void scorerule(String token);

    interface IView extends IBasePresenter.IView {

        void scoreruleSuccess(ScoreRuleBean data);

    }


}
