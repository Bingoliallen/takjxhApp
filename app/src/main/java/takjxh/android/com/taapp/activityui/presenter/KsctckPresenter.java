package takjxh.android.com.taapp.activityui.presenter;


import rx.functions.Func1;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.KsnrDetailBean;
import takjxh.android.com.taapp.activityui.model.KsctckModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IKsctckPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 14:39
 * @Description:
 **/
public class KsctckPresenter extends BasePresenter<IKsctckPresenter.IView, KsctckModel> implements IKsctckPresenter {

    public KsctckPresenter(IView view) {
        super(view);
    }

    @Override
    protected KsctckModel createModel() {
        return new KsctckModel();
    }


    @Override
    public void examdetail(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.examdetail(token, id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<KsnrDetailBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(KsnrDetailBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().examdetailSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().examdetailFailed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<KsnrDetailBean<T>, T> {

        @Override
        public KsnrDetailBean call(KsnrDetailBean response) {
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