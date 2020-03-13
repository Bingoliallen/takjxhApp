package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyBean;
import takjxh.android.com.taapp.activityui.model.WdZjsbModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IWdZjsbPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 13:36
 * @Description:
 **/
public class WdZjsbPresenter extends BasePresenter<IWdZjsbPresenter.IView, WdZjsbModel> implements IWdZjsbPresenter {

    public WdZjsbPresenter(IView view) {
        super(view);
    }

    @Override
    protected WdZjsbModel createModel() {
        return new WdZjsbModel();
    }


    @Override
    public void policyapplylist(String token, String status, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyapplylist(token, status, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<PolicyApplyBean.ApplyInfosBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<PolicyApplyBean.ApplyInfosBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().policyapplylistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().policyapplylistFailed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<PolicyApplyBean<T>, T> {

        @Override
        public T call(PolicyApplyBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.applyInfos;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}

