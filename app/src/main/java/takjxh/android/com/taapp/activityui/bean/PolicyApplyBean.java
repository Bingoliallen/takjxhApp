package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 11:35
 * @Description:
 **/
public class PolicyApplyBean<T> extends BaseComResponse {


    /**
     * applyInfos : [{"id":"ad87d04f535c103896b87a6795b7d333","time":"2019年11月12日 11:14","title":"关于民营企业股权转让收益税收奖励申请","status":"审核通过","desc":"申请企业名称：厦门喆洋出行汽车租赁有限公司<br/>组织机构代码：11212121212121313<br/>申请金额：¥100000"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T applyInfos;


    public static class ApplyInfosBean {
        /**
         * id : ad87d04f535c103896b87a6795b7d333
         * time : 2019年11月12日 11:14
         * title : 关于民营企业股权转让收益税收奖励申请
         * status : 审核通过
         * desc : 申请企业名称：厦门喆洋出行汽车租赁有限公司<br/>组织机构代码：11212121212121313<br/>申请金额：¥100000
         */

        private String id;
        private String time;
        private String title;
        private String status;
        private String desc;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
