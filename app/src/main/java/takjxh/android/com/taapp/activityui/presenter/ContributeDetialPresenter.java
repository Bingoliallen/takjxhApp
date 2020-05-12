package takjxh.android.com.taapp.activityui.presenter;


import rx.functions.Func1;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.ContributeDetial;
import takjxh.android.com.taapp.activityui.bean.ContributeDetialBean;
import takjxh.android.com.taapp.activityui.model.ContributeDetialModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IContributeDetialPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-10 23:00
 * @Description:
 **/
public class ContributeDetialPresenter  extends BasePresenter<IContributeDetialPresenter.IView, ContributeDetialModel> implements IContributeDetialPresenter {

    public ContributeDetialPresenter(IView view) {
        super(view);
    }

    @Override
    protected ContributeDetialModel createModel() {
        return new ContributeDetialModel();
    }


    @Override
    public void contributedetail(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.contributedetail(token, id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<ContributeDetial>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(ContributeDetial mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().contributedetailSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().contributedetailFailed();
                        }
                    }

                }));
    }



    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<ContributeDetialBean<T>, T> {

        @Override
        public T call(ContributeDetialBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.news;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}
