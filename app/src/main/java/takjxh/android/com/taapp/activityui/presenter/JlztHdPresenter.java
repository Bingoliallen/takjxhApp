package takjxh.android.com.taapp.activityui.presenter;


import java.util.List;

import rx.functions.Func1;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.QuestionAnswerListBean;
import takjxh.android.com.taapp.activityui.model.JlztHdModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IJlztHdPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 15:35
 * @Description:
 **/
public class JlztHdPresenter extends BasePresenter<IJlztHdPresenter.IView, JlztHdModel> implements IJlztHdPresenter {

    public JlztHdPresenter(IView view) {
        super(view);
    }

    @Override
    protected JlztHdModel createModel() {
        return new JlztHdModel();
    }


    @Override
    public void questionanswerlist(String token, String questionId, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.questionanswerlist(token, questionId, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<QuestionAnswerListBean.CommAnswersBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<QuestionAnswerListBean.CommAnswersBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().questionanswerlistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().questionanswerlistFailed();
                        }
                    }

                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<QuestionAnswerListBean<T>, T> {

        @Override
        public T call(QuestionAnswerListBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.commAnswers;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}

