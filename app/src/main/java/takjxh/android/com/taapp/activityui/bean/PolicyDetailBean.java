package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 11:44
 * @Description:
 **/
public class PolicyDetailBean<T> extends BaseComResponse {

    /**
     * policy : {"id":"050ca6634ac9103882a7b81541065dbd","title":"测试","cover":"http://106.53.78.24:8080/Takjxh/images/banner.jpg","video":null,"type":"01","createUser":"陈海彬","time":"2019-10-28 19:48:20","content":"31231231","trade":"01","createUnit":"01","applyStatus":"申报中","applyDay":"距离申报结束还有17天"}
     */

    public T policy;


    public static class PolicyBean {
        /**
         * id : 050ca6634ac9103882a7b81541065dbd
         * title : 测试
         * cover : http://106.53.78.24:8080/Takjxh/images/banner.jpg
         * video : null
         * type : 01
         * createUser : 陈海彬
         * time : 2019-10-28 19:48:20
         * content : 31231231
         * trade : 01
         * createUnit : 01
         * applyStatus : 申报中
         * applyDay : 距离申报结束还有17天
         */

        private String id;
        private String title;
        private String cover;
        private String video;
        private String type;
        private String createUser;
        private String time;
        private String content;
        private String trade;
        private String createUnit;
        private String applyStatus;
        private String applyDay;

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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTrade() {
            return trade;
        }

        public void setTrade(String trade) {
            this.trade = trade;
        }

        public String getCreateUnit() {
            return createUnit;
        }

        public void setCreateUnit(String createUnit) {
            this.createUnit = createUnit;
        }

        public String getApplyStatus() {
            return applyStatus;
        }

        public void setApplyStatus(String applyStatus) {
            this.applyStatus = applyStatus;
        }

        public String getApplyDay() {
            return applyDay;
        }

        public void setApplyDay(String applyDay) {
            this.applyDay = applyDay;
        }
    }
}
