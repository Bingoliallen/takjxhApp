package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.ApplyTypeBean;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyDetailBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 16:06
 * @Description:
 **/
public interface IZjsbtxPresenter {

    void policyapplytypelist(String token);

    void applyadddone(String token, Map<String, String> searchRequest);
    void applyupdatedone(String token, Map<String, String> searchRequest);

    void policyapplydetail(String token, String id);

    void upload(String token, String uri);

    interface IView extends IBasePresenter.IView {

        void policyapplydetailSuccess(PolicyApplyDetailBean.ApplyInfoBean data);
        void policyapplydetailFailed();

        void policyapplytypelistSuccess(List<ApplyTypeBean.ApplyTypesBean> data);

        void policyapplytypelistFailed();

        void applyadddoneSuccess(String data);
        void applyadddoneFailed();

        void applyupdatedoneSuccess(String data);
        void applyupdatedoneFailed();


        void uploadSuccess(UploadFileBean data);
    }







}
