package takjxh.android.com.taapp.activityui.presenter;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.ScoresBean;
import takjxh.android.com.taapp.activityui.model.XxbbModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IXxbbPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-06 15:59
 * @Description:
 **/
public class XxbbPresenter extends BasePresenter<IXxbbPresenter.IView, XxbbModel> implements IXxbbPresenter {

    public XxbbPresenter(IView view) {
        super(view);
    }

    @Override
    protected XxbbModel createModel() {
        return new XxbbModel();
    }


    @Override
    public void scoreslist(String token, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.scoreslist(token, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<ScoresBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(ScoresBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().scoreslistSuccess(mBean);
                        }
                    }


                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<ScoresBean<T>, T> {

        @Override
        public ScoresBean call(ScoresBean response) {
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

