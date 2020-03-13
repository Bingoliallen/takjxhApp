package takjxh.android.com.taapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Date: 2018/7/30
 * Author: libaibing
 * Email：libb@android.com.cn
 * Des：
 */

public class CommBean implements Serializable{

    private static final long serialVersionUID = 8571453522761601199L;
    private List<CommBean> data;
    /**
     * BH : 110000
     * MC : 北京市
     */

    private String bh;
    private String mc;
    private String ph;

    public List<CommBean> getData() {
        return data;
    }

    public void setData(List<CommBean> data) {
        this.data = data;
    }

    public String getBH() {
        return bh;
    }

    public void setBH(String BH) {
        this.bh = BH;
    }

    public String getMC() {
        return mc;
    }

    public void setMC(String MC) {
        this.mc = MC;
    }

    public String getPH() {
        return ph;
    }

    public void setPH(String PH) {
        this.ph = PH;
    }
}

