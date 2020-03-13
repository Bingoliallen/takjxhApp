package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-08 10:27
 * @Description:
 **/
public class SysParamBean<T> extends BaseComResponse {
    /**
     * params : {"messagesourcetype":[{"paramDesc":"消息来源类型","code":"01","value":"通知消息"},{"paramDesc":"消息来源类型","code":"02","value":"用户审核"},{"paramDesc":"消息来源类型","code":"03","value":"继续教育学习"},{"paramDesc":"消息来源类型","code":"04","value":"继续教育考试"},{"paramDesc":"消息来源类型","code":"05","value":"继续教育培训"},{"paramDesc":"消息来源类型","code":"06","value":"继续教育答疑答疑"},{"paramDesc":"消息来源类型","code":"07","value":"继续教育交流"},{"paramDesc":"消息来源类型","code":"08","value":"继续教育报名"},{"paramDesc":"消息来源类型","code":"09","value":"政策申报"},{"paramDesc":"消息来源类型","code":"10","value":"市场问卷调查"},{"paramDesc":"消息来源类型","code":"11","value":"报表任务"},{"paramDesc":"消息来源类型","code":"12","value":"课题任务"}],"logchangetype":[{"paramDesc":"积分变更类型","code":"01","value":"线上辅导培训"},{"paramDesc":"积分变更类型","code":"02","value":"继续教育考试"}],"studytype":[{"paramDesc":"继续教育学习类型","code":"01","value":"要闻"},{"paramDesc":"继续教育学习类型","code":"02","value":"新思想"},{"paramDesc":"继续教育学习类型","code":"03","value":"综合"}],"audittype":[{"paramDesc":"信息审核类型","code":"01","value":"用户注册"},{"paramDesc":"信息审核类型","code":"02","value":"政策申报"}],"faqtype":[{"paramDesc":"常见问题类型","code":"01","value":"日常"}],"applyorderstatus":[{"paramDesc":"报名状态","code":"01","value":"待支付"},{"paramDesc":"报名状态","code":"02","value":"报名成功"},{"paramDesc":"报名状态","code":"03","value":"支付失败"}],"usertype":[{"paramDesc":"用户类型","code":"01","value":"政府会员"},{"paramDesc":"用户类型","code":"02","value":"企业会员"},{"paramDesc":"用户类型","code":"03","value":"第三方服务机构"},{"paramDesc":"用户类型","code":"04","value":"系统管理员"},{"paramDesc":"用户类型","code":"05","value":"普通会员"}],"surveystatus":[{"paramDesc":"问卷状态","code":"02","value":"已发布"},{"paramDesc":"问卷状态","code":"03","value":"已结束"}],"traintype":[{"paramDesc":"继续教育培训类型","code":"01","value":"要闻"},{"paramDesc":"继续教育培训类型","code":"02","value":"新思想"},{"paramDesc":"继续教育培训类型","code":"03","value":"综合"}],"newstype":[{"paramDesc":"首页新闻类型","code":"0605","value":"个人入会申请流程"},{"paramDesc":"首页新闻类型","code":"0104","value":"学会文化"},{"paramDesc":"首页新闻类型","code":"0201","value":"学会活动"},{"paramDesc":"首页新闻类型","code":"0202","value":"文件通知"},{"paramDesc":"首页新闻类型","code":"0203","value":"工作会议"},{"paramDesc":"首页新闻类型","code":"0301","value":"培训公告"},{"paramDesc":"首页新闻类型","code":"0302","value":"讲座信息"},{"paramDesc":"首页新闻类型","code":"0401","value":"财会法律法规"},{"paramDesc":"首页新闻类型","code":"0402","value":"惠企政策宣传"},{"paramDesc":"首页新闻类型","code":"01","value":"学会概况"},{"paramDesc":"首页新闻类型","code":"0403","value":"财会改革制度"},{"paramDesc":"首页新闻类型","code":"02","value":"学会公告"},{"paramDesc":"首页新闻类型","code":"03","value":"培训信息"},{"paramDesc":"首页新闻类型","code":"04","value":"会计法规"},{"paramDesc":"首页新闻类型","code":"05","value":"课题研究"},{"paramDesc":"首页新闻类型","code":"06","value":"会员服务"},{"paramDesc":"首页新闻类型","code":"0601","value":"会员公告"},{"paramDesc":"首页新闻类型","code":"07","value":"资料库"},{"paramDesc":"首页新闻类型","code":"0602","value":"会员活动"},{"paramDesc":"首页新闻类型","code":"0101","value":"学会简介"},{"paramDesc":"首页新闻类型","code":"0102","value":"学会章程"},{"paramDesc":"首页新闻类型","code":"0603","value":"会员风采"},{"paramDesc":"首页新闻类型","code":"0103","value":"学会组织机构"},{"paramDesc":"首页新闻类型","code":"0604","value":"企业入会申请流程"}],"applystatus":[{"paramDesc":"政策申报类型","code":"01","value":"待审核"},{"paramDesc":"政策申报类型","code":"02","value":"审核通过"},{"paramDesc":"政策申报类型","code":"03","value":"审核不通过"}],"commtopictype":[{"paramDesc":"继续教育交流类型","code":"01","value":"厦门身边事"},{"paramDesc":"继续教育交流类型","code":"02","value":"生态园林"},{"paramDesc":"继续教育交流类型","code":"03","value":"其他"}],"usertrade":[{"paramDesc":"行业","code":"01","value":"农林牧渔"},{"paramDesc":"行业","code":"02","value":"医药卫生"},{"paramDesc":"行业","code":"03","value":"建筑建材"},{"paramDesc":"行业","code":"04","value":"冶金矿产"},{"paramDesc":"行业","code":"05","value":"石油化工"},{"paramDesc":"行业","code":"05","value":"水利水电"}],"userstatus":[{"paramDesc":"用户状态","code":"01","value":"待审核"},{"paramDesc":"用户状态","code":"02","value":"已注册"},{"paramDesc":"用户状态","code":"03","value":"不通过"}],"policycreteunit":[{"paramDesc":"发布单位/部门","code":"01","value":"同安会计学会"},{"paramDesc":"发布单位/部门","code":"02","value":"人民出版社"}],"auditstatus":[{"paramDesc":"审核状态","code":"01","value":"待审核"},{"paramDesc":"审核状态","code":"02","value":"审核通过"},{"paramDesc":"审核状态","code":"03","value":"审核不通过"}]}
     */

    private ParamsBean params;

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public static class ParamsBean {
        private List<MessagesourcetypeBean> messagesourcetype;//消息来源类型
        private List<LogchangetypeBean> logchangetype;//积分变更类型
        private List<StudytypeBean> studytype;//继续教育学习类型
        private List<AudittypeBean> audittype;//信息审核类型
        private List<FaqtypeBean> faqtype;//常见问题类型
        private List<ApplyorderstatusBean> applyorderstatus;//报名状态
        private List<UsertypeBean> usertype;//用户类型
        private List<SurveystatusBean> surveystatus;//问卷状态
        private List<TraintypeBean> traintype;//继续教育培训类型
        private List<NewstypeBean> newstype;//首页新闻类型
        private List<ApplystatusBean> applystatus;//政策申报类型
        private List<CommtopictypeBean> commtopictype;//继续教育交流类型
        private List<UsertradeBean> usertrade;//行业
        private List<UserstatusBean> userstatus;//用户状态
        private List<PolicycreteunitBean> policycreteunit;//发布单位/部门
        private List<AuditstatusBean> auditstatus;//审核状态

        private List<UserincomeBean> userincome;//企业营收
        private List<UserscaleBean> userscale;//企业规模
        private List<UserstationBean> userstation;

        private List<AuditstatusBean> applyordertype;


        private List<ExamtypeBean> examtype;
        private List<ExamtypeBean> questiontype;
        private List<ExamtypeBean> titletype;

        public List<ExamtypeBean> getQuestiontype() {
            return questiontype;
        }

        public void setQuestiontype(List<ExamtypeBean> questiontype) {
            this.questiontype = questiontype;
        }

        public List<ExamtypeBean> getTitletype() {
            return titletype;
        }

        public void setTitletype(List<ExamtypeBean> titletype) {
            this.titletype = titletype;
        }

        public List<ExamtypeBean> getExamtype() {
            return examtype;
        }

        public void setExamtype(List<ExamtypeBean> examtype) {
            this.examtype = examtype;
        }

        public List<AuditstatusBean> getApplyordertype() {
            return applyordertype;
        }

        public void setApplyordertype(List<AuditstatusBean> applyordertype) {
            this.applyordertype = applyordertype;
        }


        public List<UserstationBean> getUserstation() {
            return userstation;
        }

        public void setUserstation(List<UserstationBean> userstation) {
            this.userstation = userstation;
        }

        public List<UserincomeBean> getUserincome() {
            return userincome;
        }

        public void setUserincome(List<UserincomeBean> userincome) {
            this.userincome = userincome;
        }

        public List<UserscaleBean> getUserscale() {
            return userscale;
        }

        public void setUserscale(List<UserscaleBean> userscale) {
            this.userscale = userscale;
        }

        public List<MessagesourcetypeBean> getMessagesourcetype() {
            return messagesourcetype;
        }

        public void setMessagesourcetype(List<MessagesourcetypeBean> messagesourcetype) {
            this.messagesourcetype = messagesourcetype;
        }

        public List<LogchangetypeBean> getLogchangetype() {
            return logchangetype;
        }

        public void setLogchangetype(List<LogchangetypeBean> logchangetype) {
            this.logchangetype = logchangetype;
        }

        public List<StudytypeBean> getStudytype() {
            return studytype;
        }

        public void setStudytype(List<StudytypeBean> studytype) {
            this.studytype = studytype;
        }

        public List<AudittypeBean> getAudittype() {
            return audittype;
        }

        public void setAudittype(List<AudittypeBean> audittype) {
            this.audittype = audittype;
        }

        public List<FaqtypeBean> getFaqtype() {
            return faqtype;
        }

        public void setFaqtype(List<FaqtypeBean> faqtype) {
            this.faqtype = faqtype;
        }

        public List<ApplyorderstatusBean> getApplyorderstatus() {
            return applyorderstatus;
        }

        public void setApplyorderstatus(List<ApplyorderstatusBean> applyorderstatus) {
            this.applyorderstatus = applyorderstatus;
        }

        public List<UsertypeBean> getUsertype() {
            return usertype;
        }

        public void setUsertype(List<UsertypeBean> usertype) {
            this.usertype = usertype;
        }

        public List<SurveystatusBean> getSurveystatus() {
            return surveystatus;
        }

        public void setSurveystatus(List<SurveystatusBean> surveystatus) {
            this.surveystatus = surveystatus;
        }

        public List<TraintypeBean> getTraintype() {
            return traintype;
        }

        public void setTraintype(List<TraintypeBean> traintype) {
            this.traintype = traintype;
        }

        public List<NewstypeBean> getNewstype() {
            return newstype;
        }

        public void setNewstype(List<NewstypeBean> newstype) {
            this.newstype = newstype;
        }

        public List<ApplystatusBean> getApplystatus() {
            return applystatus;
        }

        public void setApplystatus(List<ApplystatusBean> applystatus) {
            this.applystatus = applystatus;
        }

        public List<CommtopictypeBean> getCommtopictype() {
            return commtopictype;
        }

        public void setCommtopictype(List<CommtopictypeBean> commtopictype) {
            this.commtopictype = commtopictype;
        }

        public List<UsertradeBean> getUsertrade() {
            return usertrade;
        }

        public void setUsertrade(List<UsertradeBean> usertrade) {
            this.usertrade = usertrade;
        }

        public List<UserstatusBean> getUserstatus() {
            return userstatus;
        }

        public void setUserstatus(List<UserstatusBean> userstatus) {
            this.userstatus = userstatus;
        }

        public List<PolicycreteunitBean> getPolicycreteunit() {
            return policycreteunit;
        }

        public void setPolicycreteunit(List<PolicycreteunitBean> policycreteunit) {
            this.policycreteunit = policycreteunit;
        }

        public List<AuditstatusBean> getAuditstatus() {
            return auditstatus;
        }

        public void setAuditstatus(List<AuditstatusBean> auditstatus) {
            this.auditstatus = auditstatus;
        }

        public static class MessagesourcetypeBean {
            /**
             * paramDesc : 消息来源类型
             * code : 01
             * value : 通知消息
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class LogchangetypeBean {
            /**
             * paramDesc : 积分变更类型
             * code : 01
             * value : 线上辅导培训
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class StudytypeBean {
            /**
             * paramDesc : 继续教育学习类型
             * code : 01
             * value : 要闻
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class AudittypeBean {
            /**
             * paramDesc : 信息审核类型
             * code : 01
             * value : 用户注册
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class FaqtypeBean {
            /**
             * paramDesc : 常见问题类型
             * code : 01
             * value : 日常
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class ApplyorderstatusBean {
            /**
             * paramDesc : 报名状态
             * code : 01
             * value : 待支付
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class UsertypeBean {
            /**
             * paramDesc : 用户类型
             * code : 01
             * value : 政府会员
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class SurveystatusBean {

            public SurveystatusBean() {
            }

            public SurveystatusBean(String code, String value) {
                this.code = code;
                this.value = value;
            }

            /**
             * paramDesc : 问卷状态
             * code : 02
             * value : 已发布
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class TraintypeBean {
            /**
             * paramDesc : 继续教育培训类型
             * code : 01
             * value : 要闻
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class NewstypeBean {
            /**
             * paramDesc : 首页新闻类型
             * code : 0605
             * value : 个人入会申请流程
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class ApplystatusBean {
            /**
             * paramDesc : 政策申报类型
             * code : 01
             * value : 待审核
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class CommtopictypeBean {
            /**
             * paramDesc : 继续教育交流类型
             * code : 01
             * value : 厦门身边事
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class UsertradeBean {
            public UsertradeBean() {
            }

            public UsertradeBean(String code, String value) {
                this.code = code;
                this.value = value;
            }

            /**
             * paramDesc : 行业
             * code : 01
             * value : 农林牧渔
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class UserstatusBean {
            /**
             * paramDesc : 用户状态
             * code : 01
             * value : 待审核
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class PolicycreteunitBean {
            public PolicycreteunitBean() {
            }

            public PolicycreteunitBean(String code, String value) {
                this.code = code;
                this.value = value;
            }

            /**
             * paramDesc : 发布单位/部门
             * code : 01
             * value : 同安会计学会
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class AuditstatusBean {
            /**
             * paramDesc : 审核状态
             * code : 01
             * value : 待审核
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class UserincomeBean {

            /**
             * paramDesc : 企业营收
             * code : 01
             * value : 0-100万
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class UserscaleBean {

            /**
             * paramDesc : 企业规模
             * code : 01
             * value : 1-20人
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class UserstationBean {

            /**
             * paramDesc : 职位
             * code : 01
             * value : 普通人员
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }


        public static class ExamtypeBean {

            /**
             * "paramDesc":"考试题目类型","code":"01","value":"输入框"
             */

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }


    }

/*
    *//**{
     "paramDesc": "报名状态",
     "code": "01",
     "value": "待支付"
     }, {
     "paramDesc": "报名状态",
     "code": "02",
     "value": "报名成功"
     }, {
     "paramDesc": "报名状态",
     "code": "03",
     "value": "支付失败"
     }

     * params : {"usertrade":[{"paramDesc":"行业","code":"01","value":"农林牧渔"},{"paramDesc":"行业","code":"02","value":"医药卫生"}],"usertype":[{"paramDesc":"用户类型","code":"01","value":"政府会员"}]}
     *//*

    private ParamsBean params;

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public static class ParamsBean {


        private List<UsertradeBean> usertrade;
        private List<UsertypeBean> usertype;
        private List<UsertypeBean> applyorderstatus;


        public List<UsertradeBean> getUsertrade() {
            return usertrade;
        }

        public void setUsertrade(List<UsertradeBean> usertrade) {
            this.usertrade = usertrade;
        }

        public List<UsertypeBean> getUsertype() {
            return usertype;
        }

        public void setUsertype(List<UsertypeBean> usertype) {
            this.usertype = usertype;
        }

        public List<UsertypeBean> getApplyorderstatus() {
            return applyorderstatus;
        }

        public void setApplyorderstatus(List<UsertypeBean> applyorderstatus) {
            this.applyorderstatus = applyorderstatus;
        }

        public static class UsertradeBean {
            *//**
     * paramDesc : 行业
     * code : 01
     * value : 农林牧渔
     *//*

            private String paramDesc;
            private String code;
            private String value;

            public UsertradeBean() {
            }

            public UsertradeBean(String code, String value) {
                this.code = code;
                this.value = value;
            }

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class UsertypeBean {
            *//**
     * paramDesc : 用户类型
     * code : 01
     * value : 政府会员
     *//*

            private String paramDesc;
            private String code;
            private String value;

            public String getParamDesc() {
                return paramDesc;
            }

            public void setParamDesc(String paramDesc) {
                this.paramDesc = paramDesc;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }*/
}
