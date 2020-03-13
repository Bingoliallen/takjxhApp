package takjxh.android.com.taapp.activityui.bean;

import java.io.Serializable;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-22 9:10
 * @Description:
 **/
public class ExamAddBean<T> extends BaseComResponse implements Serializable{

    /**
     * showTotalTime : 1:40
     * score : 2
     * des : 考试大师<br/>轻轻松松，得到满分
     * rank : 1
     * examId : 53e0b59e5d8e103896b87a6795b7d300
     */

    private String showTotalTime;
    private int score;
    private String des;
    private String rank;
    private String examId;

    public String getShowTotalTime() {
        return showTotalTime;
    }

    public void setShowTotalTime(String showTotalTime) {
        this.showTotalTime = showTotalTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }
}
