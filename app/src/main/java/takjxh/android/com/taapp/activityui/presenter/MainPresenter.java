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
import takjxh.android.com.taapp.activityui.model.MainModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IMainPresenter;
import takjxh.android.com.taapp.net.NetSubscriber;

/**
 * Created by Administrator on 2020/3/10.
 */

public class MainPresenter extends BasePresenter<IMainPresenter.IView, MainModel> implements IMainPresenter {


    public MainPresenter(IView view) {
        super(view);
    }

    @Override
    protected MainModel createModel() {
        return new MainModel();
    }


    @Override
    public void registrationid(Map<String, Object> searchRequest) {
        String token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription()
                .add(mModel.registrationid(token, searchRequest)
                        .compose(RxHelper.io_main())
                        .map(new Response2DataFunc())
                        .subscribe(new NetSubscriber<CommonResponse>(getView().getContext()) {
                            @Override
                            public void onNext(CommonResponse data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().registrationidSuccess(data.resDes);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                if (isAttach()) {
                                    getView().registrationidFailed();
                                }
                            }
                        }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<CommonResponse<T>, T> {

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

