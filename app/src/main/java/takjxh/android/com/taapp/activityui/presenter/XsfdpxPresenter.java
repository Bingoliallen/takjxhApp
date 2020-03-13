package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.TrainTypeBean;
import takjxh.android.com.taapp.activityui.bean.TrainsBean;
import takjxh.android.com.taapp.activityui.model.XsfdpxModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IXsfdpxPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import takjxh.android.com.taapp.net.NetSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 11:18
 * @Description:
 **/
public class XsfdpxPresenter extends BasePresenter<IXsfdpxPresenter.IView, XsfdpxModel> implements IXsfdpxPresenter {

    public XsfdpxPresenter(IView view) {
        super(view);
    }

    @Override
    protected XsfdpxModel createModel() {
        return new XsfdpxModel();
    }


    @Override
    public void traintypelist(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription()
                .add(mModel.traintype(token)
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc())
                        .subscribe(new NetSubscriber<List<TrainTypeBean.TrainTypesBean>>(getView().getContext()) {
                            @Override
                            public void onNext(List<TrainTypeBean.TrainTypesBean> data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().traintypelistSuccess(data);
                                }
                            }
                        }));
    }

    @Override
    public void trainslist(String typeLike, String page, String pageSize) {
        String token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.trainslist(token,typeLike,page,pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<List<TrainsBean.TrainListBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<TrainsBean.TrainListBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().trainslistSuccess(mBean);
                        }
                    }


                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<TrainTypeBean<T>, T> {

        @Override
        public T call(TrainTypeBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.trainTypes;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<TrainsBean<T>, T> {

        @Override
        public T call(TrainsBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.trainList;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }





}
