package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 9:29
 * @Description:
 **/
public class SurveyListBean<T> extends BaseComResponse {


    /**
     * marketSuveys : [{"id":"715f723b568e103896b87a6795b7d301","title":"大学生饮食情况调查问卷","userCover":"http://106.53.78.24:8080/Takjxh/images/img215969455ab0.jpg","createUser":"张三","createUnit":"同安会计学会","lastestTime":"2017-12-16","joinNum":"100人参与调查"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T marketSuveys;


    public static class MarketSuveysBean {
        /**
         * id : 715f723b568e103896b87a6795b7d301
         * title : 大学生饮食情况调查问卷
         * userCover : http://106.53.78.24:8080/Takjxh/images/img215969455ab0.jpg
         * createUser : 张三
         * createUnit : 同安会计学会
         * lastestTime : 2017-12-16
         * joinNum : 100人参与调查
         */

        private String id;
        private String title;
        private String userCover;
        private String createUser;
        private String createUnit;
        private String lastestTime;
        private String joinNum;

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

        public String getUserCover() {
            return userCover;
        }

        public void setUserCover(String userCover) {
            this.userCover = userCover;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getCreateUnit() {
            return createUnit;
        }

        public void setCreateUnit(String createUnit) {
            this.createUnit = createUnit;
        }

        public String getLastestTime() {
            return lastestTime;
        }

        public void setLastestTime(String lastestTime) {
            this.lastestTime = lastestTime;
        }

        public String getJoinNum() {
            return joinNum;
        }

        public void setJoinNum(String joinNum) {
            this.joinNum = joinNum;
        }
    }
}
