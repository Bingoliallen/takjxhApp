package takjxh.android.com.taapp.activityui.bean;

import java.util.List;
import java.util.Map;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 11:39
 * @Description:
 **/
public class PolicyApplyDetailBean<T> extends BaseComResponse {


    /**
     * applyInfo : {"id":"ad87d04f535c103896b87a6795b7d333","entName":"厦门喆洋出行汽车租赁有限公司","orgCode":"11212121212121313","regTime":"2018年11月11日 00:00","lagalPerson":"张三","linkman":"张三","linkmanPhone":"15259269855","files":"http://106.53.78.24:8080/Takjxh/images/banner.jpg,http://106.53.78.24:8080/Takjxh/images/company-1.jp","applyType":"民营企业股权转让收益税收奖励","entIncomeTax":1000000,"persIncomeTax":500000,"entIncome":500000,"applyAmount":100000,"status":"审核通过","showEntIncomeTax":"¥1000000","showPersIncomeTax":"¥500000","showEntIncome":"¥500000","showApplyAmount":"¥100000","time":"2019年11月12日 11:14","title":"关于民营企业股权转让收益税收奖励申请"}
     */

    public T applyInfo;


    public static class ApplyInfoBean {
        /**
         * id : ad87d04f535c103896b87a6795b7d333
         * entName : 厦门喆洋出行汽车租赁有限公司
         * orgCode : 11212121212121313
         * regTime : 2018年11月11日 00:00
         * lagalPerson : 张三
         * linkman : 张三
         * linkmanPhone : 15259269855
         * files : http://106.53.78.24:8080/Takjxh/images/banner.jpg,http://106.53.78.24:8080/Takjxh/images/company-1.jp
         * applyType : 民营企业股权转让收益税收奖励
         * entIncomeTax : 1000000
         * persIncomeTax : 500000
         * entIncome : 500000
         * applyAmount : 100000
         * status : 审核通过
         * showEntIncomeTax : ¥1000000
         * showPersIncomeTax : ¥500000
         * showEntIncome : ¥500000
         * showApplyAmount : ¥100000
         * time : 2019年11月12日 11:14
         * title : 关于民营企业股权转让收益税收奖励申请
         */

        private String id;
        private String entName;
        private String orgCode;
        private String regTime;
        private String lagalPerson;
        private String linkman;
        private String linkmanPhone;
        public String files;
        public String fileNames;
        private String applyType;
        private String entIncomeTax;
        private String persIncomeTax;
        private String entIncome;
        private String applyAmount;
        private String status;
        private String showEntIncomeTax;
        private String showPersIncomeTax;
        private String showEntIncome;
        private String showApplyAmount;
        private String time;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEntName() {
            return entName;
        }

        public void setEntName(String entName) {
            this.entName = entName;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public String getRegTime() {
            return regTime;
        }

        public void setRegTime(String regTime) {
            this.regTime = regTime;
        }

        public String getLagalPerson() {
            return lagalPerson;
        }

        public void setLagalPerson(String lagalPerson) {
            this.lagalPerson = lagalPerson;
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



        public String getApplyType() {
            return applyType;
        }

        public void setApplyType(String applyType) {
            this.applyType = applyType;
        }

        public String getEntIncomeTax() {
            return entIncomeTax;
        }

        public void setEntIncomeTax(String entIncomeTax) {
            this.entIncomeTax = entIncomeTax;
        }

        public String getPersIncomeTax() {
            return persIncomeTax;
        }

        public void setPersIncomeTax(String persIncomeTax) {
            this.persIncomeTax = persIncomeTax;
        }

        public String getEntIncome() {
            return entIncome;
        }

        public void setEntIncome(String entIncome) {
            this.entIncome = entIncome;
        }

        public String getApplyAmount() {
            return applyAmount;
        }

        public void setApplyAmount(String applyAmount) {
            this.applyAmount = applyAmount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getShowEntIncomeTax() {
            return showEntIncomeTax;
        }

        public void setShowEntIncomeTax(String showEntIncomeTax) {
            this.showEntIncomeTax = showEntIncomeTax;
        }

        public String getShowPersIncomeTax() {
            return showPersIncomeTax;
        }

        public void setShowPersIncomeTax(String showPersIncomeTax) {
            this.showPersIncomeTax = showPersIncomeTax;
        }

        public String getShowEntIncome() {
            return showEntIncome;
        }

        public void setShowEntIncome(String showEntIncome) {
            this.showEntIncome = showEntIncome;
        }

        public String getShowApplyAmount() {
            return showApplyAmount;
        }

        public void setShowApplyAmount(String showApplyAmount) {
            this.showApplyAmount = showApplyAmount;
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
    }
}
