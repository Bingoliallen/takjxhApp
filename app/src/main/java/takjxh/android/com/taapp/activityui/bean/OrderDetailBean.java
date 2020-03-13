package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-08 11:03
 * @Description:
 **/
public class OrderDetailBean<T> extends BaseComResponse {

    /**
     * order : {"id":"497b52eb51db103896b87a6795b7d300","title":"2019年第三季度线上辅导培训","price":40.1,"name":"张三","mobilePhone":"15259269857","applyStatus":"待支付","des":"PingWest品玩11月6日讯，据科技部官网消息。","time":"2019-11-07 11:25:52","showPrice":"¥40.1"}
     */

    public T order;


    public static class OrderBean {
        /**
         * id : 497b52eb51db103896b87a6795b7d300
         * title : 2019年第三季度线上辅导培训
         * price : 40.1
         * name : 张三
         * mobilePhone : 15259269857
         * applyStatus : 待支付
         * des : PingWest品玩11月6日讯，据科技部官网消息。
         * time : 2019-11-07 11:25:52
         * showPrice : ¥40.1
         */

        private String id;
        private String title;
        private double price;
        private String name;
        private String mobilePhone;
        private String applyStatus;
        private String des;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getApplyStatus() {
            return applyStatus;
        }

        public void setApplyStatus(String applyStatus) {
            this.applyStatus = applyStatus;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
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
