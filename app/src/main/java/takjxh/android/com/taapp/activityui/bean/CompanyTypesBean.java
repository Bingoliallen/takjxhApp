package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 11:48
 * @Description:
 **/
public class CompanyTypesBean<T> extends BaseComResponse {

    public T companyTypes;


    public static class CompanyTypeBean {
        public CompanyTypeBean() {
        }

        public CompanyTypeBean(String code, String value) {
            this.code = code;
            this.value = value;
        }

        /**
         * code : 01
         * value : 微型企业
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

}
