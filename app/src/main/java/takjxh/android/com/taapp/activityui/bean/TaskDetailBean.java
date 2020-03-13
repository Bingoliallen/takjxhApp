package takjxh.android.com.taapp.activityui.bean;

import java.util.List;
import java.util.Map;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-20 12:42
 * @Description:
 **/
public class TaskDetailBean<T> extends BaseComResponse {


    /**
     * title : 2019年9月份收入报表填报
     * des : 请核对填报报表时间，提交完成后不可修改
     * question1 : [{"id":"f2681d255692103896b87a6795b7d311","title":"您的性别为","type":"01","items":[{"id":"f2681d255692103896b87a6795b7100","des":"男","isShowNext":false},{"id":"f2681d255692103896b87a6795b7101","des":"女","isShowNext":true}]}]
     * question2 : {"data2":[{"id":"f2681d255692103896b87a6795b7d400","title":"您主要关注哪些方面的内容","type":"04","items":[{"id":"f2681d255692103896b87a6795b8000","des":null,"isShowNext":false}]}]}
     * question3 : {"data3":[{"id":"f2681d255692103896b87a6795b7d402","title":"您对于男票有什么要求","type":"04","items":[{"id":"f2681d255692103896b87a6795b8033","des":null,"isShowNext":false}]}]}
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<ItemsBeanXX> getItems() {
            return items;
        }

        public void setItems(List<ItemsBeanXX> items) {
            this.items = items;
        }

        public static class ItemsBeanXX {
            /**
             * id : f2681d255692103896b87a6795b7100
             * des : 男
             * isShowNext : false
             */

            private String id;
            private String des;
            private boolean isShowNext;

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

            public boolean isIsShowNext() {
                return isShowNext;
            }

            public void setIsShowNext(boolean isShowNext) {
                this.isShowNext = isShowNext;
            }
        }
    }
}
