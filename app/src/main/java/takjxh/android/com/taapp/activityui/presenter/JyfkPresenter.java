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
import takjxh.android.com.taapp.activityui.bean.FeedbackListBean;
import takjxh.android.com.taapp.activityui.model.JyfkModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IJyfkPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-06 15:19
 * @Description:
 **/
public class JyfkPresenter extends BasePresenter<IJyfkPresenter.IView, JyfkModel> implements IJyfkPresenter {


    public JyfkPresenter(IView view) {
        super(view);
    }

    @Override
    protected JyfkModel createModel() {
        return new JyfkModel();
    }


    @Override
    public void feedback(String token, Map<String, String> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription()
                .add(mModel.feedback(token, searchRequest)
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
                                    getView().feedbackSuccess(mBean.resDes);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                if (isAttach()) {
                                    getView().feedbackFailed();
                                }

                            }
                        }));
    }

    @Override
    public void feedbacklist(String token, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.feedbacklist(token, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<List<FeedbackListBean.UserFeedbackVosBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<FeedbackListBean.UserFeedbackVosBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().feedbacklistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().feedbacklistFailed();
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


    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<FeedbackListBean<T>, T> {

        @Override
        public T call(FeedbackListBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.userFeedbackVos;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}

