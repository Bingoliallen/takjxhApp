package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 10:34
 * @Description:
 **/
public class QaQauserListBean<T> extends BaseComResponse {


    /**
     * qaUsers : [{"name":"陈海彬","cover":"http://106.53.78.24:8080/Takjxh/images/LOGO.jpg","des":"被采纳100次"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T qaUsers;


    public static class QaUsersBean {
        /**
         * name : 陈海彬
         * cover : http://106.53.78.24:8080/Takjxh/images/LOGO.jpg
         * des : 被采纳100次
         */

        private String name;
        private String cover;
        private String des;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }
}
