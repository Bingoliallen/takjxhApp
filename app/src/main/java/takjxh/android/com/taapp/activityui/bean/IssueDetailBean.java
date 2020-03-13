package takjxh.android.com.taapp.activityui.bean;

import java.util.List;
import java.util.Map;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 12:38
 * @Description:
 **/
public class IssueDetailBean<T> extends BaseComResponse {


    /**
     * title : 2018年企业收入课题调研
     * des : 对于2018年企业各项收入调研分析！
     * question1 : [{"id":"f2681d255692103896b87a6795b7d311","title":"您的性别为","type":"01","items":[{"id":"f2681d255692103896b87a6795b7100","des":"男","isShowNext":false},{"id":"f2681d255692103896b87a6795b7101","des":"女","isShowNext":true}]}]
     */

    public String title;
    public String des;

    public Map<String, List<Question1Bean>> question2;
    public Map<String, List<Question1Bean>> question3;
    public List<Question1Bean> question1;



    public static class Question1Bean {
        /**
         * id : f2681d255692103896b87a6795b7d311
         * title : 您的性别为
         * type : 01
         * items : [{"id":"f2681d255692103896b87a6795b7100","des":"男","isShowNext":false},{"id":"f2681d255692103896b87a6795b7101","des":"女","isShowNext":true}]
         */
        public boolean isOpen=true;
        public String inputContent="";
        public int cj=1;


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

            private boolean isShowNext;



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

            public boolean isIsShowNext() {
                return isShowNext;
            }

            public void setIsShowNext(boolean isShowNext) {
                this.isShowNext = isShowNext;
            }
        }
    }
}
