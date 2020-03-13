package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.CompanyTypesBean;
import takjxh.android.com.taapp.activityui.bean.CompanysBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 16:22
 * @Description:
 **/
public interface IDtzsPresenter {

    void companytypelist(String token);

    void companyslist(String token, String type, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void companytypelistSuccess(List<CompanyTypesBean.CompanyTypeBean> data);

        void companytypelistFailed();

        void companyslistSuccess(List<CompanysBean.CompanyBean> data);

        void companyslistFailed();

    }

}
