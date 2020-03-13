package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 12:35
 * @Description:
 **/
public class IssueListBean<T> extends BaseComResponse {


    /**
     * userIssueTasks : [{"id":"715f723b568e103896b87a6795b7d301","title":"2018年企业收入课题调研","des":"对于2018年企业各项收入调研分析！","status":"02","time":"2019-11-20 16:08"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T userIssueTasks;



    public static class UserIssueTasksBean {
        /**
         * id : 715f723b568e103896b87a6795b7d301
         * title : 2018年企业收入课题调研
         * des : 对于2018年企业各项收入调研分析！
         * status : 02
         * time : 2019-11-20 16:08
         */

        private String id;
        private String title;
        private String des;
        private String status;
        private String time;
        private String createUnit;

        public String getCreateUnit() {
            return createUnit;
        }

        public void setCreateUnit(String createUnit) {
            this.createUnit = createUnit;
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

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
