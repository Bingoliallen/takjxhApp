package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * Created by Administrator on 2020/3/7.
 */

public class UserExtDetailBean<T> extends BaseComResponse {

    public ExtDetailBean ext;

    /* "ext": {
                "": "1",  //扩展编号
                 "": "15259269857", //账号
                 "": "巨灵软件", //单位名称
                 "": "张三", //单位联系人
                 "": "1111111111", //单位代码
                 "": "15259269857",  //联系人手机号码
                 "": "电饭锅跟", //企业名称
                 "": "1111111", //企业组织机构代码
                 "": "01", //行业
                 "": "农林牧渔", //行业，显示用
                 "": "2020-03-04", //注册时间
                 "": "得分",  //注册地址
                 "": "大幅度发分", //法人
                 "": null, //企业规模
                 "": null, //企业规模，显示用
                 "": null,  //企业营收
                 "": null, //企业营收，显示用
                 "": null,  //组织机构代码证附件或者扫描件
                 "": "01", //用户类型
                 "": "政府会员",  //用户类型，显示用
                 "": "01", //会员状态
                 "": "待审核", //会员状态，显示用
                 "": false, //是否为默认类型
                 "": "否" //是否为默认类型，显示用
     }
 */
    public static class ExtDetailBean {
        private String id;
        private String username;
        private String unitName;
        private String unitLinkman;
        private String unitCode;
        private String linkmanPhone;
        private String companyName;
        private String orgCode;
        private String trade;


        private String orgAuthCode;

        private String showTrade;
        private String regTime;
        private String regAddr;
        private String lagalPerson;
        private String scale;
        private String showScale;
        private String income;
        private String showIncome;
        private String orgFile;
        private String type;
        private String showType;
        private String status;
        private String showStatus;

        private boolean isDefaultType;
        private String showIsDefaultType;

        public String getOrgAuthCode() {
            return orgAuthCode;
        }

        public void setOrgAuthCode(String orgAuthCode) {
            this.orgAuthCode = orgAuthCode;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getUnitLinkman() {
            return unitLinkman;
        }

        public void setUnitLinkman(String unitLinkman) {
            this.unitLinkman = unitLinkman;
        }

        public String getUnitCode() {
            return unitCode;
        }

        public void setUnitCode(String unitCode) {
            this.unitCode = unitCode;
        }

        public String getLinkmanPhone() {
            return linkmanPhone;
        }

        public void setLinkmanPhone(String linkmanPhone) {
            this.linkmanPhone = linkmanPhone;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public String getTrade() {
            return trade;
        }

        public void setTrade(String trade) {
            this.trade = trade;
        }

        public String getShowTrade() {
            return showTrade;
        }

        public void setShowTrade(String showTrade) {
            this.showTrade = showTrade;
        }

        public String getRegTime() {
            return regTime;
        }

        public void setRegTime(String regTime) {
            this.regTime = regTime;
        }

        public String getRegAddr() {
            return regAddr;
        }

        public void setRegAddr(String regAddr) {
            this.regAddr = regAddr;
        }

        public String getLagalPerson() {
            return lagalPerson;
        }

        public void setLagalPerson(String lagalPerson) {
            this.lagalPerson = lagalPerson;
        }

        public String getScale() {
            return scale;
        }

        public void setScale(String scale) {
            this.scale = scale;
        }

        public String getShowScale() {
            return showScale;
        }

        public void setShowScale(String showScale) {
            this.showScale = showScale;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getShowIncome() {
            return showIncome;
        }

        public void setShowIncome(String showIncome) {
            this.showIncome = showIncome;
        }

        public String getOrgFile() {
            return orgFile;
        }

        public void setOrgFile(String orgFile) {
            this.orgFile = orgFile;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getShowType() {
            return showType;
        }

        public void setShowType(String showType) {
            this.showType = showType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getShowStatus() {
            return showStatus;
        }

        public void setShowStatus(String showStatus) {
            this.showStatus = showStatus;
        }

        public boolean isDefaultType() {
            return isDefaultType;
        }

        public void setDefaultType(boolean defaultType) {
            isDefaultType = defaultType;
        }

        public String getShowIsDefaultType() {
            return showIsDefaultType;
        }

        public void setShowIsDefaultType(String showIsDefaultType) {
            this.showIsDefaultType = showIsDefaultType;
        }
    }

}
