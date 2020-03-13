package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.BannnersBean;
import takjxh.android.com.taapp.activityui.bean.NewsBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 14:37
 * @Description:
 **/
public interface INewsPresenter {

    void bannnerslist();

    void newslist(String typeLike, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void bannnerslistSuccess(List<BannnersBean.BannersBean> data);

        void newslistSuccess(List<NewsBean.NewsListBean> data);

    }


}
