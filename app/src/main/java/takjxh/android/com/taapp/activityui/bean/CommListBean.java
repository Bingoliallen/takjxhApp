package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 9:38
 * @Description:
 **/
public class CommListBean<T> extends BaseComResponse {


    /**
     * commTopics : [{"id":"ae803c544ab4103882a7b81541065d00","title":"厦门有望成首个\u201c国家生态园林城市2\u201d","cover":"http://106.53.78.24:8080/Takjxh/images/banner.jpg","type":"厦门身边事","viewNum":100,"commentNum":1}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T commTopics;


    public static class CommTopicsBean {
        /**
         * id : ae803c544ab4103882a7b81541065d00
         * title : 厦门有望成首个“国家生态园林城市2”
         * cover : http://106.53.78.24:8080/Takjxh/images/banner.jpg
         * type : 厦门身边事
         * viewNum : 100
         * commentNum : 1
         */
        private String video;
        private String id;
        private String title;
        private String cover;
        private String type;
        private int viewNum;
        private int commentNum;

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getViewNum() {
            return viewNum;
        }

        public void setViewNum(int viewNum) {
            this.viewNum = viewNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }
    }
}
