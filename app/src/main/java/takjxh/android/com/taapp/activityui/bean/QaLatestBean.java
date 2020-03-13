package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 10:49
 * @Description:
 **/
public class QaLatestBean<T> extends BaseComResponse {


    /**
     * detail : {"id":"afbb640a5a2f103896b87a6795b7d300","title":"关于照料中心建设资金补助的咨询5","type":"日常","time":"2019-11-17","cover":"http://106.53.78.24:8080/Takjxh/images/img42015f86f84b20.png"}
     */

    public T detail;


    public static class DetailBean {
        /**
         * id : afbb640a5a2f103896b87a6795b7d300
         * title : 关于照料中心建设资金补助的咨询5
         * type : 日常
         * time : 2019-11-17
         * cover : http://106.53.78.24:8080/Takjxh/images/img42015f86f84b20.png
         */

        private String id;
        private String title;
        private String type;
        private String time;
        private String cover;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
