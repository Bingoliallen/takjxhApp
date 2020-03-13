package takjxh.android.com.taapp.activityui.chat.keyboard.interfaces;

import android.view.View;
import android.view.ViewGroup;

import takjxh.android.com.taapp.activityui.chat.keyboard.data.PageEntity;

public interface PageViewInstantiateListener<T extends PageEntity> {

    View instantiateItem(ViewGroup container, int position, T pageEntity);
}
