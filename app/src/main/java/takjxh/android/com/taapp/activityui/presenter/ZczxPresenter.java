package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.PolicysBean;
import takjxh.android.com.taapp.activityui.model.ZczxModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IZczxPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 14:06
 * @Description:
 **/
public class ZczxPresenter extends BasePresenter<IZczxPresenter.IView, ZczxModel> implements IZczxPresenter {

    public ZczxPresenter(IView view) {
        super(view);
    }

    @Override
    protected ZczxModel createModel() {
        return new ZczxModel();
    }


    @Override
    public void policyslist(String token, String type,  String key, String createUnit, String trade, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.policyslist(token,type, key, createUnit, trade, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<PolicysBean.PolicyInfosBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<PolicysBean.PolicyInfosBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().policyslistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().policyslistFailed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<PolicysBean<T>, T> {

        @Override
        public T call(PolicysBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.policyInfos;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}