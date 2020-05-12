package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;
import java.util.Map;

import rx.functions.Func1;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.net.response.CommonResponse;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.ContributeBean;
import takjxh.android.com.taapp.activityui.model.ContributeModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IContributePresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import takjxh.android.com.taapp.view.mulitmenuselect.Contribute;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-10 20:38
 * @Description:
 **/
public class ContributePresenter  extends BasePresenter<IContributePresenter.IView, ContributeModel> implements IContributePresenter {

    public ContributePresenter(IView view) {
        super(view);
    }

    @Override
    protected ContributeModel createModel() {
        return new ContributeModel();
    }


    @Override
    public void appindexcontribute(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.appindexcontribute(token)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<Contribute>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<Contribute> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().appindexcontributeSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().appindexcontributeFailed();
                        }
                    }
                }));
    }

    @Override
    public void contributedone(String token, Map<String, Object> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.contributedone(token,searchRequest)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<CommonResponse>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(CommonResponse mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().contributedoneSuccess(mBean.resDes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().contributedoneFailed();
                        }
                    }
                }));
    }

    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<ContributeBean<T>, T> {

        @Override
        public T call(ContributeBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.list;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<CommonResponse<T>, T> {

        @Override
        public CommonResponse call(CommonResponse response) {
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
