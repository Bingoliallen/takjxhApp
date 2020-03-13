package takjxh.android.com.taapp.activityui.presenter;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.AdsDetailBean;
import takjxh.android.com.taapp.activityui.model.XsggDetialModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IXsggDetialPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 14:17
 * @Description:
 **/
public class XsggDetialPresenter  extends BasePresenter<IXsggDetialPresenter.IView, XsggDetialModel> implements IXsggDetialPresenter {

    public XsggDetialPresenter(IView view) {
        super(view);
    }

    @Override
    protected XsggDetialModel createModel() {
        return new XsggDetialModel();
    }

    @Override
    public void adsdetail(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.adsdetail(token, id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<AdsDetailBean.AdBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(AdsDetailBean.AdBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().adsdetailSuccess(mBean);
                        }
                    }


                }));
    }



    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<AdsDetailBean<T>, T> {

        @Override
        public T call(AdsDetailBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.ad;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }




}

