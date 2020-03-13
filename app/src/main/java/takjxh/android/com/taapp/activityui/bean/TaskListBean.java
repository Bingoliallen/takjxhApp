package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-20 11:11
 * @Description:
 **/
public class TaskListBean<T> extends BaseComResponse {


    /**
     * reportTasks : [{"id":"d12e4f9c5360103896b87a6795b7d388","title":"2018年8月份收入报表填报","cover":"http://106.53.78.24:8080/Takjxh/images/tubiao01.png"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T reportTasks;


    public static class ReportTasksBean {
        /**
         * id : d12e4f9c5360103896b87a6795b7d388
         * title : 2018年8月份收入报表填报
         * cover : http://106.53.78.24:8080/Takjxh/images/tubiao01.png
         */

        private String id;
        private String title;
        private String cover;

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
    }
}
