package takjxh.android.com.taapp.activityui.presenter;

import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.taapp.activityui.bean.BannnerDetailBean;
import takjxh.android.com.taapp.activityui.model.BannersDetailModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IBannersDetailPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 16:23
 * @Description:
 **/
public class BannersDetailPresenter extends BasePresenter<IBannersDetailPresenter.IView, BannersDetailModel> implements IBannersDetailPresenter {

    public BannersDetailPresenter(IView view) {
        super(view);
    }

    @Override
    protected BannersDetailModel createModel() {
        return new BannersDetailModel();
    }


    @Override
    public void bannnerdetail(String id) {
        getCompositeSubscription().add(mModel.bannnerdetail(id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<BannnerDetailBean.DetailBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(BannnerDetailBean.DetailBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().bannnerdetailSuccess(mBean);
                        }
                    }


                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<BannnerDetailBean<T>, T> {

        @Override
        public T call(BannnerDetailBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.detail;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}
