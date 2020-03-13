package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.taapp.activityui.bean.BannnersBean;
import takjxh.android.com.taapp.activityui.model.ZFModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IZFPresenter;
import takjxh.android.com.taapp.net.NetSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-06 15:05
 * @Description:
 **/
public class ZFPresenter  extends BasePresenter<IZFPresenter.IView, ZFModel> implements IZFPresenter {

    public ZFPresenter(IView view) {
        super(view);
    }


    @Override
    protected ZFModel createModel() {
        return new ZFModel();
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

