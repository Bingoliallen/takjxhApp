package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.NewstypeBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 14:19
 * @Description:
 **/
public interface IZYPresenter {


    void newstypelist();

    interface IView extends IBasePresenter.IView {

        void newstypelistSuccess(List<NewstypeBean.NewsTypesBean> data);


    }



}
