package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-14 15:12
 * @Description:
 **/
public class NewsBean<T> extends BaseComResponse {


    /**
     * newsList : [{"id":"369fd2bef64c11e9954d6caf29bd3cf5","title":"人民日报钟声：中美良性互动多多益善","cover":"/word/media/image1.png","video":"0104","type":"0104","content":"<p style=\"\">教育督导新系统测试地址<\/p>","createUser":"张三","time":"2019-10-25 16:17:39"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T newsList;


    public static class NewsListBean {
        /**
         * id : 369fd2bef64c11e9954d6caf29bd3cf5
         * title : 人民日报钟声：中美良性互动多多益善
         * cover : /word/media/image1.png
         * video : 0104
         * type : 0104
         * content : <p style="">教育督导新系统测试地址</p>
         * createUser : 张三
         * time : 2019-10-25 16:17:39
         */

        private String id;
        private String title;
        private String cover;
        private String video;
        private String type;
        private String content;
        private String createUser;
        private String time;

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
    }
}
