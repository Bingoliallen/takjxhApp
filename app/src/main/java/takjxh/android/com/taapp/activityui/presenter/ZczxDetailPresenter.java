package takjxh.android.com.taapp.activityui.presenter;


import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.PolicyDetailBean;
import takjxh.android.com.taapp.activityui.model.ZczxDetailModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IZczxDetailPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 14:46
 * @Description:
 **/
public class ZczxDetailPresenter extends BasePresenter<IZczxDetailPresenter.IView, ZczxDetailModel> implements IZczxDetailPresenter {

    public ZczxDetailPresenter(IView view) {
        super(view);
    }

    @Override
    protected ZczxDetailModel createModel() {
        return new ZczxDetailModel();
    }


    @Override
    public void policydetail(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policydetail(token, id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<PolicyDetailBean.PolicyBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(PolicyDetailBean.PolicyBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().policydetailSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().policydetailFailed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<PolicyDetailBean<T>, T> {

        @Override
        public T call(PolicyDetailBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.policy;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}
