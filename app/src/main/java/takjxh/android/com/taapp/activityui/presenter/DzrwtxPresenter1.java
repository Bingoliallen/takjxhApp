package takjxh.android.com.taapp.activityui.presenter;

import java.util.Map;

import rx.functions.Func1;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.net.response.CommonResponse;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.TaskDetailBean1;
import takjxh.android.com.taapp.activityui.model.DzrwtxModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IDzrwtxPresenter1;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * Created by Administrator on 2019/12/29.
 */

public class DzrwtxPresenter1 extends BasePresenter<IDzrwtxPresenter1.IView, DzrwtxModel> implements IDzrwtxPresenter1 {

    public DzrwtxPresenter1(IView view) {
        super(view);
    }

    @Override
    protected DzrwtxModel createModel() {
        return new DzrwtxModel();
    }


    @Override
    public void taskdetail(String token, String id) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.taskdetail1(token, id)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<TaskDetailBean1>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(TaskDetailBean1 mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().taskdetailSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().taskdetailFailed();
                        }
                    }
                }));
    }

    @Override
    public void taskansweradd(String token, Map<String, Object> searchRequest) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.taskansweradd(token, searchRequest)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<CommonResponse>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(CommonResponse mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().taskansweraddSuccess(mBean.resDes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().taskansweraddFailed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<TaskDetailBean1<T>, T> {

        @Override
        public TaskDetailBean1 call(TaskDetailBean1 response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }
    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<CommonResponse<T>, T> {

        @Override
        public CommonResponse call(CommonResponse response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }

}