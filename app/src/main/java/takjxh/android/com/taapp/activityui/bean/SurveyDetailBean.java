package takjxh.android.com.taapp.activityui.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 11:11
 * @Description:
 **/
public class SurveyDetailBean<T> extends BaseComResponse {

    /**
     * title : 大学生饮食情况调查问卷
     * des : 同学你好，我们是秦职院调查人员，我们发现现在很多学生对于饮食的均衡和搭配不了解，饮食习惯不规律，为此我们组织调查，并承诺这次调查所得信息只用于此次调查，感谢您的支持与配合
     * question1 : [{"id":"f2681d255692103896b87a6795b7d311","title":"您的性别为","type":"01","items":[{"id":"f2681d255692103896b87a6795b7100","des":"男","isShowNext":false},{"id":"f2681d255692103896b87a6795b7101","des":"女","isShowNext":true}]}]
     * question2 : {"f2681d255692103896b87a6795b7d355":[{"id":"f2681d255692103896b87a6795b7d400","title":"您主要关注哪些方面的内容","type":"04","items":[{"id":"f2681d255692103896b87a6795b8000","des":null,"isShowNext":false}]}]}
     * question3 : {"f2681d255692103896b87a6795b7d401":[{"id":"f2681d255692103896b87a6795b7d402","title":"您对于男票有什么要求","type":"04","items":[{"id":"f2681d255692103896b87a6795b8033","des":null,"isShowNext":false}]}]}
     */

    public String title;
    public String des;
    public List<Question1Bean> question1;
    public Map<String, List<Question1Bean>> question2;
    public Map<String, List<Question1Bean>> question3;

    public static class Question1Bean {
        /**
         * id : f2681d255692103896b87a6795b7d311
         * title : 您的性别为
         * type : 01
         * items : [{"id":"f2681d255692103896b87a6795b7100","des":"男","isShowNext":false},{"id":"f2681d255692103896b87a6795b7101","des":"女","isShowNext":true}]
         */
        public int cj=1;
        private String id;
        private String title;
        private String type;
        private List<ItemsBeanXX> items;

        public List<Question1Bean> question=new ArrayList<>();
        public boolean isOpen=true;

        public String inputContent="";

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
