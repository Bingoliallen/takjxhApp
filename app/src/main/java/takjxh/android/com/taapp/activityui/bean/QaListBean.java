package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 16:40
 * @Description:
 **/
public class QaListBean<T> extends BaseComResponse {


    /**
     * qas : [{"id":"afbb640a5a2f103896b87a6795b7d300","title":"关于照料中心建设资金补助的咨询5","type":"日常","commentNum":0}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T qas;


    public static class QasBean {
        /**
         * id : afbb640a5a2f103896b87a6795b7d300
         * title : 关于照料中心建设资金补助的咨询5
         * type : 日常
         * commentNum : 0
         */

        private String id;
        private String title;
        private String type;
        private int commentNum;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }
    }
}
