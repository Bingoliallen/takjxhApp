package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.activityui.bean.CodeBean;
import takjxh.android.com.taapp.activityui.bean.RegisterBean;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.activityui.model.RegisterGLModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IRegisterGLPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import takjxh.android.com.taapp.net.NetSubscriber;
import rx.functions.Func1;
import takjxh.android.com.taapp.view.mulitmenuselect.Children;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-23 16:04
 * @Description:
 **/
public class RegisterGLPresenter extends BasePresenter<IRegisterGLPresenter.IView, RegisterGLModel> implements IRegisterGLPresenter {


    public RegisterGLPresenter(IView view) {
        super(view);
    }

    @Override
    protected RegisterGLModel createModel() {
        return new RegisterGLModel();
    }


    @Override
    public void register(Map<String, String> searchRequest) {
        getCompositeSubscription()
                .add(mModel.register(searchRequest)
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc())
                        .subscribe(new NetDialogSubscriber<RegisterBean>(getView().getContext()) {

                            @Override
                            public int configuration() {
                                return DEFAULT;
                            }

                            @Override
                            public void onNext(RegisterBean userInfoBean) {
                                super.onNext(userInfoBean);
                                if (isAttach()) {
                                    /*ShareUtils.putString(BaseAppProfile.getApplication(), "token", userInfoBean.getToken());
                                    if(userInfoBean.getUserInfo()!=null){
                                        ShareUtils.putString(BaseAppProfile.getApplication(), "jchat_userPassword", userInfoBean.getUserInfo().getPassword());

                                        ShareUtils.putString(BaseAppProfile.getApplication(), "userId", userInfoBean.getUserInfo().getId());
                                        ShareUtils.putString(BaseAppProfile.getApplication(), "userName", userInfoBean.getUserInfo().getUsername());
                                        ShareUtils.putString(BaseAppProfile.getApplication(), "name", userInfoBean.getUserInfo().getName());
                                        ShareUtils.putString(BaseAppProfile.getApplication(), "mobilePhone", userInfoBean.getUserInfo().getMobilePhone());
                                        ShareUtils.putString(BaseAppProfile.getApplication(), "company", userInfoBean.getUserInfo().getCompany());
                                        ShareUtils.putString(BaseAppProfile.getApplication(), "type", userInfoBean.getUserInfo().getType());
                                        ShareUtils.putString(BaseAppProfile.getApplication(), "score", userInfoBean.getUserInfo().getScore());
                                        ShareUtils.putBoolean(BaseAppProfile.getApplication(), "isOpenMsg", userInfoBean.getUserInfo().isIsOpenMsg());
                                        ShareUtils.putBoolean(BaseAppProfile.getApplication(), "isOpenVoice", userInfoBean.getUserInfo().isIsOpenVoice());
                                        ShareUtils.putString(BaseAppProfile.getApplication(), "avatar", userInfoBean.getUserInfo().getCover());

                                        ShareUtils.putBoolean(BaseAppProfile.getApplication(), "isTeacher", userInfoBean.getUserInfo().isTeacher());

                                    }
                                    if(userInfoBean.getUserExts()!=null){
                                        QbApplication.mBaseApplication.userExts=userInfoBean.getUserExts();
                                    }*/
                                    getView().registerSuccess(userInfoBean.resDes);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                if (isAttach()) {
                                    getView().registerFailed();
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

    @Override
    public void paramlist() {
        getCompositeSubscription()
                .add(mModel.paramlist()
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc1())
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
    public void tradetreelistt() {
        getCompositeSubscription()
                .add(mModel.tradetreelistt()
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc3())
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
    public static class Response2DataFunc<T> implements Func1<RegisterBean<T>, T> {

        @Override
        public RegisterBean call(RegisterBean response) {
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
    public static class Response2DataFunc1<T> implements Func1<SysParamBean<T>, T> {

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
    public static class Response2DataFunc3 implements Func1<List<Children>, List<Children>> {

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
