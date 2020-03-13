package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * Created by Administrator on 2019/10/16.
 */

public class JfpmBean<T> extends BaseComResponse {

    /**
     * userScores : [{"name":"陈海彬","newScore":10,"cover":"http://106.53.78.24:8080/Takjxh/images/LOGO.jpg"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T userScores;


    public static class UserScoresBean {
        /**
         * name : 陈海彬
         * newScore : 10
         * cover : http://106.53.78.24:8080/Takjxh/images/LOGO.jpg
         */

        private String name;
        private int newScore;
        private String cover;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNewScore() {
            return newScore;
        }

        public void setNewScore(int newScore) {
            this.newScore = newScore;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
