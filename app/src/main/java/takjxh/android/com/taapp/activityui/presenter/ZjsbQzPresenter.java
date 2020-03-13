package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyHelpBean2;
import takjxh.android.com.taapp.activityui.model.ZjsbQzModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IZjsbQzPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 9:14
 * @Description:
 **/
public class ZjsbQzPresenter extends BasePresenter<IZjsbQzPresenter.IView, ZjsbQzModel> implements IZjsbQzPresenter {

    public ZjsbQzPresenter(IView view) {
        super(view);
    }

    @Override
    protected ZjsbQzModel createModel() {
        return new ZjsbQzModel();
    }


    @Override
    public void policyapplyhelplist2(String token, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyapplyhelplist2(token, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<PolicyApplyHelpBean2.HelpListBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<PolicyApplyHelpBean2.HelpListBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().policyapplyhelplist2Success(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().policyapplyhelplist2Failed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<PolicyApplyHelpBean2<T>, T> {

        @Override
        public T call(PolicyApplyHelpBean2<T> response) {
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


