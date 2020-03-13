package takjxh.android.com.taapp.activityui.presenter;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.PolicyIndexBean;
import takjxh.android.com.taapp.activityui.model.ToZczxModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IToZczxPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 13:50
 * @Description:
 **/
public class ToZczxPresenter extends BasePresenter<IToZczxPresenter.IView, ToZczxModel> implements IToZczxPresenter {

    public ToZczxPresenter(IView view) {
        super(view);
    }

    @Override
    protected ToZczxModel createModel() {
        return new ToZczxModel();
    }


    @Override
    public void policyindex(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyindex(token)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<PolicyIndexBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(PolicyIndexBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().policyindexSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().policyindexFailed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<PolicyIndexBean<T>, T> {

        @Override
        public PolicyIndexBean call(PolicyIndexBean response) {
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