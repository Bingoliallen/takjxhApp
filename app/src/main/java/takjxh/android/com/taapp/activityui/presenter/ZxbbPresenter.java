package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.OrderGenerateBean;
import takjxh.android.com.taapp.activityui.bean.ZxbbBean;
import takjxh.android.com.taapp.activityui.model.ZxbbModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IZxbbPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-08 11:20
 * @Description:
 **/
public class ZxbbPresenter extends BasePresenter<IZxbbPresenter.IView, ZxbbModel> implements IZxbbPresenter {

    public ZxbbPresenter(IView view) {
        super(view);
    }

    @Override
    protected ZxbbModel createModel() {
        return new ZxbbModel();
    }


    @Override
    public void itemlist(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.itemlist(token)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<ZxbbBean.ApplyItemsBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<ZxbbBean.ApplyItemsBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().itemlistSuccess(mBean);
                        }
                    }


                }));
    }

    @Override
    public void ordergenerate(String token, Map<String, Object> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.ordergenerate(token,searchRequest)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<OrderGenerateBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(OrderGenerateBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().ordergenerateSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().ordergenerateFailed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<ZxbbBean<T>, T> {

        @Override
        public T call(ZxbbBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.applyItems;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }

    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<OrderGenerateBean<T>, T> {

        @Override
        public OrderGenerateBean call(OrderGenerateBean response) {
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

