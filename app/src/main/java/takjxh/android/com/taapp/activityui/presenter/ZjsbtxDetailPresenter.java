package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.ApplyTypeBean;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyDetailBean;
import takjxh.android.com.taapp.activityui.bean.PolicyapplyaddList;
import takjxh.android.com.taapp.activityui.model.ZjsbtxDetailModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IZjsbtxDetailPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 14:14
 * @Description:
 **/
public class ZjsbtxDetailPresenter extends BasePresenter<IZjsbtxDetailPresenter.IView, ZjsbtxDetailModel> implements IZjsbtxDetailPresenter {

    public ZjsbtxDetailPresenter(IView view) {
        super(view);
    }

    @Override
    protected ZjsbtxDetailModel createModel() {
        return new ZjsbtxDetailModel();
    }


    @Override
    public void policyapplytypelist(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyapplytypelist(token)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<List<ApplyTypeBean.ApplyTypesBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<ApplyTypeBean.ApplyTypesBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().policyapplytypelistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().policyapplytypelistFailed();
                        }
                    }
                }));
    }

    @Override
    public void policyapplydetail(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyapplydetail(token, id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<PolicyApplyDetailBean.ApplyInfoBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(PolicyApplyDetailBean.ApplyInfoBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().policyapplydetailSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().policyapplydetailFailed();
                        }
                    }
                }));
    }

    @Override
    public void policyapplydetail1(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyapplydetail1(token,id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc3())
                .subscribe(new NetDialogSubscriber<PolicyapplyaddList>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(PolicyapplyaddList mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().policyapplydetail1Success(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().policyapplydetail1Failed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<PolicyApplyDetailBean<T>, T> {

        @Override
        public T call(PolicyApplyDetailBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.applyInfo;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }

    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<ApplyTypeBean<T>, T> {

        @Override
        public T call(ApplyTypeBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.applyTypes;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }

    /**
     * 返回元素
     */
    public static class Response2DataFunc3<T> implements Func1<PolicyapplyaddList<T>, T> {

        @Override
        public PolicyapplyaddList call(PolicyapplyaddList response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }
}

