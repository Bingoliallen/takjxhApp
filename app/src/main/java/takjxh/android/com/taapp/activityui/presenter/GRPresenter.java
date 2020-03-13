package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.taapp.activityui.bean.BannnersBean;
import takjxh.android.com.taapp.activityui.model.GRModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IGRPresenter;
import takjxh.android.com.taapp.net.NetSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 17:11
 * @Description:
 **/
public class GRPresenter extends BasePresenter<IGRPresenter.IView, GRModel> implements IGRPresenter {

    public GRPresenter(IView view) {
        super(view);
    }


    @Override
    protected GRModel createModel() {
        return new GRModel();
    }

    @Override
    public void bannnerslist() {
        getCompositeSubscription()
                .add(mModel.bannners()
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc())
                        .subscribe(new NetSubscriber<List<BannnersBean.BannersBean>>(getView().getContext()) {
                            @Override
                            public void onNext(List<BannnersBean.BannersBean> data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().bannnerslistSuccess(data);
                                }
                            }
                        }));
    }



    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<BannnersBean<T>, T> {

        @Override
        public T call(BannnersBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.banners;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}

