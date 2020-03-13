package takjxh.android.com.taapp.interfaces;

/**
 * 拖拽
 *
 * @Author: libaibing
 * @Date: 2019-01-21 12:54
 * @Description:
 **/

public interface IOnDragVHListener {

    /**
     * Item被选中时触发
     */
    void onItemSelected();


    /**
     * Item在拖拽结束/滑动结束后触发
     */
    void onItemFinish();
}
