package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.OrgansBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 15:20
 * @Description:
 **/
public interface IZxjgPresenter {

    void organslist(String token, String key, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void organslistSuccess(List<OrgansBean.OrganListBean> data);
        void organslistFailed();

    }

}
