package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 16:46
 * @Description:
 **/
public class QaDetailBean<T> extends BaseComResponse {


    /**
     * detail : {"id":"8529948e5a2f103896b87a6795b7d300","title":"关于照料中心建设资金补助的咨询","type":"日常","des":"同安区有公布《同安区镇级照料中心建设资金补助办法》的通知，有的话哪里能找到文件?","time":"2019-11-12 21:12","name":"陈海彬","commentNum":0}
     */

    public T detail;

    public static class DetailBean {
        /**
         * id : 8529948e5a2f103896b87a6795b7d300
         * title : 关于照料中心建设资金补助的咨询
         * type : 日常
         * des : 同安区有公布《同安区镇级照料中心建设资金补助办法》的通知，有的话哪里能找到文件?
         * time : 2019-11-12 21:12
         * name : 陈海彬
         * commentNum : 0
         * "userId": "8529948e5a2f103896b87a6795b7d000", //发布者ID[2020-2-24修改，根据这个字段和当前登录的用户信息做一个判断，如果是同一个人就显示“采纳”按钮，反之不显示]
         "isHaveAccept": "false", //是否有采纳(true/false)[2020-2-24修改，isHaveAccept=true则不显示采纳按钮，反之根据userId进行判断是否显示采纳按钮]
         */
        private String userId;
        private String isHaveAccept;

        private String id;
        private String title;
        private String type;
        private String des;
        private String time;
        private String name;
        private int commentNum;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getIsHaveAccept() {
            return isHaveAccept;
        }

        public void setIsHaveAccept(String isHaveAccept) {
            this.isHaveAccept = isHaveAccept;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
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

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }
    }
}
