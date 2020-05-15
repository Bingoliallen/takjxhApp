package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.CompanyTypesBean;
import takjxh.android.com.taapp.activityui.bean.CompanysBean;
import takjxh.android.com.taapp.view.mulitmenuselect.Children;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 16:22
 * @Description:
 **/
public interface IDtzsPresenter {
    void tradetreelistt();
    void companytypelist(String token);

    void companyslist(String token, String unitNameLike,String trade, String page, String pageSize);

    interface IView extends IBasePresenter.IView {
        void tradetreelisttSuccess(List<Children> bean);
        void tradetreelisttFailed();


        void companytypelistSuccess(List<CompanyTypesBean.CompanyTypeBean> data);

        void companytypelistFailed();

        void companyslistSuccess(List<CompanysBean.CompanyBean> data);

        void companyslistFailed();

    }

}
