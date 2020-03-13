package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-18 14:50
 * @Description:
 **/
public class ZxbbBean<T> extends BaseComResponse {


    public T applyItems;


    public static class ApplyItemsBean {
        /**
         * id : 8b0b34c74ab2103882a7b81541065dbf
         * title : 2019年第三季度线上辅导培训
         * price : 40.1
         * showPrice : ¥40.1
         * des : PingWest品玩11月6日讯
         */

        private String id;
        private String title;
        private double price;
        private String showPrice;
        private String des;

        private String startTime; //报名开始时间
        private String endTime;    //报名结束时间
        private String fileName;     //报名附件名称，用于显示
        private String filePath;   //报名附件实际地址[20191229]

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

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

        public String getShowPrice() {
            return showPrice;
        }

        public void setShowPrice(String showPrice) {
            this.showPrice = showPrice;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }
}
