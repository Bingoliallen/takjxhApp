package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.CompanyTypesBean;
import takjxh.android.com.taapp.activityui.bean.CompanysBean;
import takjxh.android.com.taapp.activityui.model.DtzsModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IDtzsPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;
import takjxh.android.com.taapp.net.NetSubscriber;
import takjxh.android.com.taapp.view.mulitmenuselect.Children;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 16:22
 * @Description:
 **/
public class DtzsPresenter extends BasePresenter<IDtzsPresenter.IView, DtzsModel> implements IDtzsPresenter {

    public DtzsPresenter(IView view) {
        super(view);
    }

    @Override
    protected DtzsModel createModel() {
        return new DtzsModel();
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
    public void companytypelist(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.companytypelist(token)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<CompanyTypesBean.CompanyTypeBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<CompanyTypesBean.CompanyTypeBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().companytypelistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().companytypelistFailed();
                        }
                    }
                }));
    }


    @Override
    public void companyslist(String token,String unitNameLike,String trade, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.companyslist(token,  unitNameLike, trade, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<List<CompanysBean.CompanyBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<CompanysBean.CompanyBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().companyslistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().companyslistFailed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<CompanyTypesBean<T>, T> {

        @Override
        public T call(CompanyTypesBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.companyTypes;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<CompanysBean<T>, T> {

        @Override
        public T call(CompanysBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.companys;
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
