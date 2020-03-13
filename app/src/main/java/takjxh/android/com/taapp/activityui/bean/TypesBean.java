package takjxh.android.com.taapp.activityui.bean;

import java.io.Serializable;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 14:38
 * @Description:
 **/
public class TypesBean implements Serializable{

    private static final long serialVersionUID = -5053412967314724078L;

    private String code;
    private String value;

    public TypesBean(String code, String value) {
        this.code = code;
        this.value = value;
    }

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
