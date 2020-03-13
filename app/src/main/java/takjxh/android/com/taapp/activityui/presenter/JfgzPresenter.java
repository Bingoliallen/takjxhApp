package takjxh.android.com.taapp.activityui.presenter;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.ScoreRuleBean;
import takjxh.android.com.taapp.activityui.model.JfgzModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IJfgzPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-07 9:09
 * @Description:
 **/
public class JfgzPresenter extends BasePresenter<IJfgzPresenter.IView, JfgzModel> implements IJfgzPresenter {

    public JfgzPresenter(IView view) {
        super(view);
    }

    @Override
    protected JfgzModel createModel() {
        return new JfgzModel();
    }

    @Override
    public void scorerule(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.scorerule(token)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<ScoreRuleBean>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(ScoreRuleBean mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {

                            getView().scoreruleSuccess(mBean);
                        }
                    }


                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<ScoreRuleBean<T>, T> {

        @Override
        public ScoreRuleBean call(ScoreRuleBean response) {
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
