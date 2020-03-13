package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.AdsBean;
import takjxh.android.com.taapp.activityui.model.XsggModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IXsggPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 13:30
 * @Description:
 **/
public class XsggPresenter extends BasePresenter<IXsggPresenter.IView, XsggModel> implements IXsggPresenter {

    public XsggPresenter(IView view) {
        super(view);
    }

    @Override
    protected XsggModel createModel() {
        return new XsggModel();
    }


    @Override
    public void adslist(String token, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.adslist(token, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<AdsBean.AdListBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<AdsBean.AdListBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().adslistSuccess(mBean);
                        }
                    }


                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<AdsBean<T>, T> {

        @Override
        public T call(AdsBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.adList;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}
