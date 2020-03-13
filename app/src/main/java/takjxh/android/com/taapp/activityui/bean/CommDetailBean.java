package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 9:42
 * @Description:
 **/
public class CommDetailBean<T> extends BaseComResponse {

    /**
     * detail : {"id":"ae803c544ab4103882a7b81541065d99","title":"厦门有望成首个\u201c国家生态园林城市\u201d","cover":"http://106.53.78.24:8080/Takjxh/images/banner.jpg","des":"一城春色半城花，万顷波涛拥海来。","name":"陈海彬","userCover":"http://106.53.78.24:8080/Takjxh/images/LOGO.jpg","company":"厦门睿天科技有限公司"}
     */

    public T detail;


    public static class DetailBean {
        /**
         * id : ae803c544ab4103882a7b81541065d99
         * title : 厦门有望成首个“国家生态园林城市”
         * cover : http://106.53.78.24:8080/Takjxh/images/banner.jpg
         * des : 一城春色半城花，万顷波涛拥海来。
         * name : 陈海彬
         * userCover : http://106.53.78.24:8080/Takjxh/images/LOGO.jpg
         * company : 厦门睿天科技有限公司
         * "questionNum": "3" ,//提问数
         "answerNum": "5" //回答数[2020-2-24修改]
         */
        private String questionNum;
        private String answerNum;

        public String getQuestionNum() {
            return questionNum;
        }

        public void setQuestionNum(String questionNum) {
            this.questionNum = questionNum;
        }

        public String getAnswerNum() {
            return answerNum;
        }

        public void setAnswerNum(String answerNum) {
            this.answerNum = answerNum;
        }

        private String id;
        private String title;
        private String cover;
        private String des;
        private String name;
        private String userCover;
        private String company;

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

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserCover() {
            return userCover;
        }

        public void setUserCover(String userCover) {
            this.userCover = userCover;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }
    }
}
