package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-23 16:05
 * @Description:
 **/
public class RegisterBean<T> extends BaseComResponse {


    /**
     * token : eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJlMTFjODJkNmY3MDcxMWU5YjM5ZmIyZTY2OTVjOTFjMyJ9.v-tzK7cDgQD0n-aB-r0XN_7mO0Fy0plFluawi3TC7MM
     * userInfo : {"id":"e11c82d6f70711e9b39fb2e6695c91c3","username":"陈海彬","name":"陈海彬","mobilePhone":"15080342451","company":"厦门睿天科技有限公司","cover":"admin/user/76b9da90605949329c77e29d88fd41c1.png","type":"系统管理员","score":"系统管理员","isOpenMsg":true,"isOpenVoice":true}
     * userExts : [{"unitName":"陈海彬","unitLinkman":"都发给谁发给","unitCode":"工会经费","linkmanPhone":"共和国发货","companyName":"电饭锅跟","orgCode":"伤感的歌","trade":"农林牧渔","regTime":"大师法国","regAddr":"得分","lagalPerson":"大幅度发分","orgAuthCode":"爱妃阿大 四的发生"}]
     */

    private String token;
    private UserInfoBean userInfo;
    private List<UserExtsBean> userExts;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public List<UserExtsBean> getUserExts() {
        return userExts;
    }

    public void setUserExts(List<UserExtsBean> userExts) {
        this.userExts = userExts;
    }

    public static class UserInfoBean {
        /**
         * id : e11c82d6f70711e9b39fb2e6695c91c3
         * username : 陈海彬
         * name : 陈海彬
         * mobilePhone : 15080342451
         * company : 厦门睿天科技有限公司
         * cover : admin/user/76b9da90605949329c77e29d88fd41c1.png
         * type : 系统管理员
         * score : 系统管理员
         * isOpenMsg : true
         * isOpenVoice : true
         */

        private String password;


        private String id;
        private String username;
        private String name;
        private String mobilePhone;
        private String company;
        private String cover;
        private String type;
        private String score;
        private boolean isOpenMsg;
        private boolean isOpenVoice;
        private boolean isTeacher; //是否是答疑专家(继续教育答疑如果当前用户是的话才能回答)

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isTeacher() {
            return isTeacher;
        }

        public void setTeacher(boolean teacher) {
            isTeacher = teacher;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public boolean isIsOpenMsg() {
            return isOpenMsg;
        }

        public void setIsOpenMsg(boolean isOpenMsg) {
            this.isOpenMsg = isOpenMsg;
        }

        public boolean isIsOpenVoice() {
            return isOpenVoice;
        }

        public void setIsOpenVoice(boolean isOpenVoice) {
            this.isOpenVoice = isOpenVoice;
        }
    }

    public static class UserExtsBean {
        /**
         * unitName : 陈海彬
         * unitLinkman : 都发给谁发给
         * unitCode : 工会经费
         * linkmanPhone : 共和国发货
         * companyName : 电饭锅跟
         * orgCode : 伤感的歌
         * trade : 农林牧渔
         * regTime : 大师法国
         * regAddr : 得分
         * lagalPerson : 大幅度发分
         * orgAuthCode : 爱妃阿大 四的发生
         */

        private String unitName;
        private String unitLinkman;
        private String unitCode;
        private String linkmanPhone;
        private String companyName;
        private String orgCode;
        private String trade;
        private String regTime;
        private String regAddr;
        private String lagalPerson;
        private String orgAuthCode;

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

        public String getOrgAuthCode() {
            return orgAuthCode;
        }

        public void setOrgAuthCode(String orgAuthCode) {
            this.orgAuthCode = orgAuthCode;
        }
    }
}
