package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.StudyTypeBean;
import takjxh.android.com.taapp.activityui.bean.StudysBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 8:39
 * @Description:
 **/
public interface IKjllxxPresenter {

    void studytypelist(String token);

    void studyslist(String typeLike, String page, String pageSize);

    interface IView extends IBasePresenter.IView {

        void studytypelistSuccess(List<StudyTypeBean.StudyTypesBean> data);

        void studyslistSuccess(List<StudysBean.StudyListBean> data);

    }


}
