package takjxh.android.com.taapp.activityui.presenter;

import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.taapp.activityui.bean.NewsDetailBean;
import takjxh.android.com.taapp.activityui.model.NewsDetailModel;
import takjxh.android.com.taapp.activityui.presenter.impl.INewsDetailPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 15:24
 * @Description:
 **/
public class NewsDetailPresenter extends BasePresenter<INewsDetailPresenter.IView, NewsDetailModel> implements INewsDetailPresenter {

    public NewsDetailPresenter(IView view) {
        super(view);
    }

    @Override
    protected NewsDetailModel createModel() {
        return new NewsDetailModel();
    }


    @Override
    public void newsdetail(String id) {
        getCompositeSubscription().add(mModel.newsdetail(id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<NewsDetailBean.NewsBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(NewsDetailBean.NewsBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().newsdetailSuccess(mBean);
                        }
                    }


                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<NewsDetailBean<T>, T> {

        @Override
        public T call(NewsDetailBean<T> response) {
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
