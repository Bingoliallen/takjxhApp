package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.WdZxbbBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-07 14:44
 * @Description:
 **/
public interface IWdZxbbPresenter {

    void applyslist(String token,String kay,  String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void applyslistSuccess(List<WdZxbbBean.ApplyOrdersBean> data);

    }


}
