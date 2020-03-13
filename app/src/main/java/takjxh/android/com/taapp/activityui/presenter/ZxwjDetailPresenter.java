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
import takjxh.android.com.taapp.activityui.bean.SurveyDetailBean;
import takjxh.android.com.taapp.activityui.model.ZxwjDetailModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IZxwjDetailPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 11:33
 * @Description:
 **/
public class ZxwjDetailPresenter extends BasePresenter<IZxwjDetailPresenter.IView, ZxwjDetailModel> implements IZxwjDetailPresenter {

    public ZxwjDetailPresenter(IView view) {
        super(view);
    }

    @Override
    protected ZxwjDetailModel createModel() {
        return new ZxwjDetailModel();
    }

    @Override
    public void surveydetail(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.surveydetail(token, id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<SurveyDetailBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(SurveyDetailBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().surveydetailSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().surveydetailFailed();
                        }
                    }

                }));
    }

    @Override
    public void surveyansweradd(String token, Map<String, Object> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.surveyansweradd(token, searchRequest)
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

                            getView().surveyansweraddSuccess(mBean.resDes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().surveyansweraddFailed();
                        }
                    }

                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<SurveyDetailBean<T>, T> {

        @Override
        public SurveyDetailBean call(SurveyDetailBean response) {
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

