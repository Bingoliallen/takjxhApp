package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyHelpBean;
import takjxh.android.com.taapp.activityui.model.WdZjsbQzModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IWdZjsbQzPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 10:20
 * @Description:
 **/
public class WdZjsbQzPresenter extends BasePresenter<IWdZjsbQzPresenter.IView, WdZjsbQzModel> implements IWdZjsbQzPresenter {

    public WdZjsbQzPresenter(IView view) {
        super(view);
    }

    @Override
    protected WdZjsbQzModel createModel() {
        return new WdZjsbQzModel();
    }


    @Override
    public void policyapplyhelplist(String token, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyapplyhelplist(token, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<PolicyApplyHelpBean.HelpListBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<PolicyApplyHelpBean.HelpListBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().policyapplyhelplistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().policyapplyhelplistFailed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<PolicyApplyHelpBean<T>, T> {

        @Override
        public T call(PolicyApplyHelpBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.helpList;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}
