package takjxh.android.com.taapp.presenter;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.net.RxHelper;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.bean.CenterBean;
import takjxh.android.com.taapp.bean.UserInfoBean;
import takjxh.android.com.taapp.model.MineModel;
import takjxh.android.com.taapp.net.NetDialogSubscriber;
import takjxh.android.com.taapp.net.NetSubscriber;
import takjxh.android.com.taapp.presenter.impl.IMinePresenter;

/**
 * 名称定义
 *
 * @Author: libaibing
 * @Date: 2019-01-22 12:51
 * @Description:
 **/

public class MinePresenter extends BasePresenter<IMinePresenter.IView, MineModel> implements IMinePresenter {

    public MinePresenter(IView view) {
        super(view);
    }


    @Override
    protected MineModel createModel() {
        return new MineModel();
    }

    @Override
    public void getUserInfo(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription()
                .add(mModel.getUserInfo(token)
                        .compose(RxHelper.io_main())
                        .map(new RxHelper.Response2DataFunc())
                        .subscribe(new NetSubscriber<UserInfoBean>(getView().getContext()) {
                            @Override
                            public void onNext(UserInfoBean data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().getUserInfoSuccess(data);
                                }
                            }
                        }));
    }

    @Override
    public void getCenter(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription()
                .add(mModel.getCenter(token)
                        .compose(RxHelper.io_main())
                        .map(new RxHelper.Response2DataFunc())
                        .subscribe(new NetSubscriber<CenterBean>(getView().getContext()) {
                            @Override
                            public void onNext(CenterBean data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().getCenterSuccess(data);
                                }
                            }
                        }));
    }

    @Override
    public void loginOut(String token) {
        token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        getCompositeSubscription()
                .add(mModel.loginOut(token)
                        .compose(RxHelper.io_main())
                        .map(new RxHelper.Response2DataFunc())
                        .subscribe(new NetDialogSubscriber<Object>(getView().getContext()) {
                            @Override
                            public int configuration() {
                                return DEFAULT;
                            }

                            @Override
                            public void onNext(Object data) {
                                super.onNext(data);
                                if (isAttach()) {
                                    getView().loginOutSuccess();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                if (isAttach()) {
                                    getView().loginOutError();
                                }
                            }

                        }));


    }


}
