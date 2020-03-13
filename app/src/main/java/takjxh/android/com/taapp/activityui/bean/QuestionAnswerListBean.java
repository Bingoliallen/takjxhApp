package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 13:57
 * @Description:
 **/
public class QuestionAnswerListBean<T> extends BaseComResponse {


    /**
     * commAnswers : [{"id":"f55352915a26103896b87a6795b7d300","content":"这个事情就是这样子的","time":"2019-11-17 09:08:34","name":"陈海彬","userCover":"http://106.53.78.24:8080/Takjxh/images/LOGO.jpg"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T commAnswers;


    public static class CommAnswersBean {
        /**
         * id : f55352915a26103896b87a6795b7d300
         * content : 这个事情就是这样子的
         * time : 2019-11-17 09:08:34
         * name : 陈海彬
         * userCover : http://106.53.78.24:8080/Takjxh/images/LOGO.jpg
         */

        private String id;
        private String content;
        private String time;
        private String name;
        private String userCover;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
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
    }
}
