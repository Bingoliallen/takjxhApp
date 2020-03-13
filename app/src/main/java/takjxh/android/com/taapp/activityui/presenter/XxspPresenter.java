package takjxh.android.com.taapp.activityui.presenter;

import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.net.response.CommonResponse;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.StudysDetailBean;
import takjxh.android.com.taapp.activityui.bean.TrainsDetailBean;
import takjxh.android.com.taapp.activityui.model.XxspModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IXxspPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 9:31
 * @Description:
 **/
public class XxspPresenter extends BasePresenter<IXxspPresenter.IView, XxspModel> implements IXxspPresenter {

    public XxspPresenter(IView view) {
        super(view);
    }

    @Override
    protected XxspModel createModel() {
        return new XxspModel();
    }

    @Override
    public void studysdetail(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.studysdetail(token, id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<StudysDetailBean.StudyBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(StudysDetailBean.StudyBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().studysdetailSuccess(mBean);
                        }
                    }


                }));
    }

    @Override
    public void trainsdetail(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.trainsdetail(token, id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<TrainsDetailBean.TrainBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(TrainsDetailBean.TrainBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().trainsdetailSuccess(mBean);
                        }
                    }


                }));
    }

    @Override
    public void studyreaddone(String token, Map<String, Object> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.studyreaddone(token, searchRequest)
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

                            getView().studyreaddoneSuccess(mBean.resDes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().studyreaddoneFailed();
                        }
                    }
                }));
    }

    @Override
    public void trainreaddone(String token, Map<String, Object> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.trainreaddone(token, searchRequest)
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

                            getView().trainreaddoneSuccess(mBean.resDes);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().trainreaddoneFailed();
                        }
                    }

                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<StudysDetailBean<T>, T> {

        @Override
        public T call(StudysDetailBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.study;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<TrainsDetailBean<T>, T> {

        @Override
        public T call(TrainsDetailBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.train;
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
