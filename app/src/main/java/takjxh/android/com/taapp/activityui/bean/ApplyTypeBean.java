package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 16:08
 * @Description:
 **/
public class ApplyTypeBean<T> extends BaseComResponse {


    public T applyTypes;


    public static class ApplyTypesBean {
        /**
         * id : ad87d04f535c103896b87a6795b7d302
         * name : 民营企业税后利润转增资、再投资奖励
         * des : 民营企业税后利润转增资、再投资奖励
         */

        private String id;
        private String name;
        private String des;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }
}
