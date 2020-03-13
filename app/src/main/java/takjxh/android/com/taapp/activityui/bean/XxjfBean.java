package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * Created by Administrator on 2019/10/16.
 */

public class XxjfBean<T> extends BaseComResponse {


    /**
     * score : 10
     * time : 2019-11-05
     * scoreTasks : [{"title":"线上辅导培训","type":"01","isComplete":"01","desc":"学习积分+3","time":"2019-11-06"},{"title":"继续教育考试","type":"02","isComplete":"00","desc":"","time":"2019-11-06"}]
     */

    private int score;
    private String time;
    private List<ScoreTasksBean> scoreTasks;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<ScoreTasksBean> getScoreTasks() {
        return scoreTasks;
    }

    public void setScoreTasks(List<ScoreTasksBean> scoreTasks) {
        this.scoreTasks = scoreTasks;
    }

    public static class ScoreTasksBean {
        /**
         * title : 线上辅导培训
         * type : 01
         * isComplete : 01
         * desc : 学习积分+3
         * time : 2019-11-06
         */

        private String title;
        private String type;
        private String isComplete;
        private String desc;
        private String time;

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

        public String getIsComplete() {
            return isComplete;
        }

        public void setIsComplete(String isComplete) {
            this.isComplete = isComplete;
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
    }
}
