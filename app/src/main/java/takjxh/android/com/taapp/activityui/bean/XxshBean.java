package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-18 16:55
 * @Description:
 **/
public class XxshBean<T> extends BaseComResponse {


    /**
     * audits : [{"id":"e11c82d6f70711e9b39fb2e6695c91c4","title":"\u201c厦门长天企业\u201d资金申报审核","des":"注册企业：厦门长天企业有限公司\r\n<br/>统一社会信用代码：11212121212121313\r\n<br/>企业收入：100万","submitName":"张三","time":"2019-11-06 19:51:35","auditStatus":"01","showStatus":"待审核"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T audits;


    public static class AuditsBean {
        /**
         * id : e11c82d6f70711e9b39fb2e6695c91c4
         * title : “厦门长天企业”资金申报审核
         * des : 注册企业：厦门长天企业有限公司
         <br/>统一社会信用代码：11212121212121313
         <br/>企业收入：100万
         * submitName : 张三
         * time : 2019-11-06 19:51:35
         * auditStatus : 01
         * showStatus : 待审核
         */

        private String id;
        private String title;
        private String des;
        private String submitName;
        private String time;
        private String auditStatus;
        private String showStatus;

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

        public String getSubmitName() {
            return submitName;
        }

        public void setSubmitName(String submitName) {
            this.submitName = submitName;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(String auditStatus) {
            this.auditStatus = auditStatus;
        }

        public String getShowStatus() {
            return showStatus;
        }

        public void setShowStatus(String showStatus) {
            this.showStatus = showStatus;
        }
    }
}
