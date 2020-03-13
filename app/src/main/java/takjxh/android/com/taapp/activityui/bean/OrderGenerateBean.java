package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-08 11:08
 * @Description:
 **/
public class OrderGenerateBean<T> extends BaseComResponse {


    /**
     * orderId : de31db59527b103896b87a6795b7d300
     * effectSecond : 900
     */
    private String orderId;
    private String effectSecond;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEffectSecond() {
        return effectSecond;
    }

    public void setEffectSecond(String effectSecond) {
        this.effectSecond = effectSecond;
    }
}
