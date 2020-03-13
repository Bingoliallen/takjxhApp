package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.taapp.activityui.bean.BannnersBean;
import takjxh.android.com.taapp.activityui.bean.NewsBean;
import takjxh.android.com.taapp.activityui.model.NewsModel;
import takjxh.android.com.taapp.activityui.presenter.impl.INewsPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import takjxh.android.com.taapp.net.NetSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 14:37
 * @Description:
 **/
public class NewsPresenter extends BasePresenter<INewsPresenter.IView, NewsModel> implements INewsPresenter {

    public NewsPresenter(IView view) {
        super(view);
    }


    @Override
    protected NewsModel createModel() {
        return new NewsModel();
    }

    @Override
    public void bannnerslist() {

        getCompositeSubscription()
                .add(mModel.bannners()
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc())
                        .subscribe(new NetSubscriber<List<BannnersBean.BannersBean>>(getView().getContext()) {
                            @Override
                            public void onNext(List<BannnersBean.BannersBean> data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().bannnerslistSuccess(data);
                                }
                            }
                        }));
    }

    @Override
    public void newslist(String typeLike, String page, String pageSize) {
        getCompositeSubscription().add(mModel.newslist(typeLike,page,pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<List<NewsBean.NewsListBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<NewsBean.NewsListBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().newslistSuccess(mBean);
                        }
                    }


                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<BannnersBean<T>, T> {

        @Override
        public T call(BannnersBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.banners;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<NewsBean<T>, T> {

        @Override
        public T call(NewsBean<T> response) {
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
