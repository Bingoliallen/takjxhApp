package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-06 15:56
 * @Description:
 **/
public class ScoresBean<T> extends BaseComResponse {


    /**
     * score : 10
     * rank : 1
     * userScores : [{"changeScore":3,"changeType":"线上辅导培训","desc":"参与阅读，有效阅读2分钟以上","time":"2019-11-05 21:18:48","showDesc":"线上辅导培训:参与阅读，有效阅读2分钟以上"}]
     * page : 1
     * pageSize : 5
     */

    private int score;
    private String rank;
    private int page;
    private int pageSize;
    private List<UserScoresBean> userScores;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<UserScoresBean> getUserScores() {
        return userScores;
    }

    public void setUserScores(List<UserScoresBean> userScores) {
        this.userScores = userScores;
    }

    public static class UserScoresBean {
        /**
         * changeScore : 3
         * changeType : 线上辅导培训
         * desc : 参与阅读，有效阅读2分钟以上
         * time : 2019-11-05 21:18:48
         * showDesc : 线上辅导培训:参与阅读，有效阅读2分钟以上
         */

        private int changeScore;
        private String changeType;
        private String desc;
        private String time;
        private String showDesc;

        public int getChangeScore() {
            return changeScore;
        }

        public void setChangeScore(int changeScore) {
            this.changeScore = changeScore;
        }

        public String getChangeType() {
            return changeType;
        }

        public void setChangeType(String changeType) {
            this.changeType = changeType;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getShowDesc() {
            return showDesc;
        }

        public void setShowDesc(String showDesc) {
            this.showDesc = showDesc;
        }
    }
}
