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
import takjxh.android.com.taapp.activityui.bean.QaAnswerListBean;
import takjxh.android.com.taapp.activityui.bean.QaDetailBean;
import takjxh.android.com.taapp.activityui.model.TwDetailModel;
import takjxh.android.com.taapp.activityui.presenter.impl.ITwDetailPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 9:11
 * @Description:
 **/
public class TwDetailPresenter extends BasePresenter<ITwDetailPresenter.IView, TwDetailModel> implements ITwDetailPresenter {

    public TwDetailPresenter(IView view) {
        super(view);
    }

    @Override
    protected TwDetailModel createModel() {
        return new TwDetailModel();
    }

    @Override
    public void qadetail(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.qadetail(token, id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<QaDetailBean.DetailBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(QaDetailBean.DetailBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().qadetailSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().qadetailFailed();
                        }
                    }

                }));
    }

    @Override
    public void qaanswerlist(String token, String qaId, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.qaanswerlist(token, qaId, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<QaAnswerListBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(QaAnswerListBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().qaanswerlistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().qaanswerlistFailed();
                        }
                    }

                }));
    }

    @Override
    public void answeraccept(String token, Map<String, Object> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.answeraccept(token, searchRequest)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc2())
                .subscribe(new NetDialogSubscriber<CommonResponse>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(CommonResponse mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().answeracceptSuccess(mBean.resDes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().answeracceptFailed();
                        }
                    }

                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<QaDetailBean<T>, T> {

        @Override
        public T call(QaDetailBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.detail;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<QaAnswerListBean<T>, T> {

        @Override
        public QaAnswerListBean call(QaAnswerListBean response) {
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
    public static class Response2DataFunc2<T> implements Func1<CommonResponse<T>, T> {

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
