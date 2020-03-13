package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import rx.functions.Func1;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.TaskListBean;
import takjxh.android.com.taapp.activityui.bean.TaskTypeListBean;
import takjxh.android.com.taapp.activityui.model.ToBbModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IToBbPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-20 11:17
 * @Description:
 **/
public class ToBbPresenter extends BasePresenter<IToBbPresenter.IView, ToBbModel> implements IToBbPresenter {

    public ToBbPresenter(IView view) {
        super(view);
    }

    @Override
    protected ToBbModel createModel() {
        return new ToBbModel();
    }


    @Override
    public void tasktypelist(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.tasktypelist(token)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<TaskTypeListBean.CommTypesBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<TaskTypeListBean.CommTypesBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().tasktypelistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().tasktypelistFailed();
                        }
                    }
                }));
    }

    @Override
    public void tasklist(String token, String type, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.tasklist(token, type, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc1())
                .subscribe(new NetDialogSubscriber<List<TaskListBean.ReportTasksBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<TaskListBean.ReportTasksBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().tasklistSuccess(mBean,type);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().tasklistFailed();
                        }
                    }
                }));
    }

    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<TaskTypeListBean<T>, T> {

        @Override
        public T call(TaskTypeListBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.commTypes;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }

    /**
     * 返回元素
     */
    public static class Response2DataFunc1<T> implements Func1<TaskListBean<T>, T> {

        @Override
        public T call(TaskListBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.reportTasks;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }
}
