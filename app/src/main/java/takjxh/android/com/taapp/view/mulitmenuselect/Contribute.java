package takjxh.android.com.taapp.view.mulitmenuselect;

import java.util.List;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-11 8:34
 * @Description:
 **/
public class Contribute {
    /**
     * code : 04
     * value : 会计法规
     * secondList : [{"code":"0401","value":"财会法律法规","secondList":null},{"code":"0402","value":"惠企政策宣传","secondList":null},{"code":"0403","value":"财会改革制度","secondList":null}]
     */
    private String parentId="0";
    private String code;
    private String value;
    private List<Contribute> secondList;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public List<Contribute> getSecondList() {
        return secondList;
    }

    public void setSecondList(List<Contribute> secondList) {
        this.secondList = secondList;
    }
}
