package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.taapp.activityui.bean.NewstypeBean;
import takjxh.android.com.taapp.activityui.model.ZYModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IZYPresenter;
import takjxh.android.com.taapp.net.NetSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 14:19
 * @Description:
 **/
public class ZYPresenter extends BasePresenter<IZYPresenter.IView, ZYModel> implements IZYPresenter {

    public ZYPresenter(IView view) {
        super(view);
    }


    @Override
    protected ZYModel createModel() {
        return new ZYModel();
    }

    @Override
    public void newstypelist() {

        getCompositeSubscription()
                .add(mModel.newstype()
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc())
                        .subscribe(new NetSubscriber<List<NewstypeBean.NewsTypesBean>>(getView().getContext()) {
                            @Override
                            public void onNext(List<NewstypeBean.NewsTypesBean> data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().newstypelistSuccess(data);
                                }
                            }
                        }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<NewstypeBean<T>, T> {

        @Override
        public T call(NewstypeBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.newsTypes;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}
