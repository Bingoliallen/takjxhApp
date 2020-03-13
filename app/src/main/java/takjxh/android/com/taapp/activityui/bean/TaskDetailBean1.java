package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * Created by Administrator on 2019/12/29.
 */

public class TaskDetailBean1<T> extends BaseComResponse {

    public String title;

    public List<Question1Bean> itemTitles;

    public static class Question1Bean {
        public boolean isOpen=true;
        private String id;
        private String title;
        private List<ItemsBeanXX> items;

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

        public List<ItemsBeanXX> getItems() {
            return items;
        }

        public void setItems(List<ItemsBeanXX> items) {
            this.items = items;
        }

        public static class ItemsBeanXX {
            private String id;
            private String des;
            public String inputContent="";

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }
        }
    }



}
