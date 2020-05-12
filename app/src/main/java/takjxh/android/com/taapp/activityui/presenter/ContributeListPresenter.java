package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import rx.functions.Func1;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.ContributeDetial;
import takjxh.android.com.taapp.activityui.bean.ContributeListBean;
import takjxh.android.com.taapp.activityui.model.ContributeListModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IContributeListPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-10 22:25
 * @Description:
 **/
public class ContributeListPresenter  extends BasePresenter<IContributeListPresenter.IView,ContributeListModel> implements IContributeListPresenter {


    public ContributeListPresenter(IView view) {
        super(view);
    }

    @Override
    protected ContributeListModel createModel() {
        return new ContributeListModel();
    }


    @Override
    public void contributeList(String token, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.contributeList(token, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<ContributeDetial>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<ContributeDetial> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().contributeListSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().contributeListFailed();
                        }
                    }
                }));
    }




    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<ContributeListBean<T>, T> {

        @Override
        public T call(ContributeListBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.newsList;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }



}

