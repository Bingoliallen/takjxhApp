package takjxh.android.com.taapp.activityui.chat.keyboard.interfaces;

import android.view.ViewGroup;

import takjxh.android.com.taapp.activityui.chat.keyboard.adpater.EmoticonsAdapter;

public interface EmoticonDisplayListener<T> {

    void onBindView(int position, ViewGroup parent, EmoticonsAdapter.ViewHolder viewHolder, T t, boolean isDelBtn);
}
