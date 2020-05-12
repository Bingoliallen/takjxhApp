package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.net.response.CommonResponse;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.ApplyTypeBean;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyDetailBean;
import takjxh.android.com.taapp.activityui.bean.PolicyapplyaddList;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.activityui.model.ZjsbtxModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IZjsbtxPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;
import takjxh.android.com.taapp.net.NetSubscriber;
import takjxh.android.com.taapp.view.mulitmenuselect.Children;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 16:06
 * @Description:
 **/
public class ZjsbtxPresenter extends BasePresenter<IZjsbtxPresenter.IView, ZjsbtxModel> implements IZjsbtxPresenter {

    public ZjsbtxPresenter(IView view) {
        super(view);
    }

    @Override
    protected ZjsbtxModel createModel() {
        return new ZjsbtxModel();
    }


    @Override
    public void tradetreelistt() {
        getCompositeSubscription()
                .add(mModel.tradetreelistt()
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc5())
                        .subscribe(new NetSubscriber<List<Children>>(getView().getContext()) {
                            @Override
                            public void onNext(List<Children> data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().tradetreelisttSuccess(data);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                if (isAttach()) {
                                    getView().tradetreelisttFailed();
                                }
                            }
                        }));
    }

    @Override
    public void getPredictAmount(String token, Map<String, Object> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.getPredictAmount(token,searchRequest)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetSubscriber<CommonResponse>(getView().getContext()) {
                    @Override
                    public void onNext(CommonResponse data) {
                        super.onNext(data);
                        if (isAttach()) {
                            getView().getPredictAmountSuccess(data.resDes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().getPredictAmountFailed(e.getMessage());
                        }
                    }
                }));

    }

    @Override
    public void policyapplycheckApply(String token, String typeId) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyapplycheckApply(token,typeId)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<CommonResponse>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(CommonResponse mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().policyapplycheckApplySuccess(mBean.resDes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().policyapplycheckApplyFailed(e.getMessage());
                        }
                    }
                }));
    }

    @Override
    public void policyapplydetail1(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyapplydetail1(token,id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc4())
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

    @Override
    public void policyapplyupdate(String token, String applyId) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyapplyupdate(token,applyId)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc4())
                .subscribe(new NetDialogSubscriber<PolicyapplyaddList>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(PolicyapplyaddList mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().policyapplyupdateSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().policyapplyupdateFailed();
                        }
                    }
                }));
    }

    @Override
    public void applyadddtempone(String token, Map<String, Object> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.applyadddtempone(token,searchRequest)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<CommonResponse>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(CommonResponse mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().applyadddtemponeSuccess(mBean.resDes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().applyadddtemponeFailed(e.getMessage());
                        }
                    }
                }));
    }

    @Override
    public void policyapplyadd(String token, String typeId) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyapplyadd(token,typeId)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc4())
                .subscribe(new NetDialogSubscriber<PolicyapplyaddList>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(PolicyapplyaddList mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().policyapplyaddSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().policyapplyaddFailed();
                        }
                    }
                }));
    }

    @Override
    public void policyapplytypelist(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyapplytypelist(token)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
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
    public void applyadddone(String token, Map<String, Object> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.applyadddone(token,searchRequest)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<CommonResponse>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(CommonResponse mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().applyadddoneSuccess(mBean.resDes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().applyadddoneFailed(e.getMessage());
                        }
                    }
                }));
    }

    @Override
    public void applyupdatedone(String token, Map<String, Object> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.applyupdatedone(token,searchRequest)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<CommonResponse>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(CommonResponse mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().applyupdatedoneSuccess(mBean.resDes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().applyupdatedoneFailed(e.getMessage());
                        }
                    }
                }));
    }

    @Override
    public void policyapplydetail(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyapplydetail(token, id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc3())
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
    public void upload(String token, String uri) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription()
                .add(mModel.upload(token, uri)
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc2())
                        .subscribe(new NetDialogSubscriber<UploadFileBean>(getView().getContext()) {
                            @Override
                            public int configuration() {
                                return DEFAULT;
                            }

                            @Override
                            public void onNext(UploadFileBean data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().uploadSuccess(data);
                                }
                            }
                        }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<ApplyTypeBean<T>, T> {

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
    public static class Response2DataFunc1<T> implements Func1<CommonResponse<T>, T> {

        @Override
        public CommonResponse call(CommonResponse response) {
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

    /**
     * 返回元素
     */
    public static class Response2DataFunc2<T> implements Func1<UploadFileBean<T>, T> {

        @Override
        public UploadFileBean call(UploadFileBean response) {
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


    /**
     * 返回元素
     */
    public static class Response2DataFunc3<T> implements Func1<PolicyApplyDetailBean<T>, T> {

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
    public static class Response2DataFunc4<T> implements Func1<PolicyapplyaddList<T>, T> {

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

    /**
     * 返回元素
     */
    public static class Response2DataFunc5 implements Func1<List<Children>, List<Children>> {

        @Override
        public List<Children> call(List<Children> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else{
                return response;
            }

        }
    }

}

