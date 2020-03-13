package takjxh.android.com.taapp.activityui.presenter;

import java.util.Map;

import rx.functions.Func1;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.net.response.CommonResponse;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.activityui.bean.UserExtDetailBean;
import takjxh.android.com.taapp.activityui.model.UserExtsEditMode;
import takjxh.android.com.taapp.activityui.presenter.impl.IUserExtsEditPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import takjxh.android.com.taapp.net.NetSubscriber;

/**
 * Created by Administrator on 2020/3/7.
 */

public class UserExtsEditPresenter extends BasePresenter<IUserExtsEditPresenter.IView, UserExtsEditMode> implements IUserExtsEditPresenter {

    public UserExtsEditPresenter(IView view) {
        super(view);
    }

    @Override
    protected UserExtsEditMode createModel() {
        return new UserExtsEditMode();
    }


    @Override
    public void userextdetail(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.userextdetail(token, id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<UserExtDetailBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(UserExtDetailBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().userextdetailSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().userextdetailFailed();
                        }
                    }
                }));
    }

    @Override
    public void userextadd(String token, Map<String, Object> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.userextadd(token, searchRequest)
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
                            getView().userextaddSuccess(mBean.resDes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().userextaddFailed();
                        }
                    }
                }));
    }

    @Override
    public void userextupdate(String token, Map<String, Object> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.userextupdate(token, searchRequest)
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
                            getView().userextupdateSuccess(mBean.resDes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().userextupdateFailed();
                        }
                    }
                }));
    }


    @Override
    public void paramlist() {
        getCompositeSubscription()
                .add(mModel.paramlist()
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc2())
                        .subscribe(new NetSubscriber<SysParamBean>(getView().getContext()) {
                            @Override
                            public void onNext(SysParamBean data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().paramlistSuccess(data);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                if (isAttach()) {
                                    getView().paramlistFailed();
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
                        .map(new Response2DataFunc3())
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
    public static class Response2DataFunc<T> implements Func1<UserExtDetailBean<T>, T> {

        @Override
        public UserExtDetailBean call(UserExtDetailBean response) {
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
    public static class Response2DataFunc2<T> implements Func1<SysParamBean<T>, T> {

        @Override
        public SysParamBean call(SysParamBean response) {
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
    public static class Response2DataFunc3<T> implements Func1<UploadFileBean<T>, T> {

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

}

