package takjxh.android.com.taapp.activityui.presenter;


import java.util.List;

import rx.functions.Func1;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.CommDetailBean;
import takjxh.android.com.taapp.activityui.bean.CommQuestionBean;
import takjxh.android.com.taapp.activityui.model.JlztDetaiModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IJlztDetaiPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 13:27
 * @Description:
 **/
public class JlztDetaiPresenter extends BasePresenter<IJlztDetaiPresenter.IView, JlztDetaiModel> implements IJlztDetaiPresenter {

    public JlztDetaiPresenter(IView view) {
        super(view);
    }

    @Override
    protected JlztDetaiModel createModel() {
        return new JlztDetaiModel();
    }

    @Override
    public void commdetail(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.commdetail(token, id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<CommDetailBean.DetailBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(CommDetailBean.DetailBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().commdetailSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().commdetailFailed();
                        }
                    }

                }));
    }

    @Override
    public void commquestionlist(String token, String topicId, String orderBy, String ascOrDesc, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.commquestionlist(token, topicId, orderBy, ascOrDesc, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<List<CommQuestionBean.CommQuestionsBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<CommQuestionBean.CommQuestionsBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().commquestionlistSuccess(mBean,  orderBy);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().commquestionlistFailed();
                        }
                    }

                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<CommDetailBean<T>, T> {

        @Override
        public T call(CommDetailBean<T> response) {
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

    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<CommQuestionBean<T>, T> {

        @Override
        public T call(CommQuestionBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.commQuestions;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}
