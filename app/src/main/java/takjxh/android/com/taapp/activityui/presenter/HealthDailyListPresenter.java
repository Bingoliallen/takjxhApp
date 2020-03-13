package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;
import java.util.Map;

import rx.functions.Func1;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.HealthListBean;
import takjxh.android.com.taapp.activityui.model.HealthDailyListModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IHealthDailyListPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * Created by Administrator on 2020/3/10.
 */

public class HealthDailyListPresenter  extends BasePresenter<IHealthDailyListPresenter.IView, HealthDailyListModel> implements IHealthDailyListPresenter {


    public HealthDailyListPresenter(IView view) {
        super(view);
    }

    @Override
    protected HealthDailyListModel createModel() {
        return new HealthDailyListModel();
    }



    @Override
    public void healthlist(String token, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.healthlist(token, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<HealthListBean.HealthListVosBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<HealthListBean.HealthListVosBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().healthlistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().healthlistFailed();
                        }
                    }
                }));
    }




    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<HealthListBean<T>, T> {

        @Override
        public T call(HealthListBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.healthListVos;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}

