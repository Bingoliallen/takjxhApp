package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.ContributeDetial;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-10 22:25
 * @Description:
 **/
public interface IContributeListPresenter {

    void contributeList(String token, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void contributeListSuccess(List<ContributeDetial> data);
        void contributeListFailed();

    }

}
