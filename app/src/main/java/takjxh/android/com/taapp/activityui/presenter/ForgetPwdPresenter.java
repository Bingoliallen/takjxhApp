package takjxh.android.com.taapp.activityui.presenter;

import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.net.response.CommonResponse;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.CodeBean;
import takjxh.android.com.taapp.activityui.model.ForgetPwdModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IForgetPwdPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-07 13:53
 * @Description:
 **/
public class ForgetPwdPresenter extends BasePresenter<IForgetPwdPresenter.IView, ForgetPwdModel> implements IForgetPwdPresenter {


    public ForgetPwdPresenter(IView view) {
        super(view);
    }

    @Override
    protected ForgetPwdModel createModel() {
        return new ForgetPwdModel();
    }


    @Override
    public void updatepwd(String token, Map<String, String> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription()
                .add(mModel.updatepwd(token, searchRequest)
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc())
                        .subscribe(new NetDialogSubscriber<CommonResponse>(getView().getContext()) {

                            @Override
                            public int configuration() {
                                return DEFAULT;
                            }

                            @Override
                            public void onNext(CommonResponse mBean) {
                                super.onNext(mBean);
                                if (isAttach()) {
                                    getView().updatepwdSuccess(mBean.resDes);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                if (isAttach()) {
                                    getView().updatepwdFailed();
                                }

                            }
                        }));
    }

    @Override
    public void getCode(String phone) {
        getCompositeSubscription()
                .add(mModel.getCode(phone)
                        .compose(RxHelper.io_main())
                        .map(new RxHelper.Response2DataFunc())
                        .subscribe(new NetDialogSubscriber<CodeBean>(getView().getContext()) {

                            @Override
                            public int configuration() {
                                return DEFAULT;
                            }

                            @Override
                            public void onNext(CodeBean userInfoBean) {
                                super.onNext(userInfoBean);
                                if (isAttach()) {
                                    getView().getCodeSuccess();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                if (isAttach()) {
                                    getView().getCodeFailed();
                                }

                            }
                        }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<CommonResponse<T>, T> {

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