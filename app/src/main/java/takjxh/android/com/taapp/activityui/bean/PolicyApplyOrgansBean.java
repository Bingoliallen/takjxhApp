package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 11:44
 * @Description:
 **/
public class PolicyApplyOrgansBean<T> extends BaseComResponse {


    /**
     * organList : [{"id":"42bca86b4e99103896b87a6795b7d300","name":"以江管理咨询","orgCode":"1111111","cover":"http://106.53.78.24:8080/Takjxhimages/1212.jpg","des":"以江管理咨询，是一家由全球合伙人联合所有和运营的有限合伙企业，由来自中国、美国、英国等地的团队为客户提供咨询服务。","workTime":"上班时间：9:00-18:00","linkman":"张三","linkmanPhone":"021-5629 7756","address":"上海市长宁区虹桥路2535号永融国际中心8楼","email":"hrd@yijiangglobal.com"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T organList;


    public static class OrganListBean {
        /**
         * id : 42bca86b4e99103896b87a6795b7d300
         * name : 以江管理咨询
         * orgCode : 1111111
         * cover : http://106.53.78.24:8080/Takjxhimages/1212.jpg
         * des : 以江管理咨询，是一家由全球合伙人联合所有和运营的有限合伙企业，由来自中国、美国、英国等地的团队为客户提供咨询服务。
         * workTime : 上班时间：9:00-18:00
         * linkman : 张三
         * linkmanPhone : 021-5629 7756
         * address : 上海市长宁区虹桥路2535号永融国际中心8楼
         * email : hrd@yijiangglobal.com
         */

        private String id;
        private String name;
        private String orgCode;
        private String cover;
        private String des;
        private String workTime;
        private String linkman;
        private String linkmanPhone;
        private String address;
        private String email;

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

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getWorkTime() {
            return workTime;
        }

        public void setWorkTime(String workTime) {
            this.workTime = workTime;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getLinkmanPhone() {
            return linkmanPhone;
        }

        public void setLinkmanPhone(String linkmanPhone) {
            this.linkmanPhone = linkmanPhone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
