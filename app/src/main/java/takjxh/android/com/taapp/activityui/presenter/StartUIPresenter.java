package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.taapp.activityui.bean.StartBean;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.model.StartUIModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IStartUIPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import takjxh.android.com.taapp.net.NetSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 11:00
 * @Description:
 **/
public class StartUIPresenter extends BasePresenter<IStartUIPresenter.IView, StartUIModel> implements IStartUIPresenter {


    public StartUIPresenter(IView view) {
        super(view);
    }

    @Override
    protected StartUIModel createModel() {
        return new StartUIModel();
    }


    @Override
    public void startUI() {
        getCompositeSubscription().add(mModel.start()
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<String>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<String> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().startSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().startFailed();
                        }

                    }
                }));
    }

    @Override
    public void paramlist() {
        getCompositeSubscription()
                .add(mModel.paramlist()
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc1())
                        .subscribe(new NetSubscriber<SysParamBean>(getView().getContext()) {
                            @Override
                            public void onNext(SysParamBean data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().paramlistSuccess(data);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                if (isAttach()) {
                                    getView().paramlistFailed();
                                }
                            }
                        }));
    }

    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<StartBean<T>, T> {

        @Override
        public T call(StartBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.medias;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<SysParamBean<T>, T> {

        @Override
        public SysParamBean call(SysParamBean response) {
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
