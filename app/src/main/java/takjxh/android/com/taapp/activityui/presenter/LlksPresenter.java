package takjxh.android.com.taapp.activityui.presenter;


import rx.functions.Func1;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.ExamIndexBean;
import takjxh.android.com.taapp.activityui.model.LlksModle;
import takjxh.android.com.taapp.activityui.presenter.impl.ILlksPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 13:48
 * @Description:
 **/
public class LlksPresenter extends BasePresenter<ILlksPresenter.IView, LlksModle> implements ILlksPresenter {

    public LlksPresenter(IView view) {
        super(view);
    }

    @Override
    protected LlksModle createModel() {
        return new LlksModle();
    }


    @Override
    public void examindex(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.examindex(token)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<ExamIndexBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(ExamIndexBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().examindexSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().examindexFailed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<ExamIndexBean<T>, T> {

        @Override
        public ExamIndexBean call(ExamIndexBean response) {
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
