package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import rx.functions.Func1;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.CommListBean;
import takjxh.android.com.taapp.activityui.bean.CommTypeListBean;
import takjxh.android.com.taapp.activityui.model.JlztModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IJlztPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 10:47
 * @Description:
 **/
public class JlztPresenter extends BasePresenter<IJlztPresenter.IView, JlztModel> implements IJlztPresenter {

    public JlztPresenter(IView view) {
        super(view);
    }

    @Override
    protected JlztModel createModel() {
        return new JlztModel();
    }

    @Override
    public void commtypelist(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.commtypelist(token)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<CommTypeListBean.CommTypesBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<CommTypeListBean.CommTypesBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().commtypelistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().commtypelistFailed();
                        }
                    }

                }));
    }

    @Override
    public void commlist(String token, String type, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.commlist(token, type, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<List<CommListBean.CommTopicsBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<CommListBean.CommTopicsBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().commlistSuccess(mBean);
                        }
                    }

                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<CommTypeListBean<T>, T> {

        @Override
        public T call(CommTypeListBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.commTypes;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<CommListBean<T>, T> {

        @Override
        public T call(CommListBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.commTopics;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}