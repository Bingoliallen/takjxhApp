package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.MessagesBean;
import takjxh.android.com.taapp.activityui.model.XxzzModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IXxzzPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 13:17
 * @Description:
 **/
public class XxzzPresenter extends BasePresenter<IXxzzPresenter.IView, XxzzModel> implements IXxzzPresenter {

    public XxzzPresenter(IView view) {
        super(view);
    }

    @Override
    protected XxzzModel createModel() {
        return new XxzzModel();
    }


    @Override
    public void messagelist(String token, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.messagelist(token, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<MessagesBean.SysMessagesBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<MessagesBean.SysMessagesBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().messagelistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().messagelistFailed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<MessagesBean<T>, T> {

        @Override
        public T call(MessagesBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.sysMessages;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}


