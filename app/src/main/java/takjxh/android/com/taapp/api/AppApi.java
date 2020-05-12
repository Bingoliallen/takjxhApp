package takjxh.android.com.taapp.api;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import takjxh.android.com.commlibrary.net.response.CommonResponse;
import takjxh.android.com.taapp.activityui.base.UserExtsListBean;
import takjxh.android.com.taapp.activityui.bean.AdsBean;
import takjxh.android.com.taapp.activityui.bean.AdsDetailBean;
import takjxh.android.com.taapp.activityui.bean.ApplyTypeBean;
import takjxh.android.com.taapp.activityui.bean.BannnerDetailBean;
import takjxh.android.com.taapp.activityui.bean.BannnersBean;
import takjxh.android.com.taapp.activityui.bean.CodeBean;
import takjxh.android.com.taapp.activityui.bean.CommDetailBean;
import takjxh.android.com.taapp.activityui.bean.CommListBean;
import takjxh.android.com.taapp.activityui.bean.CommQuestionBean;
import takjxh.android.com.taapp.activityui.bean.CommTypeListBean;
import takjxh.android.com.taapp.activityui.bean.CompanyTypesBean;
import takjxh.android.com.taapp.activityui.bean.CompanysBean;
import takjxh.android.com.taapp.activityui.bean.ContributeBean;
import takjxh.android.com.taapp.activityui.bean.ContributeDetial;
import takjxh.android.com.taapp.activityui.bean.ContributeDetialBean;
import takjxh.android.com.taapp.activityui.bean.ContributeListBean;
import takjxh.android.com.taapp.activityui.bean.DownFileByApplyId;
import takjxh.android.com.taapp.activityui.bean.ExamAddBean;
import takjxh.android.com.taapp.activityui.bean.ExamIndexBean;
import takjxh.android.com.taapp.activityui.bean.FeedbackListBean;
import takjxh.android.com.taapp.activityui.bean.HealthIndexBean;
import takjxh.android.com.taapp.activityui.bean.HealthListBean;
import takjxh.android.com.taapp.activityui.bean.IssueDetailBean;
import takjxh.android.com.taapp.activityui.bean.IssueListBean;
import takjxh.android.com.taapp.activityui.bean.JfpmBean;
import takjxh.android.com.taapp.activityui.bean.KsnrBean;
import takjxh.android.com.taapp.activityui.bean.KsnrDetailBean;
import takjxh.android.com.taapp.activityui.bean.LoginUIBean;
import takjxh.android.com.taapp.activityui.bean.MessagesBean;
import takjxh.android.com.taapp.activityui.bean.NewsBean;
import takjxh.android.com.taapp.activityui.bean.NewsDetailBean;
import takjxh.android.com.taapp.activityui.bean.NewstypeBean;
import takjxh.android.com.taapp.activityui.bean.OrderDetailBean;
import takjxh.android.com.taapp.activityui.bean.OrderGenerateBean;
import takjxh.android.com.taapp.activityui.bean.OrderQueryBean;
import takjxh.android.com.taapp.activityui.bean.OrgansBean;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyBean;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyDetailBean;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyEvaluateDetailBean;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyHelpBean;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyHelpBean2;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyHelpDetailBean;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyOrgansBean;
import takjxh.android.com.taapp.activityui.bean.PolicyDetailBean;
import takjxh.android.com.taapp.activityui.bean.PolicyIndexBean;
import takjxh.android.com.taapp.activityui.bean.PolicyapplyaddList;
import takjxh.android.com.taapp.activityui.bean.PolicysBean;
import takjxh.android.com.taapp.activityui.bean.QaAnswerListBean;
import takjxh.android.com.taapp.activityui.bean.QaDetailBean;
import takjxh.android.com.taapp.activityui.bean.QaLatestBean;
import takjxh.android.com.taapp.activityui.bean.QaListBean;
import takjxh.android.com.taapp.activityui.bean.QaQauserListBean;
import takjxh.android.com.taapp.activityui.bean.QaTypeListBean;
import takjxh.android.com.taapp.activityui.bean.QafaqListBean;
import takjxh.android.com.taapp.activityui.bean.QuestionAnswerListBean;
import takjxh.android.com.taapp.activityui.bean.RegisterBean;
import takjxh.android.com.taapp.activityui.bean.ScoreRuleBean;
import takjxh.android.com.taapp.activityui.bean.ScoresBean;
import takjxh.android.com.taapp.activityui.bean.StartBean;
import takjxh.android.com.taapp.activityui.bean.StudyTypeBean;
import takjxh.android.com.taapp.activityui.bean.StudysBean;
import takjxh.android.com.taapp.activityui.bean.StudysDetailBean;
import takjxh.android.com.taapp.activityui.bean.SurveyDetailBean;
import takjxh.android.com.taapp.activityui.bean.SurveyListBean;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.bean.TaskDetailBean;
import takjxh.android.com.taapp.activityui.bean.TaskDetailBean1;
import takjxh.android.com.taapp.activityui.bean.TaskListBean;
import takjxh.android.com.taapp.activityui.bean.TaskTypeListBean;
import takjxh.android.com.taapp.activityui.bean.TrainTypeBean;
import takjxh.android.com.taapp.activityui.bean.TrainsBean;
import takjxh.android.com.taapp.activityui.bean.TrainsDetailBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.activityui.bean.UserExtDetailBean;
import takjxh.android.com.taapp.activityui.bean.WdZxbbBean;
import takjxh.android.com.taapp.activityui.bean.XxjfBean;
import takjxh.android.com.taapp.activityui.bean.XxshBean;
import takjxh.android.com.taapp.activityui.bean.ZxbbBean;
import takjxh.android.com.taapp.bean.CenterBean;
import takjxh.android.com.taapp.bean.HeadImgBean;
import takjxh.android.com.taapp.bean.LogoBean;
import takjxh.android.com.taapp.bean.MenuBean;
import takjxh.android.com.taapp.bean.UserInfoBean;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import rx.Observable;
import takjxh.android.com.taapp.view.mulitmenuselect.Children;
import takjxh.android.com.taapp.view.mulitmenuselect.Contribute;

/**
 * api接口
 *
 * @Author: libaibing
 * @Date: 2019-01-09 12:54
 * @Description:
 **/

public interface AppApi {

    /**
     * 获取启动页的图片/视频信息
     */
    @GET("app/sys/start.do")
    Observable<StartBean<List<String>>> start();


    /**
     * 用户登录
     */
    @POST("app/user/login.do")
    Observable<LoginUIBean> loginUI(
            @Body Map<String, String> searchRequest);

    /**
     * 用户注册
     */
    @POST("app/user/register.do")
    Observable<RegisterBean> register(
            @Body Map<String, String> searchRequest);

    /**
     * 获取短信验证码
     */
    @GET("app/user/getcode.do")
    Observable<CommonResponse<CodeBean>> getCode(
            @Query("phone") String phone);

    /**
     * 获取新闻类型信息
     */
    @GET("app/index/get/newstype.do")
    Observable<NewstypeBean<List<NewstypeBean.NewsTypesBean>>> newstype();

    /**
     * 获取Banner广告信息
     */
    @GET("app/sys/get/bannner/list.do")
    Observable<BannnersBean<List<BannnersBean.BannersBean>>> bannners();


    /**
     * 获取指定Banner广告的内容信息（站内）接口
     */
    @GET("app/sys/get/bannner/detail.do")
    Observable<BannnerDetailBean<BannnerDetailBean.DetailBean>> bannnerdetail(
            @Query("id") String id);


    /**
     * 获取首页新闻列表
     */
    @GET("app/index/news/list/{typeLike}.do")
    Observable<NewsBean<List<NewsBean.NewsListBean>>> newslist(
            @Path("typeLike") String typeLike,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     * 获取首页新闻详情信息
     */
    @GET("app/index/news/detail/{id}.do")
    Observable<NewsDetailBean<NewsDetailBean.NewsBean>> newsdetail(
            @Path("id") String id);


    /**
     * 获取继续教育学习类型信息
     */
    @GET("app/study/get/studytype.do")
    Observable<StudyTypeBean<List<StudyTypeBean.StudyTypesBean>>> studytype(
            @Query("token") String token
    );


    /**
     * 获取继续教育学习列表信息
     */
    @GET("app/study/list/{typeLike}.do")
    Observable<StudysBean<List<StudysBean.StudyListBean>>> studyslist(
            @Path("typeLike") String typeLike,
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );


    /**
     * 获取继续教育学习详情信息
     */
    @GET("app/study/detail/{id}.do")
    Observable<StudysDetailBean<StudysDetailBean.StudyBean>> studysdetail(
            @Path("id") String id,
            @Query("token") String token
    );

    /**
     * 获取继续教育培训类型信息
     */
    @GET("app/train/get/traintype.do")
    Observable<TrainTypeBean<List<TrainTypeBean.TrainTypesBean>>> traintype(
            @Query("token") String token
    );


    /**
     * 获取继续教育培训列表信息
     */
    @GET("app/train/list/{typeLike}.do")
    Observable<TrainsBean<List<TrainsBean.TrainListBean>>> trainslist(
            @Path("typeLike") String typeLike,
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     * 获取继续教育培训详情信息
     */
    @GET("app/train/detail/{id}.do")
    Observable<TrainsDetailBean<TrainsDetailBean.TrainBean>> trainsdetail(
            @Path("id") String id,
            @Query("token") String token);

    /**
     * 获取广告信息列表信息
     */
    @GET("app/ad/list.do")
    Observable<AdsBean<List<AdsBean.AdListBean>>> adslist(
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     * 获取广告信息详情信息
     */
    @GET("app/ad/detail/{id}.do")
    Observable<AdsDetailBean<AdsDetailBean.AdBean>> adsdetail(
            @Path("id") String id,
            @Query("token") String token);

    /**
     * 提交用户建议反馈信息
     */
    @POST("app/user/feedback.do")
    Observable<CommonResponse> feedback(
            @Query("token") String token,
            @Body Map<String, String> searchRequest);


    /**
     * 获取用户积分列表信息
     */
    @GET("app/user/score/list.do")
    Observable<ScoresBean> scoreslist(
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );


    /**
     * 获取用户积分排名列表信息
     */
    @GET("app/user/rank/list.do")
    Observable<JfpmBean<List<JfpmBean.UserScoresBean>>> rankslist(
            @Query("token") String token,
            @Query("type") String type,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     * 获取积分规则信息
     */
    @GET("app/sys/score/rule.do")
    Observable<ScoreRuleBean> scorerule(
            @Query("token") String token
    );

    /**
     * 获取我的积分信息
     */
    @GET("app/user/myscore.do")
    Observable<XxjfBean> myscore(
            @Query("token") String token
    );

    /**
     * 获取审核信息列表
     */
    @GET("app/user/audit/list.do")
    Observable<XxshBean<List<XxshBean.AuditsBean>>> auditlist(
            @Query("token") String token,
            @Query("status") String status,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );


    /**
     * 用户密码修改
     */
    @POST("app/user/updatepwd.do")
    Observable<CommonResponse> updatepwd(
            @Query("token") String token,
            @Body Map<String, String> searchRequest);


    /**
     * 获取在线报名类型列表信息
     */
    @GET("app/apply/item/list.do")
    Observable<ZxbbBean<List<ZxbbBean.ApplyItemsBean>>> itemlist(
            @Query("token") String token
    );


    /**
     * 获取系统参数信息
     */
    @GET("app/sys/param/list.do")
    Observable<SysParamBean> paramlist(
    );

    /**
     * 获取所有行业的树信息
     */
    @GET("app/policy/apply/trade/tree/list.do")
    Observable<List<Children>> tradetreelistt(
    );





    /**
     * 获取我的在线报名列表信息
     */
    @GET("app/apply/list.do")
    Observable<WdZxbbBean<List<WdZxbbBean.ApplyOrdersBean>>> applyslist(
            @Query("token") String token,
            @Query("key") String key,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     * 获取报名订单详情信息
     */
    @GET("app/apply/order/detail/{id}.do")
    Observable<OrderDetailBean<OrderDetailBean.OrderBean>> orderdetail(
            @Path("id") String id,
            @Query("token") String token);

    /**
     * 生成报名订单信息，在在线报名申请页面点击“马上报名”调用
     */
    @POST("app/apply/order/generate.do")
    Observable<OrderGenerateBean> ordergenerate(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);

    /**
     * 查询指定报名订单的状态和剩余生效时间
     */
    @GET("app/apply/order/query/{id}.do")
    Observable<OrderQueryBean> orderquery(
            @Path("id") String id,
            @Query("token") String token);

    /**
     * 完成报名下单操作（包含支付）
     */
    @POST("app/apply/order/done.do")
    Observable<CommonResponse> orderdone(
            @Query("token") String token,
            @Body Map<String, String> searchRequest);


    /**
     * 获取政策资讯首页信息
     */
    @GET("app/policy/index.do")
    Observable<PolicyIndexBean> policyindex(
            @Query("token") String token);

    /**
     * 获取政策资讯列表信息
     */
    @GET("app/policy/list.do")
    Observable<PolicysBean<List<PolicysBean.PolicyInfosBean>>> policyslist(
            @Query("token") String token,
            @Query("type") String type,
            @Query("key") String key,
            @Query("createUnit") String createUnit,
            @Query("trade") String trade,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );


    /**
     * 获取政策资讯详情信息
     */
    @GET("app/policy/detail/{id}.do")
    Observable<PolicyDetailBean<PolicyDetailBean.PolicyBean>> policydetail(
            @Path("id") String id,
            @Query("token") String token);


    /**
     * 获取公司类型信息
     */
    @GET("app/company/type/list.do")
    Observable<CompanyTypesBean<List<CompanyTypesBean.CompanyTypeBean>>> companytypelist(
            @Query("token") String token);


    /**
     * 获取公司列表信息
     */
    @GET("app/company/list.do.do")
    Observable<CompanysBean<List<CompanysBean.CompanyBean>>> companyslist(
            @Query("token") String token,
            @Query("type") String type,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );


    /**
     * 获取政策申报列表信息
     */
    @GET("app/policy/apply/list.do")
    Observable<PolicyApplyBean<List<PolicyApplyBean.ApplyInfosBean>>> policyapplylist(
            @Query("token") String token,
            @Query("status") String status,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     * 获取指定的政策申报详情信息
     */
    @GET("app/policy/apply/detail/{id}.do")
    Observable<PolicyApplyDetailBean<PolicyApplyDetailBean.ApplyInfoBean>> policyapplydetail(
            @Path("id") String id,
            @Query("token") String token);

    /**
     * 获取第三方服务机构列表信息
     */
    @GET("app/organ/list.do")
    Observable<OrgansBean<List<OrgansBean.OrganListBean>>> organslist(
            @Query("token") String token,
            @Query("key") String key,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     * 获取求助机构列表信息
     */
    @GET("app/policy/apply/organ/list.do")
    Observable<PolicyApplyOrgansBean<List<PolicyApplyOrgansBean.OrganListBean>>> policyapplyorganslist(
            @Query("token") String token,
            @Query("key") String key,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );


    /**
     * 获取政策申报类型列表信息
     */
    @GET("app/policy/apply/type/list.do")
    Observable<ApplyTypeBean<List<ApplyTypeBean.ApplyTypesBean>>> policyapplytypelist(
            @Query("token") String token
    );

    /**
     * 进入投稿页面获取投稿类型列表信息（列表树只有两级）
     */
    @GET("app/index/contribute.do")
    Observable<ContributeBean<List<Contribute>>> appindexcontribute(
            @Query("token") String token
    );

    /**
     *  提交投稿信息
     */
    @POST("app/index/contribute/done.do")
    Observable<CommonResponse> contributedone(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);





    /**
     * 加载指定政策申报项信息接口
     */
    @GET("app/policy/apply/add.do")
    Observable<PolicyapplyaddList> policyapplyadd(
            @Query("typeId") String typeId,
            @Query("token") String token
    );


    /**
     *  提交政策申报并存为草稿
     */
    @POST("app/policy/apply/add/temp/done.do")
    Observable<CommonResponse> applyadddtempone(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);



    /**
     * 政策申报提交
     */
    @POST("app/policy/apply/add/done.do")
    Observable<CommonResponse> applyadddone(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);

    /**
     * 计算扶持的金额（每次填写选择信息项的时候调用一次，并且将结果显示在提交按钮上面，效果如下图）
     */
    @POST("app/policy/apply/getPredictAmount.do")
    Observable<CommonResponse> getPredictAmount(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);




    /**
     * 获取指定正常申报信息填报详情信息（和update接口类似，不过value有填写的话都有值）看详情
     */
    @GET("app/policy/apply/detail/{id}.do")
    Observable<PolicyapplyaddList> policyapplydetail1(
            @Path("id") String id,
            @Query("token") String token);

    /**
     * 进入指定正常申报信息填报更新页面数据加载（和add接口类似，不过value有填写的话都有值）  进入修改时，获取的详情
     */
    @GET("app/policy/apply/update.do")
    Observable<PolicyapplyaddList> policyapplyupdate(
            @Query("applyId") String applyId,
            @Query("token") String token
    );

    /**
     * 更新指定的政策申报信息
     */
    @POST("app/policy/apply/update/done.do")
    Observable<CommonResponse> applyupdatedone(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);

    /**
     * 检查当前用户是否有申报过指定的政策（当调用接口3之前需要先调用下这个接口，当resCode为1可进行接口3调用，反之提示）
     */
    @GET("app/policy/apply/checkApply.do")
    Observable<CommonResponse> policyapplycheckApply(
            @Query("typeId") String typeId,
            @Query("token") String token
    );





    /**
     * 提交政策申报求助信息
     */
    @POST("app/policy/apply/add/help.do")
    Observable<CommonResponse> applyadddhelp(
            @Query("token") String token,
            @Body Map<String, String> searchRequest);

    /**
     * 获取政策申报求助列表信息（求助人）
     */
    @GET("app/policy/apply/help/list.do")
    Observable<PolicyApplyHelpBean<List<PolicyApplyHelpBean.HelpListBean>>> policyapplyhelplist(
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     * 获取政策申报求助列表信息（求助机构）
     */
    @GET("app/policy/apply/help/list2.do")
    Observable<PolicyApplyHelpBean2<List<PolicyApplyHelpBean2.HelpListBean>>> policyapplyhelplist2(
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     * 获取指定的政策申报求助详情信息
     */
    @GET("app/policy/apply/help/detail/{id}.do")
    Observable<PolicyApplyHelpDetailBean<PolicyApplyHelpDetailBean.HelpBean>> policyapplyhelpdetail(
            @Path("id") String id,
            @Query("token") String token);

    /**
     * 获取指定求助评价页面数据信息
     */
    @GET("app/policy/apply/evaluate/detail/{id}.do")
    Observable<PolicyApplyEvaluateDetailBean<PolicyApplyEvaluateDetailBean.DetailVoBean>> policyapplyevaluatedetail(
            @Path("id") String id,
            @Query("token") String token);

    /**
     * 提交指定求助的评价信息
     */
    @POST("app/policy/apply/add/evaluate.do")
    Observable<CommonResponse> applyadddevaluate(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);

    /**
     * 获取系统消息列表信息
     */
    @GET("app/message/list.do")
    Observable<MessagesBean<List<MessagesBean.SysMessagesBean>>> messagelist(
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     * 信息审核提交接口;进行系统信息审核提交
     */
    @POST("app/user/update/audit.do")
    Observable<CommonResponse> updateaudit(
            @Query("token") String token,
            @Body Map<String, String> searchRequest);

    /**
     * 获取调查问卷列表信息
     */
    @GET("app/survey/list.do")
    Observable<SurveyListBean<List<SurveyListBean.MarketSuveysBean>>> surveylist(
            @Query("token") String token,
            @Query("createUnit") String createUnit,
            @Query("status") String status,/*
            @Query("orderBy") String orderBy,*/
            @Query("ascOrDesc") String ascOrDesc,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     * 获取继续教育交流类型列表信息
     */
    @GET("app/comm/type/list.do")
    Observable<CommTypeListBean<List<CommTypeListBean.CommTypesBean>>> commtypelist(
            @Query("token") String token);


    /**
     * 获取继续教育交流主题列表信息
     */
    @GET("app/comm/list/{type}.do")
    Observable<CommListBean<List<CommListBean.CommTopicsBean>>> commlist(
            @Path("type") String type,
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     * 获取我的投稿列表信息
     */
    @GET("app/index/contribute/list.do")
    Observable<ContributeListBean<List<ContributeDetial>>> contributeList(
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     * 获取指定的投稿详情信息
     */
    @GET("app/index/contribute/detail/{id}.do")
    Observable<ContributeDetialBean<ContributeDetial>> contributedetail(
            @Path("id") String id,
            @Query("token") String token);







    /**
     * 新增继续教育交流主题信息
     */
    @POST("app/comm/add.do")
    Observable<CommonResponse> commadd(
            @Query("token") String token,
            @Body Map<String, String> searchRequest);


    /**
     * 获取指定的继续教育主题详情信息
     */
    @GET("app/comm/detail/{id}.do")
    Observable<CommDetailBean<CommDetailBean.DetailBean>> commdetail(
            @Path("id") String id,
            @Query("token") String token);

    /**
     * 新增继续教育交流问题信息
     */
    @POST("app/comm/question/add.do")
    Observable<CommonResponse> questionadd(
            @Query("token") String token,
            @Body Map<String, String> searchRequest);

    /**
     * 获取继续教育交流问题列表信息
     */
    @GET("app/comm/question/list/{topicId}.do")
    Observable<CommQuestionBean<List<CommQuestionBean.CommQuestionsBean>>> commquestionlist(
            @Path("topicId") String topicId,
            @Query("token") String token,
            @Query("orderBy") String orderBy,
            @Query("ascOrDesc") String ascOrDesc,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     *  新增继续教育交流问题回答信息
     */
    @POST("app/comm/question/answer/add.do")
    Observable<CommonResponse> questionansweradd(
            @Query("token") String token,
            @Body Map<String, String> searchRequest);

    /**
     *  获取指定继续教育交流问题回答列表信息
     */
    @GET("app/comm/question/answer/list/{questionId}.do")
    Observable<QuestionAnswerListBean<List<QuestionAnswerListBean.CommAnswersBean>>> questionanswerlist(
            @Path("questionId") String questionId,
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     *   获取继续教育答疑常见问题列表信息
     */
    @GET("app/qa/faq/list.do")
    Observable<QafaqListBean> qafaqlist(
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     *   获取继续教育答疑类型列表信息
     */
    @GET("app/qa/type/list.do")
    Observable<QaTypeListBean<List<QaTypeListBean.CommTypesBean>>> qatypelist(
            @Query("token") String token
    );

    /**
     *  新增继续教育答疑信息
     */
    @POST("app/qa/add.do")
    Observable<CommonResponse> commquestionadd(
            @Query("token") String token,
            @Body Map<String, String> searchRequest);

    /**
     *  获取指定继续教育答疑列表信息
     */
    @GET("app/qa/list.do")
    Observable<QaListBean<List<QaListBean.QasBean>>> qalist(
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     * 获取指定的继续教育答疑详情信息
     */
    @GET("app/qa/detail/{id}.do")
    Observable<QaDetailBean<QaDetailBean.DetailBean>> qadetail(
            @Path("id") String id,
            @Query("token") String token
    );

    /**
     * 新增继续教育答疑回复信息
     */
    @POST("app/qa/answer/add.do")
    Observable<CommonResponse> qaansweradd(
            @Query("token") String token,
            @Body Map<String, String> searchRequest);

    /**
     *   获取指定继续教育答疑列表信息
     */
    @GET("app/qa/answer/list/{qaId}.do")
    Observable<QaAnswerListBean> qaanswerlist(
            @Path("qaId") String qaId,
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     *  获取继续教育答疑专家列表信息
     */
    @GET("app/qa/qauser/list.do")
    Observable<QaQauserListBean<List<QaQauserListBean.QaUsersBean>>> qaqauserlist(
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     *  获取继续教育答疑首页最新答疑信息
     */
    @GET("app/qa/latest.do")
    Observable<QaLatestBean<QaLatestBean.DetailBean>> qalatest(
            @Query("token") String token
    );

    /**
     *   获取指定的调查问卷详情信息
     */
    @GET("app/survey/detail/{id}.do")
    Observable<SurveyDetailBean> surveydetail(
            @Path("id") String id,
            @Query("token") String token
    );

    /**
     * 提交调查问卷回答信息
     */
    @POST("app/survey/answer/add.do")
    Observable<CommonResponse> surveyansweradd(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);


    /**
     *  获取报表任务类型列表信息
     */
    @GET("app/task/type/list.do")
    Observable<TaskTypeListBean<List<TaskTypeListBean.CommTypesBean>>> tasktypelist(
            @Query("token") String token
    );

    /**
     *  获取报表任务列表信息
     */
    @GET("app/task/list/{type}.do")
    Observable<TaskListBean<List<TaskListBean.ReportTasksBean>>> tasklist(
            @Path("type") String type,
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     *  获取指定的报表任务详情信息（类似于问卷调查）
     */
    @GET("app/task/detail/{id}.do")
    Observable<TaskDetailBean> taskdetail(
            @Path("id") String id,
            @Query("token") String token
    );

    /**
     *  获取指定的报表任务详情信息（类似于问卷调查）
     */
    @GET("app/task/detail/{id}.do")
    Observable<TaskDetailBean1> taskdetail1(
            @Path("id") String id,
            @Query("token") String token
    );



    /**
     *  提交报表任务回答信息（类似于调查问卷）
     */
    @POST("app/task/answer/add.do")
    Observable<CommonResponse> taskansweradd(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);

    /**
     *  获取课题任务列表信息（用户请使用：陈海彬/a367e51434824b98）
     */
    @GET("app/issue/list/{type}.do")
    Observable<IssueListBean<List<IssueListBean.UserIssueTasksBean>>> issuelist(
            @Path("type") String type,
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     *  获取指定的课题任务详情信息（类似于问卷调查）
     */
    @GET("app/issue/detail/{id}.do")
    Observable<IssueDetailBean> issuedetail(
            @Path("id") String id,
            @Query("token") String token
    );

    /**
     *  提交任务回答信息（类似于调查问卷）
     */
    @POST("app/issue/answer/add.do")
    Observable<CommonResponse> issueansweradd(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);

    /**
     *  提交任务反馈信息
     */
    @POST("app/issue/feedback/add.do")
    Observable<CommonResponse> issuefeedbackadd(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);

    /**
     *  获取理论考试首页信息
     */
    @GET("app/exam/index.do")
    Observable<ExamIndexBean> examindex(
            @Query("token") String token
      );

    /**
     *  获取理论考试题目信息
     */
    @GET("app/exam/start/{type}.do")
    Observable<KsnrBean> examlist(
            @Path("type") String type,
            @Query("token") String token
    );



    /**
     *  提交考试回答信息
     */
    @POST("app/exam/answer/add.do")
    Observable<ExamAddBean> examadd(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);

    /**
     *  理论考试成绩详情查询
     */
    @GET("app/exam/detail/{id}.do")
    Observable<KsnrDetailBean> examdetail(
            @Path("id") String id,
            @Query("token") String token
    );

    /**
     *  提交继续教育学习情况（当停留在文章页面超过两分钟提交，如果是视频文章的话，超过三分钟提交）
     */
    @POST("app/study/read/done.do")
    Observable<CommonResponse> studyreaddone(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);

    /**
     *  提交继续教育培训情况（当停留在文章页面超过两分钟提交，如果是视频文章的话，超过三分钟提交）
     */
    @POST("app/train/read/done.do")
    Observable<CommonResponse> trainreaddone(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);


    /**
     *  注销当前用户的账号
     */
    @GET("app/user/cancel.do")
    Observable<CommonResponse> usercancel(
            @Query("token") String token
    );


    /**
     *  更新用户基本信息
     */
    @POST("app/user/update/info.do")
    Observable<CommonResponse> updateinfo(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);



    /**
     *   提交继续教育问题答案采纳
     */
    @POST("app/qa/answer/accept.do")
    Observable<CommonResponse> answeraccept(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);

    /**
     *  获取用户反馈列表信息
     */
    @GET("app/user/feedback/list.do")
    Observable<FeedbackListBean<List<FeedbackListBean.UserFeedbackVosBean>>> feedbacklist(
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     *  获取用户扩展列表信息
     */
    @GET("app/user/ext/list.do")
    Observable<UserExtsListBean<List<UserExtsListBean.UserExtListVosBean>>> userextlist(
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );

    /**
     *   新增用户扩展信息
     */
    @POST("app/user/ext/add.do")
    Observable<CommonResponse> userextadd(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);

    /**
     *   更新用户扩展信息
     */
    @POST("app/user/ext/update.do")
    Observable<CommonResponse> userextupdate(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);


    /**
     *  获取指定的用户扩展信息详情
     */
    @GET("app/user/ext/detail/{id}.do")
    Observable<UserExtDetailBean> userextdetail(
            @Path("id") String id,
            @Query("token") String token
    );


    /**
     *   设置用户默认用户扩展信息
     */
    @POST("app/user/ext/default/{id}.do")
    Observable<CommonResponse> userextdefault(
            @Path("id") String id,
            @Query("token") String token
    );

    /**
     *   删除指定的用户扩展信息
     */
    @POST("app/user/ext/del/{id}.do")
    Observable<CommonResponse> userextdel(
            @Path("id") String id,
            @Query("token") String token
    );


    /**
     *  进入每日健康报告页面数据初始化
     */
    @GET("app/health/index.do")
    Observable<HealthIndexBean> healthindex(
            @Query("token") String token
    );


    /**
     *   提交每日健康报告页面  ???
     */
    @POST("app/health/add.do")
    Observable<CommonResponse> healthadd(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);


    /**
     *  获取健康报告列表
     */
    @GET("app/health/list.do")
    Observable<HealthListBean<List<HealthListBean.HealthListVosBean>>> healthlist(
            @Query("token") String token,
            @Query("page") String page,
            @Query("pageSize") String pageSize
    );


    /**
     *   将极光推送的RegistrationID提交到服务端
     */
    @POST("app/user/push/registrationid.do")
    Observable<CommonResponse> registrationid(
            @Query("token") String token,
            @Body Map<String, Object> searchRequest);


    /**
     *   下载政策申请表模板接口
     */
    @GET("app/policy/apply/downFileByApplyId.do")
    Observable<DownFileByApplyId> downFileByApplyId(
            @Query("applyId") String applyId,
            @Query("token") String token
    );

    /**
     *   下载政策申报收据接口
     */
    @GET("app/policy/apply/downSJByApplyId.do")
    Observable<DownFileByApplyId> downSJByApplyId(
            @Query("applyId") String applyId,
            @Query("token") String token
    );






    /**
     * 附件上传
     */
    @Multipart
    @POST("app/uploadfile.do")
    Observable<UploadFileBean> upload(
         /*   @Query("token") String token,*/
            @Part MultipartBody.Part file
    );


    /**
     * 修改用户头像
     */
    @Multipart
    @POST("app/uploadfile.do")
    Observable<UploadFileBean> editHeadImg1(
          /*  @Query("token") String token,*/
            @Part MultipartBody.Part file
    );










    /**
     * 修改用户头像
     */
    @Multipart
    @POST("app/uploadfile.do")
    Observable<CommonResponse<HeadImgBean>> editHeadImg(
            @Header("accessToken") String token,
            @Part MultipartBody.Part file
    );



    /**
     * 初始化：启动加载
     *
     * @Param:
     * @return: LogoBean
     * @Author: libaibing
     * @Date: 2019/1/23
     */

    @GET("api/home/logo")
    Observable<CommonResponse<LogoBean>> logo();





    /**
     * 首页底部菜单
     */
    @GET("api/home/getMenuAll")
    Observable<CommonResponse<MenuBean>> menu(

    );



    /**
     * 资讯详情
     */
    @GET("api/home/getHotNewsById/{id}")
    Observable<CommonResponse<takjxh.android.com.taapp.bean.NewsDetailBean>> hotNews(
            @Path("id") String id
    );








    @GET("bimg/338/{fileName}")  //{fileName}是动态码
    @Streaming
        //GET下载文件必须结合@Streaming使用
    Observable<CommonResponse> downLoadImg(
            @Path("fileName") String fileName
    );





    /**
     * 退出账号
     */
    @GET("api/user/loginOut")
    Observable<CommonResponse<Object>> loginOut(
            @Header("accessToken") String token
    );





    /**
     * 获取个人基本信息
     */
    @POST("api/user/getUserInfo")
    Observable<CommonResponse<UserInfoBean>> getUserInfo(
            @Header("accessToken") String token
    );

    /**
     * 个人中心模块
     */
    @POST("api/user/getCenter")
    Observable<CommonResponse<CenterBean>> getCenter(
            @Header("accessToken") String token
    );


}
