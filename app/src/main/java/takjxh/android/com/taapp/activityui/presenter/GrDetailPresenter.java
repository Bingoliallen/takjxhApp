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
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.activityui.model.GrDetailModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IGrDetailPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-20 14:26
 * @Description:
 **/
public class GrDetailPresenter extends BasePresenter<IGrDetailPresenter.IView, GrDetailModel> implements IGrDetailPresenter {

    public GrDetailPresenter(IView view) {
        super(view);
    }

    @Override
    protected GrDetailModel createModel() {
        return new GrDetailModel();
    }


    @Override
    public void editHeadImg(String token, String uri) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription()
                .add(mModel.editHeadImg(token, uri)
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc())
                        .subscribe(new NetDialogSubscriber<UploadFileBean>(getView().getContext()) {
                            @Override
                            public int configuration() {
                                return DEFAULT;
                            }

                            @Override
                            public void onNext(UploadFileBean data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().editHeadImgSuccess(data);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                if (isAttach()) {
                                    getView().editHeadImgError();
                                }
                            }

                        }));


    }

    @Override
    public void usercancel(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription()
                .add(mModel.usercancel(token)
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc1())
                        .subscribe(new NetDialogSubscriber<CommonResponse>(getView().getContext()) {
                            @Override
                            public int configuration() {
                                return DEFAULT;
                            }

                            @Override
                            public void onNext(CommonResponse data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().usercancelSuccess(data.resDes);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                if (isAttach()) {
                                    getView().usercancelError();
                                }
                            }

                        }));
    }

    @Override
    public void updateinfo(String token, Map<String, Object> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription()
                .add(mModel.updateinfo(token,searchRequest)
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc1())
                        .subscribe(new NetDialogSubscriber<CommonResponse>(getView().getContext()) {
                            @Override
                            public int configuration() {
                                return DEFAULT;
                            }

                            @Override
                            public void onNext(CommonResponse data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().updateinfoSuccess(data.resDes);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                if (isAttach()) {
                                    getView().updateinfoError();
                                }
                            }

                        }));
    }

    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<UploadFileBean<T>, T> {

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

}

