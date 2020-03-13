package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 8:49
 * @Description:
 **/
public class PolicyApplyHelpBean<T> extends BaseComResponse {


    /**
     * helpList : [{"id":"3e2e2f48569e103896b87a6795b7d300","title":"关于民营企业税后利润转增资、再投资奖励申请求助","desc":"申请企业名称：厦门睿天科技有限公司<br/>求助机构名称：以江管理咨询<br/>申报类型名称：民营企业税后利润转增资、再投资奖励","status":"待协助","evaluateStar":null,"time":"2019年11月12日 21:12"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T helpList;


    public static class HelpListBean {
        /**
         * id : 3e2e2f48569e103896b87a6795b7d300
         * title : 关于民营企业税后利润转增资、再投资奖励申请求助
         * desc : 申请企业名称：厦门睿天科技有限公司<br/>求助机构名称：以江管理咨询<br/>申报类型名称：民营企业税后利润转增资、再投资奖励
         * status : 待协助
         * evaluateStar : null
         * time : 2019年11月12日 21:12
         */

        private String id;
        private String title;
        private String desc;
        private String status;
        private String evaluateStar;
        private String time;

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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getEvaluateStar() {
            return evaluateStar;
        }

        public void setEvaluateStar(String evaluateStar) {
            this.evaluateStar = evaluateStar;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
