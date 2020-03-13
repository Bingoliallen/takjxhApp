package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 11:37
 * @Description:
 **/
public class PolicyIndexBean<T> extends BaseComResponse {


    /**
     * policyNum : 2
     * policingNum : 2
     * policyTypes : [{"id":"9b3ae120535e103896b87a6795b7d301","name":"给资金","ico":"http://106.53.78.24:8080/Takjxhimages/policy-1.png"},{"id":"9b3ae120535e103896b87a6795b7d302","name":"降成本","ico":"http://106.53.78.24:8080/Takjxhimages/policy-2.png"}]
     */
    public String cover;
    public int policyNum;
    public int policingNum;
    public List<PolicyTypesBean> policyTypes;

    public static class PolicyTypesBean {
        /**
         * id : 9b3ae120535e103896b87a6795b7d301
         * name : 给资金
         * ico : http://106.53.78.24:8080/Takjxhimages/policy-1.png
         */

        private String id;
        private String name;
        private String ico;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIco() {
            return ico;
        }

        public void setIco(String ico) {
            this.ico = ico;
        }
    }
}
