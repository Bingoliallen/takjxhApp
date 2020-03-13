package takjxh.android.com.taapp.activityui.presenter;

import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.activityui.bean.LoginUIBean;
import takjxh.android.com.taapp.activityui.model.LoginUIModel;
import takjxh.android.com.taapp.activityui.presenter.impl.ILoginUIPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-23 15:34
 * @Description:
 **/
public class LoginUIPresenter extends BasePresenter<ILoginUIPresenter.IView, LoginUIModel> implements ILoginUIPresenter {


    public LoginUIPresenter(IView view) {
        super(view);
    }

    @Override
    protected LoginUIModel createModel() {
        return new LoginUIModel();
    }


    @Override
    public void loginUI(Map<String, String> searchRequest) {
        getCompositeSubscription().add(mModel.loginUI(searchRequest)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<LoginUIBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(LoginUIBean userInfoBean) {
                        super.onNext(userInfoBean);
                        if (isAttach()) {
                            ShareUtils.putString(BaseAppProfile.getApplication(), "token", userInfoBean.getToken());
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

                                ShareUtils.putString(BaseAppProfile.getApplication(), "station", userInfoBean.getUserInfo().getStation());
                                ShareUtils.putBoolean(BaseAppProfile.getApplication(), "isTeacher", userInfoBean.getUserInfo().isTeacher());



                            }
                            if(userInfoBean.getUserExts()!=null){
                                QbApplication.mBaseApplication.userExts=userInfoBean.getUserExts();
                            }

                            getView().loginSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().loginFailed();
                        }

                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<LoginUIBean<T>, T> {

        @Override
        public LoginUIBean call(LoginUIBean response) {
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
