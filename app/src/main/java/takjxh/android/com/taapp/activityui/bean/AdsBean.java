package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 11:13
 * @Description:
 **/
public class AdsBean<T> extends BaseComResponse {

    /**
     * adList : [{"id":"369fd2bef64c11e9954d6caf29bd3cf5","title":"人民日报钟声：中美良性互动多多益善","cover":"/word/media/image1.png","content":"<p style=\"\">教育督导新系统测试地址<\/p>","createUser":"张三","time":"2019-10-25 16:17:39"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T adList;


    public static class AdListBean {
        /**
         * id : 369fd2bef64c11e9954d6caf29bd3cf5
         * title : 人民日报钟声：中美良性互动多多益善
         * cover : /word/media/image1.png
         * content : <p style="">教育督导新系统测试地址</p>
         * createUser : 张三
         * time : 2019-10-25 16:17:39
         */

        private String id;
        private String title;
        private String cover;
        private String content;
        private String createUser;
        private String time;
        /**
         * userCover : http://106.53.78.24:8080/Takjxh/images/img215969455ab0.jpg
         * viewNum : 0
         */

        private String userCover;
        private int viewNum;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getUserCover() {
            return userCover;
        }

        public void setUserCover(String userCover) {
            this.userCover = userCover;
        }

        public int getViewNum() {
            return viewNum;
        }

        public void setViewNum(int viewNum) {
            this.viewNum = viewNum;
        }
    }
}
