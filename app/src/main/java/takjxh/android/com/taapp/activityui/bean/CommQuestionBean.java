package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 13:50
 * @Description:
 **/
public class CommQuestionBean<T> extends BaseComResponse {


    /**
     * commQuestions : [{"id":"642c4f885a21103896b87a6795b7d300","content":"吴先生您好，昆剧真的有必要再创新吗？","time":"2019-11-17 08:28:44","name":"陈海彬","userCover":"http://106.53.78.24:8080/Takjxh/images/LOGO.jpg","commentNum":0}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T commQuestions;



    public static class CommQuestionsBean {
        /**
         * id : 642c4f885a21103896b87a6795b7d300
         * content : 吴先生您好，昆剧真的有必要再创新吗？
         * time : 2019-11-17 08:28:44
         * name : 陈海彬
         * userCover : http://106.53.78.24:8080/Takjxh/images/LOGO.jpg
         * commentNum : 0
         */

        private String id;
        private String content;
        private String time;
        private String name;
        private String userCover;
        private int commentNum;

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

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }
    }
}
