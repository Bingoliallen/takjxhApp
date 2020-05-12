package takjxh.android.com.taapp.activityui.presenter.impl;

import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import takjxh.android.com.taapp.activityui.bean.ApplyTypeBean;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyDetailBean;
import takjxh.android.com.taapp.activityui.bean.PolicyapplyaddList;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.view.mulitmenuselect.Children;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 16:06
 * @Description:
 **/
public interface IZjsbtxPresenter {


    void tradetreelistt();

    void getPredictAmount(String token, Map<String, Object> searchRequest);


    void policyapplycheckApply(String token,String typeId);
    void policyapplydetail1(String token,String id);
    void policyapplyupdate(String token,String applyId);

    void applyadddtempone(String token, Map<String, Object> searchRequest);

    void policyapplyadd(String token,String typeId);
    void policyapplytypelist(String token);

    void applyadddone(String token, Map<String, Object> searchRequest);
    void applyupdatedone(String token, Map<String, Object> searchRequest);

    void policyapplydetail(String token, String id);

    void upload(String token, String uri);

    interface IView extends IBasePresenter.IView {
        void tradetreelisttSuccess(List<Children> bean);
        void tradetreelisttFailed();

        void policyapplycheckApplySuccess(String data);
        void policyapplycheckApplyFailed(String data);

        void policyapplydetail1Success(PolicyapplyaddList data);
        void policyapplydetail1Failed();
        void policyapplyupdateSuccess(PolicyapplyaddList data);
        void policyapplyupdateFailed();

        void policyapplyaddSuccess(PolicyapplyaddList data);
        void policyapplyaddFailed();

        void policyapplydetailSuccess(PolicyApplyDetailBean.ApplyInfoBean data);
        void policyapplydetailFailed();

        void policyapplytypelistSuccess(List<ApplyTypeBean.ApplyTypesBean> data);

        void policyapplytypelistFailed();


        void applyadddtemponeSuccess(String data);
        void applyadddtemponeFailed(String data);



        void getPredictAmountSuccess(String data);
        void getPredictAmountFailed(String data);

        void applyadddoneSuccess(String data);
        void applyadddoneFailed(String data);

        void applyupdatedoneSuccess(String data);
        void applyupdatedoneFailed(String data);


        void uploadSuccess(UploadFileBean data);
    }







}
