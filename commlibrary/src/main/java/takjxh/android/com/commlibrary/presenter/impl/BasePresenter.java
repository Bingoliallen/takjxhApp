package takjxh.android.com.commlibrary.presenter.impl;


import java.lang.ref.WeakReference;
import takjxh.android.com.commlibrary.presenter.IBasePresenter;
import rx.subscriptions.CompositeSubscription;


public abstract class BasePresenter<V extends IBasePresenter.IView, M> {

  protected String TAG = this.getClass().getSimpleName();
  protected M mModel;
  private CompositeSubscription mCompositeSubscription;
  private WeakReference<V> mView;

  public BasePresenter(V view) {
    mCompositeSubscription = new CompositeSubscription();
    mView = new WeakReference(view);
    mModel = createModel();
  }

  protected abstract M createModel();

  public boolean isAttach() {
    return mView != null && mView.get() != null;
  }

  public V getView() {
    if (mView == null) {
      return null;
    }
    return mView.get();
  }

  private void unsubscribe() {
    if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
      mCompositeSubscription.unsubscribe();
      mCompositeSubscription.clear();
      mCompositeSubscription = null;
    }
  }

  public CompositeSubscription getCompositeSubscription() {
    if (mCompositeSubscription == null) {
      mCompositeSubscription = new CompositeSubscription();
    }
    return mCompositeSubscription;
  }

  public void onDestroy() {
    unsubscribe();
    if (mView != null) {
      mView.clear();
      mView = null;
    }
  }

  // public <T> boolean isValidResult(JsonCommon<T> jsonCommon, BasePresenter.IView viewCallback) {
  //     if (jsonCommon.getCode().equals("200")) {
  //         return true;
  //     } else {
  //         viewCallback.toast(jsonCommon.getMessage());
  //         return false;
  //     }
  // }
}