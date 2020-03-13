package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 16:39
 * @Description:
 **/
public class KsnrDetailBean <T> extends BaseComResponse {
    public String id;
    public String title;
    public String des;

    public List<Question1Bean> question1;


    public static class Question1Bean {
        /**
         * id : f2681d255692103896b87a6795b7d311
         * title : 您的性别为
         * type : 01
         * items : [{"id":"f2681d255692103896b87a6795b7100","des":"男","isShowNext":false},{"id":"f2681d255692103896b87a6795b7101","des":"女","isShowNext":true}]
         */
        public String des;
        private String id;
        private String title;
        private String type;
        private List<ItemsBean> items;

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

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * id : f2681d255692103896b87a6795b7100
             * des : 男
             * isShowNext : false
             */

            private String id;
            private String des;

            public boolean isCheck=false;

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

