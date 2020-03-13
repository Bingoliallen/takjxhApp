package takjxh.android.com.taapp.activityui.presenter;

import java.util.List;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.ApiException;
import takjxh.android.com.commlibrary.net.ResponseCode;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.activityui.bean.OrgansBean;
import takjxh.android.com.taapp.activityui.model.ZxjgModel;
import takjxh.android.com.taapp.activityui.presenter.impl.IZxjgPresenter;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import rx.functions.Func1;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 15:20
 * @Description:
 **/
public class ZxjgPresenter extends BasePresenter<IZxjgPresenter.IView, ZxjgModel> implements IZxjgPresenter {

    public ZxjgPresenter(IView view) {
        super(view);
    }

    @Override
    protected ZxjgModel createModel() {
        return new ZxjgModel();
    }


    @Override
    public void organslist(String token, String key, String page, String pageSize) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription().add(mModel.organslist(token, key, page, pageSize)
                .compose(RxHelper.io_main())
                .map(new Response2DataFunc())
                .subscribe(new NetDialogSubscriber<List<OrgansBean.OrganListBean>>(getView().getContext()) {

                    @Override
                    public int configuration() {
                        return DEFAULT;
                    }

                    @Override
                    public void onNext(List<OrgansBean.OrganListBean> mBean) {
                        super.onNext(mBean);
                        if (isAttach()) {
                            getView().organslistSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isAttach()) {
                            getView().organslistFailed();
                        }
                    }
                }));
    }


    /**
     * 返回元素
     */
    public static class Response2DataFunc<T> implements Func1<OrgansBean<T>, T> {

        @Override
        public T call(OrgansBean<T> response) {
            if (response != null) {
                RxHelper.beanToJson(response);
            }
            if (response == null) {
                throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
            } else if (ResponseCode.SUCCESS == response.resCode) {
                return response.organList;
            } else {
                throw new ApiException(response.resCode, response.resDes);
            }
        }
    }


}
