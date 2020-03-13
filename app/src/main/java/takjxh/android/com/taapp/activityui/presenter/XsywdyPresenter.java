package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import rx.functions.Func1;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.QaLatestBean;
import takjxh.android.com.taapp.activityui.bean.QaListBean;
import takjxh.android.com.taapp.activityui.bean.QaQauserListBean;
import takjxh.android.com.taapp.activityui.bean.QafaqListBean;
import takjxh.android.com.taapp.activityui.model.XsywdyModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IXsywdyPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 16:51
 * @Description:
 **/
public class XsywdyPresenter extends BasePresenter<IXsywdyPresenter.IView, XsywdyModel> implements IXsywdyPresenter {

    public XsywdyPresenter(IView view) {
        super(view);
    }

    @Override
    protected XsywdyModel createModel() {
        return new XsywdyModel();
    }

    @Override
    public void qafaqlist(String token,String page,String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.qafaqlist(token, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<QafaqListBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(QafaqListBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().qafaqlistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().qafaqlistFailed();
                        }
                    }

                }));
    }

    @Override
    public void qalist(String token, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.qalist(token,  page,  pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<List<QaListBean.QasBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<QaListBean.QasBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().qalistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().qalistFailed();
                        }
                    }

                }));
    }

    @Override
    public void qaqauserlist(String token, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.qaqauserlist(token,  page,  pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc2())
                .subscribe(new NetDialogSubscriber<List<QaQauserListBean.QaUsersBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<QaQauserListBean.QaUsersBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().qaqauserlistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().qaqauserlistFailed();
                        }
                    }

                }));
    }

    @Override
    public void qalatest(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.qalatest(token)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc3())
                .subscribe(new NetDialogSubscriber<QaLatestBean.DetailBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(QaLatestBean.DetailBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().qalatestSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().qalatestFailed();
                        }
                    }

                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<QafaqListBean<T>, T> {

        @Override
        public QafaqListBean call(QafaqListBean response) {
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
    public static class Response2DataFunc1<T> implements Func1<QaListBean<T>, T> {

        @Override
        public T call(QaListBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.qas;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc2<T> implements Func1<QaQauserListBean<T>, T> {

        @Override
        public T call(QaQauserListBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.qaUsers;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc3<T> implements Func1<QaLatestBean<T>, T> {

        @Override
        public T call(QaLatestBean<T> response) {
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


}

