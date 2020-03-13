package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-18 14:24
 * @Description:
 **/
public class WdZxbbBean<T> extends BaseComResponse {


    /**
     * applyOrders : [{"id":"497b52eb51db103896b87a6795b7d300","title":"2019年第三季度线上辅导培训","price":40.1,"applyStatus":"待支付","time":"2019-11-07 11:25:52","showPrice":"¥40.1"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T applyOrders;


    public static class ApplyOrdersBean {
        /**
         * id : 497b52eb51db103896b87a6795b7d300
         * title : 2019年第三季度线上辅导培训
         * price : 40.1
         * applyStatus : 待支付
         * time : 2019-11-07 11:25:52
         * showPrice : ¥40.1
         */

        private String id;
        private String title;
        private double price;
        private String applyStatus;
        private String time;
        private String showPrice;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getApplyStatus() {
            return applyStatus;
        }

        public void setApplyStatus(String applyStatus) {
            this.applyStatus = applyStatus;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getShowPrice() {
            return showPrice;
        }

        public void setShowPrice(String showPrice) {
            this.showPrice = showPrice;
        }
    }
}
