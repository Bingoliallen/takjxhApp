package takjxh.android.com.taapp.presenter.impl;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.bean.NewsDetailBean;

/**
 * 名称定义
 *
 * @Author: libaibing
 * @Date: 2019-01-17 12:51
 * @Description:
 **/

public interface INewsPresenter {

    void hotNews(String id);


    interface IView extends IBasePresenter.IView {

        void hotNewsSuccess(NewsDetailBean data);


    }


}
