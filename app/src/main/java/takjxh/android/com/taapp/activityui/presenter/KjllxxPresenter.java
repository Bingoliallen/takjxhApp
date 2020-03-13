package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.StudyTypeBean;
import takjxh.android.com.taapp.activityui.bean.StudysBean;
import takjxh.android.com.taapp.activityui.model.KjllxxModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IKjllxxPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import takjxh.android.com.taapp.net.NetSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 8:39
 * @Description:
 **/
public class KjllxxPresenter extends BasePresenter<IKjllxxPresenter.IView, KjllxxModel> implements IKjllxxPresenter {

    public KjllxxPresenter(IView view) {
        super(view);
    }

    @Override
    protected KjllxxModel createModel() {
        return new KjllxxModel();
    }


    @Override
    public void studytypelist(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription()
                .add(mModel.studytype(token)
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc())
                        .subscribe(new NetSubscriber<List<StudyTypeBean.StudyTypesBean>>(getView().getContext()) {
                            @Override
                            public void onNext(List<StudyTypeBean.StudyTypesBean> data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().studytypelistSuccess(data);
                                }
                            }
                        }));
    }

    @Override
    public void studyslist(String typeLike, String page, String pageSize) {
        String token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.studyslist(token,typeLike,page,pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<List<StudysBean.StudyListBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<StudysBean.StudyListBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().studyslistSuccess(mBean);
                        }
                    }


                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<StudyTypeBean<T>, T> {

        @Override
        public T call(StudyTypeBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.studyTypes;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<StudysBean<T>, T> {

        @Override
        public T call(StudysBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.studyList;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }





}
