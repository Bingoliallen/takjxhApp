package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-08 11:11
 * @Description:
 **/
public class OrderQueryBean<T> extends BaseComResponse {


    /**
     * applyStatus : 01
     * effectSecond : 859
     */

    private String applyStatus;
    private int effectSecond;

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public int getEffectSecond() {
        return effectSecond;
    }

    public void setEffectSecond(int effectSecond) {
        this.effectSecond = effectSecond;
    }
}
