package takjxh.android.com.taapp.presenter;

import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.taapp.bean.NewsDetailBean;
import takjxh.android.com.taapp.model.NewsModel;
import takjxh.android.com.taapp.net.NetSubscriber;
import takjxh.android.com.taapp.presenter.impl.INewsPresenter;

/**
 * 名称定义
 *
 * @Author: libaibing
 * @Date: 2019-01-17 12:51
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
    public void hotNews(String id) {
        getCompositeSubscription()
                .add(mModel.hotNews(id)
                        .compose(RxHelper.io_main())
                        .map(new RxHelper.Response2DataFunc())
                        .subscribe(new NetSubscriber<NewsDetailBean>(getView().getContext()) {
                            @Override
                            public void onNext(NewsDetailBean data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().hotNewsSuccess(data);
                                }
                            }
                        }));
    }

}
