package takjxh.android.com.taapp.activityui.presenter;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.XxjfBean;
import takjxh.android.com.taapp.activityui.model.XxjfModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IXxjfPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-07 11:12
 * @Description:
 **/
public class XxjfPresenter  extends BasePresenter<IXxjfPresenter.IView, XxjfModel> implements IXxjfPresenter {

    public XxjfPresenter(IView view) {
        super(view);
    }

    @Override
    protected XxjfModel createModel() {
        return new XxjfModel();
    }

    @Override
    public void myscore(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.myscore(token)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<XxjfBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(XxjfBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().myscoreSuccess(mBean);
                        }
                    }


                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<XxjfBean<T>, T> {

        @Override
        public XxjfBean call(XxjfBean response) {
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
