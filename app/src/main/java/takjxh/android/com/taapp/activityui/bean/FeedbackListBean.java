package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * Created by Administrator on 2020/3/1.
 */

public class FeedbackListBean<T> extends BaseComResponse {

    public int page;
    public int pageSize;
    public T userFeedbackVos;



    public static class UserFeedbackVosBean {
        /**{{
         "id": "b55fce4953c311ea877100163e123f46",
         "content": "请及时审批",
         "answer": "好的啊",
         "answerUser": "管理员",
         "time": "2020-02-20",
         "isAnswer": "已回复"
         }
         */

        private String id;
        private String content;
        private String answer;
        private String answerUser;
        private String time;
        private String isAnswer;

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

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getAnswerUser() {
            return answerUser;
        }

        public void setAnswerUser(String answerUser) {
            this.answerUser = answerUser;
        }

        public String getIsAnswer() {
            return isAnswer;
        }

        public void setIsAnswer(String isAnswer) {
            this.isAnswer = isAnswer;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }


}
