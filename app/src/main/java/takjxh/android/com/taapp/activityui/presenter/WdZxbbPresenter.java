package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.WdZxbbBean;
import takjxh.android.com.taapp.activityui.model.WdZxbbModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IWdZxbbPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-07 14:44
 * @Description:
 **/
public class WdZxbbPresenter extends BasePresenter<IWdZxbbPresenter.IView, WdZxbbModel> implements IWdZxbbPresenter {

    public WdZxbbPresenter(IView view) {
        super(view);
    }

    @Override
    protected WdZxbbModel createModel() {
        return new WdZxbbModel();
    }


    @Override
    public void applyslist(String token, String key, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.applyslist(token,key, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<WdZxbbBean.ApplyOrdersBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<WdZxbbBean.ApplyOrdersBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().applyslistSuccess(mBean);
                        }
                    }


                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<WdZxbbBean<T>, T> {

        @Override
        public T call(WdZxbbBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.applyOrders;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}

