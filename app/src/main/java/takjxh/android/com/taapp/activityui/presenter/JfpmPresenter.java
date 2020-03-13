package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.JfpmBean;
import takjxh.android.com.taapp.activityui.model.JfpmModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IJfpmPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-06 16:23
 * @Description:
 **/
public class JfpmPresenter  extends BasePresenter<IJfpmPresenter.IView, JfpmModel> implements IJfpmPresenter {

    public JfpmPresenter(IView view) {
        super(view);
    }

    @Override
    protected JfpmModel createModel() {
        return new JfpmModel();
    }

    @Override
    public void rankslist(String token, String type, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.rankslist(token, type, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<JfpmBean.UserScoresBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<JfpmBean.UserScoresBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().rankslistSuccess(mBean);
                        }
                    }


                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<JfpmBean<T>, T> {

        @Override
        public T call(JfpmBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.userScores;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}
