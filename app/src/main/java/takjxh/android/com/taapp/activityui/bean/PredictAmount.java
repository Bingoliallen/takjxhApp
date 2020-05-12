package takjxh.android.com.taapp.activityui.bean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-11 14:36
 * @Description:
 **/
public class PredictAmount {


    public PredictAmount(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * code :
     * value : 大同街道
     */

    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
