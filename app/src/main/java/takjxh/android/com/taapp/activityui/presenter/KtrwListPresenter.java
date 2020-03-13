package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import rx.functions.Func1;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.IssueListBean;
import takjxh.android.com.taapp.activityui.model.KtrwListModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IKtrwListPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 12:52
 * @Description:
 **/
public class KtrwListPresenter extends BasePresenter<IKtrwListPresenter.IView, KtrwListModel> implements IKtrwListPresenter {

    public KtrwListPresenter(IView view) {
        super(view);
    }

    @Override
    protected KtrwListModel createModel() {
        return new KtrwListModel();
    }


    @Override
    public void issuelist(String token, String type, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.issuelist(token, type, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<IssueListBean.UserIssueTasksBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<IssueListBean.UserIssueTasksBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().issuelistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().issuelistFailed();
                        }
                    }
                }));
    }

    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<IssueListBean<T>, T> {

        @Override
        public T call(IssueListBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.userIssueTasks;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}
