package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 10:01
 * @Description:
 **/
public class QaAnswerListBean<T> extends BaseComResponse {


    /**
     * isAccept : false
     * qaAnswers : [{"id":"70da7b755afb103896b87a6795b7d300","content":"具有我市初中学校正式学籍且在学籍所在校有三年完整学习经历","isAccept":false,"time":"2019-11-18 10:29:35"}]
     * page : 1
     * pageSize : 5
     */

    public boolean isAccept;
    public int page;
    public int pageSize;
    public List<QaAnswersBean> qaAnswers;


    public static class QaAnswersBean {
        /**
         * id : 70da7b755afb103896b87a6795b7d300
         * content : 具有我市初中学校正式学籍且在学籍所在校有三年完整学习经历
         * isAccept : false
         * time : 2019-11-18 10:29:35
         */

        private String id;
        private String content;
        private boolean isAccept;
        private String time;
        private String cover;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

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

        public boolean isIsAccept() {
            return isAccept;
        }

        public void setIsAccept(boolean isAccept) {
            this.isAccept = isAccept;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
