package takjxh.android.com.taapp.activityui.presenter;


import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyHelpDetailBean;
import takjxh.android.com.taapp.activityui.model.ZjsbQzDetailModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IZjsbQzDetailPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 9:50
 * @Description:
 **/
public class ZjsbQzDetailPresenter extends BasePresenter<IZjsbQzDetailPresenter.IView, ZjsbQzDetailModel> implements IZjsbQzDetailPresenter {

    public ZjsbQzDetailPresenter(IView view) {
        super(view);
    }

    @Override
    protected ZjsbQzDetailModel createModel() {
        return new ZjsbQzDetailModel();
    }


    @Override
    public void policyapplyhelpdetail(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyapplyhelpdetail(token, id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<PolicyApplyHelpDetailBean.HelpBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(PolicyApplyHelpDetailBean.HelpBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().policyapplyhelpdetailSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().policyapplyhelpdetailFailed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<PolicyApplyHelpDetailBean<T>, T> {

        @Override
        public T call(PolicyApplyHelpDetailBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.help;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}

