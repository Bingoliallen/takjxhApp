package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 9:06
 * @Description:
 **/
public class MessagesBean<T> extends BaseComResponse {

    /**
     * sysMessages : [{"id":"e11c82d6f70711e9b39fb2e6695c9100","sourceType":"01","showSourceType":"通知消息","sourceId":null,"des":"亲，元旦节啦，祝您节日快乐。","cover":"http://106.53.78.24:8080/Takjxh/images/msg-default.png","time":"2019-11-13 15:42:43"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T sysMessages;


    public static class SysMessagesBean {
        /**
         * id : e11c82d6f70711e9b39fb2e6695c9100
         * sourceType : 01
         * showSourceType : 通知消息
         * sourceId : null
         * des : 亲，元旦节啦，祝您节日快乐。
         * cover : http://106.53.78.24:8080/Takjxh/images/msg-default.png
         * time : 2019-11-13 15:42:43
         */

        private String id;
        private String sourceType;
        private String showSourceType;
        private String sourceId;
        private String des;
        private String cover;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSourceType() {
            return sourceType;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }

        public String getShowSourceType() {
            return showSourceType;
        }

        public void setShowSourceType(String showSourceType) {
            this.showSourceType = showSourceType;
        }

        public String getSourceId() {
            return sourceId;
        }

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
