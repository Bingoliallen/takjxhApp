package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 14:21
 * @Description:
 **/
public class NewstypeBean<T> extends BaseComResponse {

    public T newsTypes;
   /* private List<NewsTypesBean> newsTypes;
*/


    public static class NewsTypesBean {
        /**
         * code : 01
         * value : 学会概况
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
